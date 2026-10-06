using System.IO;
using UnityEditor;
using UnityEditor.SceneManagement;
using UnityEngine;
using UnityEngine.Rendering;

// Builds the art-only starting scene: ground, path, towers, gate, cover, lighting and the visual prefabs.
// No gameplay code here: the game's classes are written by hand in Assets/Scripts.
// Menu: Gauntlet > Rebuild Scene. Batch: -executeMethod GauntletSceneBuilder.Build
public static class GauntletSceneBuilder
{
    const string ScenePath = "Assets/Scenes/Gauntlet.unity";
    const string MatDir = "Assets/Art/Materials";
    const string PrefabDir = "Assets/Prefabs/Visuals";

    [MenuItem("Gauntlet/Rebuild Scene")]
    public static void Build()
    {
        var scene = EditorSceneManager.NewScene(NewSceneSetup.EmptyScene, NewSceneMode.Single);

        var grass = Mat("Grass", new Color(0.20f, 0.36f, 0.15f), 0.05f);
        var dirt = Mat("Dirt", new Color(0.45f, 0.34f, 0.23f), 0.02f);

        // ground + the path the player walks, from the start pad to the gate
        var ground = GameObject.CreatePrimitive(PrimitiveType.Plane);
        ground.name = "Ground";
        ground.transform.localScale = new Vector3(30, 1, 30);
        ground.GetComponent<Renderer>().sharedMaterial = grass;

        var path = GameObject.CreatePrimitive(PrimitiveType.Cube);
        path.name = "Path";
        path.transform.position = new Vector3(0, 0.02f, 0);
        path.transform.localScale = new Vector3(5, 0.04f, 60);
        path.GetComponent<Renderer>().sharedMaterial = dirt;

        var start = GameObject.CreatePrimitive(PrimitiveType.Cylinder);
        start.name = "StartPad";
        start.transform.position = new Vector3(0, 0.05f, -28);
        start.transform.localScale = new Vector3(4, 0.05f, 4);
        start.GetComponent<Renderer>().sharedMaterial = Mat("StartPad", new Color(0.25f, 0.45f, 0.85f), 0.3f);

        // the props made in Blender (Art-Source/build_assets.py)
        var map = new GameObject("Map");
        Place("Gate", map, new Vector3(0, 0, 30), 180);
        Place("ArcherTower", map, new Vector3(-8, 0, -14), 0, "ArcherTower_A");
        Place("ArcherTower", map, new Vector3(8, 0, -2), 0, "ArcherTower_B");
        Place("ArcherTower", map, new Vector3(-8, 0, 10), 0, "ArcherTower_C");
        Place("CannonTower", map, new Vector3(9, 0, 20), -30, "CannonTower_A");
        Place("CoverWall", map, new Vector3(1.4f, 0, -9), 0, "Cover_1");
        Place("CoverWall", map, new Vector3(-1.4f, 0, 3), 0, "Cover_2");
        Place("CoverWall", map, new Vector3(1.4f, 0, 15), 0, "Cover_3");

        // visual-only prefabs (no scripts): the player, a tower soldier, an arrow, a torch fire
        var player = SavePrefab(BuildPlayerBody(), "PlayerBody");
        var soldier = SavePrefab(BuildSoldierBody(), "TowerSoldierBody");
        SavePrefab(BuildArrow(), "Arrow");
        var fire = SavePrefab(BuildFire(), "Fire");

        Spawn(player, new Vector3(0, 0, -28), 0, "Player");
        foreach (var t in new[] { "ArcherTower_A", "ArcherTower_B", "ArcherTower_C" })
        {
            var tower = GameObject.Find(t).transform;
            Spawn(soldier, tower.position + new Vector3(0, 6.35f, 0), 180, "TowerSoldier", tower);
        }
        Spawn(fire, new Vector3(-1.9f, 4.75f, 30.85f), 0, "Fire_GateL", map.transform);
        Spawn(fire, new Vector3(1.9f, 4.75f, 30.85f), 0, "Fire_GateR", map.transform);

        // light, sky, fog, camera
        var sun = new GameObject("Sun").AddComponent<Light>();
        sun.type = LightType.Directional;
        sun.color = new Color(1f, 0.93f, 0.82f);
        sun.intensity = 1.2f;
        sun.shadows = LightShadows.Soft;
        sun.transform.rotation = Quaternion.Euler(48, -35, 0);
        RenderSettings.sun = sun;
        RenderSettings.ambientMode = AmbientMode.Trilight;
        RenderSettings.ambientSkyColor = new Color(0.62f, 0.72f, 0.88f);
        RenderSettings.ambientEquatorColor = new Color(0.52f, 0.55f, 0.50f);
        RenderSettings.ambientGroundColor = new Color(0.28f, 0.25f, 0.20f);
        RenderSettings.fog = true;
        RenderSettings.fogMode = FogMode.Linear;
        RenderSettings.fogColor = new Color(0.70f, 0.80f, 0.90f);
        RenderSettings.fogStartDistance = 35;
        RenderSettings.fogEndDistance = 110;

        var cam = new GameObject("Main Camera").AddComponent<Camera>();
        cam.tag = "MainCamera";
        cam.transform.position = new Vector3(0, 9, -40);
        cam.transform.rotation = Quaternion.Euler(20, 0, 0);
        cam.fieldOfView = 55;
        cam.gameObject.AddComponent<AudioListener>();

        EditorSceneManager.SaveScene(scene, ScenePath);
        EditorBuildSettings.scenes = new[] { new EditorBuildSettingsScene(ScenePath, true) };
        AssetDatabase.SaveAssets();
        Debug.Log("GAUNTLET: scene built at " + ScenePath);
    }

