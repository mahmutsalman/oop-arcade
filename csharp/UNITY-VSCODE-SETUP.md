# Connecting VS Code to Unity (macOS)

A checklist to make VS Code the code editor for a Unity project: autocomplete, error squiggles, "go to definition",
double-click a script in Unity to open it in VS Code, and debugging.
Tested with Unity 6 (6000.4.2f1), VS Code, the Unity extension 1.3.2, C# Dev Kit 3.40, .NET SDK 10.

## What connects to what
```
Unity  ──(package: Visual Studio Editor)──►  writes .csproj / .sln(x) files  ──►  VS Code (C# Dev Kit) reads them
Unity  ◄──(VS Code extension: Unity)──────  debugger attaches to the running editor
```
Without the package, Unity never writes the project files, and VS Code shows your C# as plain text.

## 1. Once per computer
1. **.NET SDK**: check with `dotnet --version`. Missing → `brew install dotnet` (or the installer from dot.net).
2. **VS Code extension "Unity"** (publisher: Microsoft, id `visualstudiotoolsforunity.vstuc`). It also installs
   **C#** and **C# Dev Kit**. From a terminal: `code --install-extension visualstudiotoolsforunity.vstuc`
3. C# Dev Kit may ask you to sign in with a Microsoft account; it is free for individual use.

## 2. Once per Unity project
1. **Add the package**: Unity → Window → Package Manager → Unity Registry → **Visual Studio Editor** → Install.
   (Or add `"com.unity.ide.visualstudio": "2.0.27"` to `Packages/manifest.json`; use the version your Unity offers.)
2. **Pick VS Code**: Unity → Settings (macOS: Unity → Settings…) → **External Tools** → External Script Editor →
   **Visual Studio Code**. If it is not in the list, choose Browse… and pick `/Applications/Visual Studio Code.app`.
3. In the same panel, click **Regenerate project files**.
4. In VS Code: File → Open Folder → the **project root** (the folder that has `Assets/`, `Packages/`,
   `ProjectSettings/`), not `Assets/`.

## 3. Check it works
- [ ] The project root now has `Assembly-CSharp.csproj` (appears after the first script exists) and a `.sln`/`.slnx`.
- [ ] In a script, type `transform.` → a list of members appears.
- [ ] Type a wrong name → a red squiggle appears.
- [ ] Double-click a script in Unity's Project window → it opens in VS Code.
- [ ] Debugging: in VS Code, Run and Debug → **Attach to Unity**, set a breakpoint, press Play in Unity → it stops there.

## 4. When something is wrong
| symptom | fix |
|---|---|
| no autocomplete, everything white | Unity: External Tools → Regenerate project files; then in VS Code run "Developer: Reload Window" |
| only `Assembly-CSharp-Editor.csproj` exists | normal until you create the first script outside `Assets/Editor/`; create it in Unity and the file appears |
| VS Code opened `Assets/` | reopen the project ROOT folder |
| double-click opens another editor | External Tools → External Script Editor is not VS Code; set it again |
| "project system" errors from C# Dev Kit | check `dotnet --version`, then reload the window |
| autocomplete broke after adding a package | Regenerate project files again |

## 5. The same setup from the command line (what was used for `gauntlet`)
```bash
code --install-extension visualstudiotoolsforunity.vstuc
# add "com.unity.ide.visualstudio": "2.0.27" to Packages/manifest.json, then let Unity set the editor + write the files:
/Applications/Unity/Hub/Editor/<version>/Unity.app/Contents/MacOS/Unity -batchmode -quit \
  -projectPath "$PWD" -executeMethod IdeSetup.UseVSCode -logFile -
```
`IdeSetup.UseVSCode` lives in `gauntlet/Assets/Editor/IdeSetup.cs`: it calls
`CodeEditor.SetExternalScriptEditor("/Applications/Visual Studio Code.app")` and `CodeEditor.CurrentEditor.SyncAll()`.
Copy that file into any new Unity project's `Assets/Editor/` to reuse it.

## 6. New scripts and IntelliSense without opening Unity
Unity's generated `Assembly-CSharp.csproj` lists every script file **by name**, and only Unity rewrites it. If you never
open Unity, a NEW `.cs` file is missing from that list, so VS Code treats it as a loose file: the Output panel shows
`FileBasedProgramsProjectSystem` / `Canonical.csproj`, and `MonoBehaviour`, `Debug`, `transform` are red.
Fix used in `gauntlet`: **`bin/sync-csproj`** (a small Python script) rewrites that list from `Assets/**/*.cs` (outside
`Editor/` folders) and adds the project to the `.slnx`. `bin/play` runs it on every play, and there is a VS Code task:
Cmd+Shift+P → Run Task → **Sync C# project (IntelliSense)**. After it runs: Cmd+Shift+P → **Developer: Reload Window**.

## 7. Git
The generated `*.csproj`, `*.sln`, `*.slnx`, `Library/`, `Temp/`, `Logs/` and `UserSettings/` are machine-specific:
keep them in `.gitignore` (see `gauntlet/.gitignore`). Commit `Assets/`, `Packages/` and `ProjectSettings/`.
