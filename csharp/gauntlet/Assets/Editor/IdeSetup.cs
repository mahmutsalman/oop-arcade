using Unity.CodeEditor;
using UnityEditor;

// Batch: -executeMethod IdeSetup.UseVSCode  → sets VS Code as the external editor and writes the .csproj/.sln files.
public static class IdeSetup
{
    public static void UseVSCode()
    {
        CodeEditor.SetExternalScriptEditor("/Applications/Visual Studio Code.app");
        CodeEditor.CurrentEditor.SyncAll();
        UnityEngine.Debug.Log("GAUNTLET: external editor = " + CodeEditor.CurrentEditorPath);
    }
}
