using System.Text.RegularExpressions;
using UnityEditor;

// Unity lists every script by name in Assembly-CSharp.csproj, so a NEW file is invisible to VS Code until Unity runs again.
// This rewrites the list as one wildcard, so VS Code sees new scripts the moment they are saved.
public class CsprojGlob : AssetPostprocessor
{
    static string OnGeneratedCSProject(string path, string content)
    {
        if (!path.EndsWith("Assembly-CSharp.csproj")) return content;
        content = Regex.Replace(content, @"[ \t]*<Compile Include=""Assets/[^""]*"" />\r?\n", "");
        return content.Replace("</Project>",
            "  <ItemGroup>\n    <Compile Include=\"Assets/**/*.cs\" Exclude=\"Assets/**/Editor/**\" />\n  </ItemGroup>\n</Project>");
    }
}
