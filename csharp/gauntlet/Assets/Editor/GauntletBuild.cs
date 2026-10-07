using System;
using System.Linq;
using UnityEditor;
using UnityEditor.Build.Reporting;
using UnityEngine;

// The "play without opening Unity" pipeline, driven by bin/play:
//   1. rebuild the scene from code (GauntletSceneBuilder)
//   2. add a "Game" object and attach your `Game` class to it, if it exists (your composition root)
//   3. build a macOS app into Builds/Gauntlet.app
public static class GauntletBuild
{
    public static void PlayBuild()
    {
        GauntletSceneBuilder.Build();

        var gameType = AppDomain.CurrentDomain.GetAssemblies()
            .Where(a => a.GetName().Name == "Assembly-CSharp")
            .SelectMany(a => a.GetTypes())
            .FirstOrDefault(t => t.Name == "Game" && typeof(MonoBehaviour).IsAssignableFrom(t));
        if (gameType != null)
        {
            new GameObject("Game").AddComponent(gameType);
            var scene = UnityEngine.SceneManagement.SceneManager.GetActiveScene();
            UnityEditor.SceneManagement.EditorSceneManager.MarkSceneDirty(scene);
            UnityEditor.SceneManagement.EditorSceneManager.SaveScene(scene);
            Debug.Log("GAUNTLET: attached " + gameType.FullName + " to the Game object");
        }
        else
        {
            Debug.Log("GAUNTLET: no `Game : MonoBehaviour` class yet, the scene runs without code");
        }

        // README-video recorder: inert unless the app is launched with -demo (bin/demo)
        new GameObject("DemoRecorder").AddComponent<DemoRecorder>();
        var active = UnityEngine.SceneManagement.SceneManager.GetActiveScene();
        UnityEditor.SceneManagement.EditorSceneManager.MarkSceneDirty(active);
        UnityEditor.SceneManagement.EditorSceneManager.SaveScene(active);

        PlayerSettings.fullScreenMode = FullScreenMode.Windowed;
        PlayerSettings.defaultScreenWidth = 1280;
        PlayerSettings.defaultScreenHeight = 720;
        PlayerSettings.resizableWindow = true;
        PlayerSettings.runInBackground = true;
        // Debug.Log prints just your message; errors and exceptions keep their stack traces (file:line)
        PlayerSettings.SetStackTraceLogType(LogType.Log, StackTraceLogType.None);
        PlayerSettings.SetStackTraceLogType(LogType.Warning, StackTraceLogType.None);
        PlayerSettings.SetStackTraceLogType(LogType.Error, StackTraceLogType.ScriptOnly);
        PlayerSettings.SetStackTraceLogType(LogType.Exception, StackTraceLogType.ScriptOnly);

        var report = BuildPipeline.BuildPlayer(new BuildPlayerOptions
        {
            scenes = new[] { "Assets/Scenes/Gauntlet.unity" },
            locationPathName = "Builds/Gauntlet.app",
            target = BuildTarget.StandaloneOSX,
            options = BuildOptions.Development   // keeps Debug.Log + stack traces with line numbers
        });
        Debug.Log("GAUNTLET: build " + report.summary.result + " in " + report.summary.totalTime);
        EditorApplication.Exit(report.summary.result == BuildResult.Succeeded ? 0 : 1);
    }
}