    // Batch only: renders the main camera to a PNG so the look can be checked without opening the editor.
    public static void BuildAndShoot()
    {
        Build();
        var outPath = System.Environment.GetEnvironmentVariable("GAUNTLET_SHOT") ?? "gauntlet.png";
        var cam = Camera.main;
        var rt = new RenderTexture(1280, 720, 24);
        cam.targetTexture = rt;
        cam.Render();
        RenderTexture.active = rt;
        var tex = new Texture2D(1280, 720, TextureFormat.RGB24, false);
        tex.ReadPixels(new Rect(0, 0, 1280, 720), 0, 0);
        tex.Apply();
        File.WriteAllBytes(outPath, tex.EncodeToPNG());
        cam.targetTexture = null;
        RenderTexture.active = null;
        Debug.Log("GAUNTLET: screenshot " + outPath);
    }

    static GameObject BuildPlayerBody()
    {
        var root = new GameObject("PlayerBody");
        Part(PrimitiveType.Capsule, root, new Vector3(0, 1, 0), new Vector3(0.9f, 1, 0.9f), Mat("PlayerBlue", new Color(0.18f, 0.42f, 0.95f), 0.4f));
        Part(PrimitiveType.Cube, root, new Vector3(0, 1.55f, 0.36f), new Vector3(0.62f, 0.2f, 0.22f), Mat("Visor", new Color(0.05f, 0.06f, 0.08f), 0.9f, new Color(0.1f, 0.8f, 1f) * 0.6f));
        Part(PrimitiveType.Cube, root, new Vector3(0, 1.05f, -0.45f), new Vector3(0.55f, 0.6f, 0.25f), Mat("Backpack", new Color(0.35f, 0.25f, 0.15f), 0.2f));
        Part(PrimitiveType.Sphere, root, new Vector3(0, 2.05f, 0), new Vector3(0.25f, 0.25f, 0.25f), Mat("Antenna", new Color(1f, 0.75f, 0.1f), 0.5f, new Color(1f, 0.6f, 0.1f) * 0.8f));
        return root;
    }

    static GameObject BuildSoldierBody()
    {
        var root = new GameObject("TowerSoldierBody");
        Part(PrimitiveType.Capsule, root, new Vector3(0, 0.8f, 0), new Vector3(0.7f, 0.8f, 0.7f), Mat("SoldierRed", new Color(0.75f, 0.15f, 0.12f), 0.3f));
        Part(PrimitiveType.Cylinder, root, new Vector3(0, 1.55f, 0), new Vector3(0.62f, 0.12f, 0.62f), Mat("Helmet", new Color(0.25f, 0.25f, 0.28f), 0.7f));
        Part(PrimitiveType.Cube, root, new Vector3(0, 1.3f, 0.3f), new Vector3(0.45f, 0.12f, 0.12f), Mat("Visor", Color.black, 0.9f));
        return root;
    }

    static GameObject BuildArrow()
    {
        var root = new GameObject("Arrow");   // points along +Z, 1 m long
        var wood = Mat("ArrowWood", new Color(0.55f, 0.38f, 0.2f), 0.2f);
        var shaft = Part(PrimitiveType.Cylinder, root, Vector3.zero, new Vector3(0.04f, 0.5f, 0.04f), wood);
        shaft.transform.localRotation = Quaternion.Euler(90, 0, 0);
        var head = Part(PrimitiveType.Cube, root, new Vector3(0, 0, 0.53f), new Vector3(0.09f, 0.09f, 0.12f), Mat("ArrowHead", new Color(0.7f, 0.7f, 0.75f), 0.8f));
        head.transform.localRotation = Quaternion.Euler(0, 45, 0);
        var feather = Mat("Feather", new Color(0.95f, 0.95f, 0.9f), 0.1f);
        Part(PrimitiveType.Cube, root, new Vector3(0, 0, -0.44f), new Vector3(0.01f, 0.12f, 0.16f), feather);
        Part(PrimitiveType.Cube, root, new Vector3(0, 0, -0.44f), new Vector3(0.12f, 0.01f, 0.16f), feather);
        return root;
    }

