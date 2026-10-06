# Builds Gauntlet's low-poly props in Blender and exports each one as an FBX for Unity.
# Run: Blender -b -P build_assets.py -- <out_dir>
import bpy, bmesh, math, sys, os

OUT = sys.argv[sys.argv.index("--") + 1] if "--" in sys.argv else os.path.dirname(__file__)
os.makedirs(OUT, exist_ok=True)

_mats = {}
def reset():
    bpy.ops.wm.read_factory_settings(use_empty=True)
    _mats.clear()

def mat(name, rgb, rough=0.85, metal=0.0, emit=None):
    if name in _mats:
        return _mats[name]
    m = bpy.data.materials.new(name)
    m.use_nodes = True
    bsdf = next(n for n in m.node_tree.nodes if n.type == "BSDF_PRINCIPLED")
    bsdf.inputs["Base Color"].default_value = (*rgb, 1)
    bsdf.inputs["Roughness"].default_value = rough
    bsdf.inputs["Metallic"].default_value = metal
    if emit:
        bsdf.inputs["Emission Color"].default_value = (*emit, 1)
        bsdf.inputs["Emission Strength"].default_value = 3.0
    m.diffuse_color = (*rgb, 1)
    _mats[name] = m
    return m

def finish(obj, material, name):
    obj.name = name
    obj.data.materials.clear()
    obj.data.materials.append(material)
    for p in obj.data.polygons:
        p.use_smooth = False
    return obj

def cyl(name, r1, r2, depth, z, verts, material, loc=(0, 0)):
    bpy.ops.mesh.primitive_cone_add(vertices=verts, radius1=r1, radius2=r2, depth=depth,
                                    location=(loc[0], loc[1], z + depth / 2))
    return finish(bpy.context.active_object, material, name)

def box(name, size, loc, material, rot=(0, 0, 0)):
    bpy.ops.mesh.primitive_cube_add(size=1, location=loc, rotation=rot)
    o = bpy.context.active_object
    o.scale = size
    bpy.ops.object.transform_apply(scale=True, rotation=True)
    return finish(o, material, name)

def join_all(name):
    objs = [o for o in bpy.context.scene.objects if o.type == "MESH"]
    bpy.ops.object.select_all(action="DESELECT")
    for o in objs:
        o.select_set(True)
    bpy.context.view_layer.objects.active = objs[0]
    bpy.ops.object.join()
    o = bpy.context.active_object
    o.name = name
    bpy.context.scene.cursor.location = (0, 0, 0)
    bpy.ops.object.origin_set(type="ORIGIN_CURSOR")
    return o

def export(name):
    bpy.ops.wm.save_as_mainfile(filepath=os.path.join(OUT, name + ".blend"))
    bpy.ops.export_scene.fbx(filepath=os.path.join(OUT, name + ".fbx"), use_selection=False,
                             apply_scale_options="FBX_SCALE_UNITS", axis_forward="-Z", axis_up="Y",
                             bake_space_transform=True, mesh_smooth_type="FACE", add_leaf_bones=False)
    print("EXPORTED", name)

STONE = (0.55, 0.53, 0.50); STONE_DARK = (0.36, 0.35, 0.34); WOOD = (0.42, 0.27, 0.15)
ROOF = (0.62, 0.16, 0.12); BANNER = (0.85, 0.65, 0.10); IRON = (0.18, 0.18, 0.20)

# ---------- ARCHER TOWER: tall, octagonal, crenellated, red roof, banner ----------
reset()
s, sd, w, r, b = mat("Stone", STONE), mat("StoneDark", STONE_DARK), mat("Wood", WOOD), mat("Roof", ROOF), mat("Banner", BANNER, 0.6)
cyl("Base", 1.9, 1.7, 0.6, 0.0, 8, sd)
cyl("Body", 1.6, 1.35, 5.4, 0.6, 8, s)
for i, z in enumerate((2.2, 4.0)):
    cyl(f"Band{i}", 1.55 - i * 0.12, 1.5 - i * 0.12, 0.18, z, 8, sd)
cyl("Platform", 1.85, 1.85, 0.35, 6.0, 8, sd)
for i in range(8):
    a = i * math.tau / 8
    box(f"Merlon{i}", (0.55, 0.32, 0.55), (1.62 * math.cos(a), 1.62 * math.sin(a), 6.62), s, (0, 0, a))
