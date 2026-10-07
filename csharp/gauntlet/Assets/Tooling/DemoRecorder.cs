using System;
using System.IO;
using UnityEngine;

// README videos, not part of the game. Inert unless the app is launched with "-demo <seconds> -demoDir <folder>":
// then a scripted brain walks the player while the body turns, one PNG is saved per frame at a fixed 20 fps,
// and the app quits. bin/demo turns the frames into a GIF.
public class DemoRecorder : MonoBehaviour
{
    float duration = 6f;
    string outDir;
    int frame;

    void Awake()
    {
        var args = Environment.GetCommandLineArgs();
        int i = Array.IndexOf(args, "-demo");
        if (i < 0) { Destroy(this); return; }
        if (i + 1 < args.Length && float.TryParse(args[i + 1], out var d)) duration = d;
        int j = Array.IndexOf(args, "-demoDir");
        outDir = j >= 0 && j + 1 < args.Length ? args[j + 1] : "demo-frames";
        Directory.CreateDirectory(outDir);
        Time.captureFramerate = 20;                 // game time advances 1/20 s per frame, however long a frame takes
    }

    void Start()
    {
        var player = FindFirstObjectByType<Player>();
        if (player != null) player.Init(new DemoBrain());
    }

    void Update()
    {
        var player = FindFirstObjectByType<Player>();
        float t = Time.time;
        if (player != null && t > 1.5f && t < 3.5f) player.transform.Rotate(0, 35f * Time.deltaTime, 0);   // look around
        if (player != null && t > 4f && t < 5.5f) player.transform.Rotate(0, -50f * Time.deltaTime, 0);
    }

    void LateUpdate()
    {
        ScreenCapture.CaptureScreenshot(Path.Combine(outDir, $"f{frame++:0000}.png"));
        if (Time.time >= duration) Application.Quit();
    }

    class DemoBrain : IBrain
    {
        public Vector3 Decide() => Time.time < 5f ? Vector3.forward : Vector3.zero;   // walk, then stop
    }
}