    static GameObject BuildFire()
    {
        var root = new GameObject("Fire");
        var ps = root.AddComponent<ParticleSystem>();
        var main = ps.main;
        main.startLifetime = 0.6f;
        main.startSpeed = 1.2f;
        main.startSize = new ParticleSystem.MinMaxCurve(0.15f, 0.35f);
        main.startColor = new ParticleSystem.MinMaxGradient(new Color(1f, 0.85f, 0.3f), new Color(1f, 0.35f, 0.05f));
        main.simulationSpace = ParticleSystemSimulationSpace.World;
        var emission = ps.emission;
        emission.rateOverTime = 40;
        var shape = ps.shape;
        shape.shapeType = ParticleSystemShapeType.Cone;
        shape.angle = 8;
        shape.radius = 0.08f;
        shape.rotation = new Vector3(-90, 0, 0);
        var size = ps.sizeOverLifetime;
        size.enabled = true;
        size.size = new ParticleSystem.MinMaxCurve(1, AnimationCurve.Linear(0, 1, 1, 0));
        var r = root.GetComponent<ParticleSystemRenderer>();
        r.sharedMaterial = ParticleMat();
        var light = new GameObject("Glow").AddComponent<Light>();
        light.transform.SetParent(root.transform, false);
        light.type = LightType.Point;
        light.color = new Color(1f, 0.6f, 0.25f);
        light.range = 6;
        light.intensity = 1.5f;
        return root;
    }

    // ---------- helpers ----------
    static GameObject Part(PrimitiveType type, GameObject parent, Vector3 pos, Vector3 scale, Material m)
    {
        var go = GameObject.CreatePrimitive(type);
        Object.DestroyImmediate(go.GetComponent<Collider>());   // visuals only: colliders are a design decision for the game code
        go.transform.SetParent(parent.transform, false);
        go.transform.localPosition = pos;
        go.transform.localScale = scale;
        go.GetComponent<Renderer>().sharedMaterial = m;
        return go;
    }

    static void Place(string model, GameObject parent, Vector3 pos, float yaw, string name = null)
    {
        var asset = AssetDatabase.LoadAssetAtPath<GameObject>("Assets/Art/Models/" + model + ".fbx");
        var go = (GameObject)PrefabUtility.InstantiatePrefab(asset);
        go.name = name ?? model;
        go.transform.SetParent(parent.transform, false);
        go.transform.position = pos;
        go.transform.rotation = Quaternion.Euler(0, yaw, 0);
        var b = new Bounds(pos, Vector3.zero);
        foreach (var rr in go.GetComponentsInChildren<Renderer>()) b.Encapsulate(rr.bounds);
        Debug.Log($"GAUNTLET: {go.name} size {b.size}");
    }

    static GameObject SavePrefab(GameObject go, string name)
    {
        var p = PrefabUtility.SaveAsPrefabAsset(go, $"{PrefabDir}/{name}.prefab");
        Object.DestroyImmediate(go);
        return p;
    }

    static void Spawn(GameObject prefab, Vector3 pos, float yaw, string name, Transform parent = null)
    {
        var go = (GameObject)PrefabUtility.InstantiatePrefab(prefab);
        go.name = name;
        if (parent) go.transform.SetParent(parent, true);
        go.transform.position = pos;
        go.transform.rotation = Quaternion.Euler(0, yaw, 0);
    }

    static Material Mat(string name, Color c, float smooth, Color? emission = null)
    {
        var path = $"{MatDir}/{name}.mat";
        var m = AssetDatabase.LoadAssetAtPath<Material>(path);
        if (m == null) { m = new Material(Shader.Find("Standard")); AssetDatabase.CreateAsset(m, path); }
        m.color = c;
        m.SetFloat("_Glossiness", smooth);
        if (emission.HasValue)
        {
            m.EnableKeyword("_EMISSION");
            m.SetColor("_EmissionColor", emission.Value);
        }
        EditorUtility.SetDirty(m);
        return m;
    }

    static Material ParticleMat()
    {
        var path = $"{MatDir}/FireParticle.mat";
        var m = AssetDatabase.LoadAssetAtPath<Material>(path);
        if (m == null)
        {
            m = new Material(Shader.Find("Legacy Shaders/Particles/Additive"));
            m.mainTexture = AssetDatabase.GetBuiltinExtraResource<Texture2D>("Default-Particle.psd");
            AssetDatabase.CreateAsset(m, path);
        }
        return m;
    }
}