for i in range(4):
    a = math.pi / 4 + i * math.tau / 4
    cyl(f"Post{i}", 0.09, 0.09, 1.7, 6.35, 6, w, (1.2 * math.cos(a), 1.2 * math.sin(a)))
cyl("Roof", 2.1, 0.0, 1.9, 8.0, 8, r)
cyl("Pole", 0.05, 0.05, 1.4, 9.7, 6, w)
box("Flag", (0.04, 0.9, 0.5), (0, 0.48, 10.75), b)
for i in range(3):  # arrow slits
    a = i * math.tau / 3
    box(f"Slit{i}", (0.12, 0.12, 0.7), (1.47 * math.cos(a), 1.47 * math.sin(a), 3.2), mat("Slit", (0.05, 0.05, 0.06)), (0, 0, a))
join_all("ArcherTower"); export("ArcherTower")

# ---------- CANNON TOWER: squat heavy artillery ----------
reset()
s, sd, w, i_ = mat("Stone", STONE), mat("StoneDark", STONE_DARK), mat("Wood", WOOD), mat("Iron", IRON, 0.4, 0.8)
cyl("Base", 2.6, 2.3, 2.6, 0.0, 10, sd)
cyl("Ring", 2.45, 2.45, 0.3, 2.6, 10, s)
for k in range(10):
    a = k * math.tau / 10
    box(f"Merlon{k}", (0.6, 0.4, 0.5), (2.2 * math.cos(a), 2.2 * math.sin(a), 3.15), s, (0, 0, a))
box("Carriage", (1.2, 1.0, 0.5), (0, 0, 3.15), w)
cyl("WheelL", 0.45, 0.45, 0.15, 0, 10, w, (0, 0))
wl = bpy.context.active_object; wl.rotation_euler = (math.pi / 2, 0, 0); wl.location = (0, 0.6, 3.2)
cyl("WheelR", 0.45, 0.45, 0.15, 0, 10, w)
wr = bpy.context.active_object; wr.rotation_euler = (math.pi / 2, 0, 0); wr.location = (0, -0.6, 3.2)
cyl("Barrel", 0.32, 0.24, 2.4, 0, 12, i_)
br = bpy.context.active_object; br.rotation_euler = (0, math.radians(80), 0); br.location = (0.2, 0, 3.7)
bpy.ops.object.select_all(action="SELECT"); bpy.ops.object.transform_apply(location=False, rotation=True, scale=True)
join_all("CannonTower"); export("CannonTower")

# ---------- GATE: the goal at the end of the map ----------
reset()
s, sd, w, i_, glow = mat("Stone", STONE), mat("StoneDark", STONE_DARK), mat("Wood", WOOD), mat("Iron", IRON, 0.4, 0.8), mat("Glow", (1.0, 0.75, 0.3), 0.5, 0, (1.0, 0.6, 0.2))
for x in (-2.6, 2.6):
    box(f"Pillar{x}", (1.4, 1.4, 6.0), (x, 0, 3.0), s)
    box(f"Cap{x}", (1.7, 1.7, 0.4), (x, 0, 6.2), sd)
box("Lintel", (6.6, 1.2, 1.1), (0, 0, 5.6), sd)
box("DoorL", (1.75, 0.3, 4.6), (-0.95, 0, 2.3), w)
box("DoorR", (1.75, 0.3, 4.6), (0.95, 0, 2.3), w)
for z in (1.2, 3.4):
    box(f"Strap{z}", (3.7, 0.36, 0.18), (0, 0, z), i_)
for x in (-1.9, 1.9):
    box(f"Torch{x}", (0.18, 0.18, 0.7), (x * 1.0, -0.85, 4.2), w)
    box(f"Flame{x}", (0.28, 0.28, 0.32), (x * 1.0, -0.85, 4.7), glow)
join_all("Gate"); export("Gate")

# ---------- COVER WALL: low stone wall to hide behind ----------
reset()
s, sd = mat("Stone", STONE), mat("StoneDark", STONE_DARK)
for k, (x, h) in enumerate(((-1.2, 1.2), (0.0, 1.35), (1.2, 1.15))):
    box(f"Block{k}", (1.15, 0.8, h), (x, 0, h / 2), s if k != 1 else sd)
join_all("CoverWall"); export("CoverWall")
print("DONE")
