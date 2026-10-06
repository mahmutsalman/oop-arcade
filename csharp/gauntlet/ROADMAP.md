# Gauntlet roadmap

The order the game grows in. Each step is small, hand-written, and practises one idea (design or algorithm).
✅ done · ▶️ next · ⬜ later

## 1. The player (composition basics)
- ✅ The art scene: path, towers, gate, cover walls, look-only prefabs
- ✅ `Game` = the composition root (attaches and wires everything)
- ✅ WASD walking, frame-rate independent (speed × deltaTime), diagonals normalized
- ✅ Input split from movement (`ReadDirection` vs `Move`)
- ✅ The camera follows any `Transform` (share the part, not the whole object)
- ✅ `IMovement`: `Move` (walk) and `Sprint` (Shift); the player picks the object, then makes one call
- ⬜ `Sprint.Multiplier` as a property with a guard

## 2. Body + brain (one class for every character)
- ▶️ `IBrain` decides the direction; `KeyboardBrain` (the player's keys move out of Player)
- ⬜ `Character` (the body) HAS an `IMovement` and an `IBrain`; `Init(...)` wires them (MonoBehaviours have no constructor)
- ⬜ The player becomes `Character` + `KeyboardBrain`

## 3. Enemies
- ⬜ A ground enemy = `Character` + `ChaseBrain` (greedy: close the bigger gap; same as Battlefield's chaser)
- ⬜ More obstacles on the map, so the greedy chaser gets stuck (feel where greedy breaks)
- ⬜ Pathfinding around obstacles on a grid: BFS, then A* (Walls and Gates, Network Delay Time)
- ⬜ Tower enemies that can jump down and become ground enemies (swap the brain at runtime)

## 4. Weapons
- ⬜ `IWeapon` + arrows as objects; a bow for tower soldiers
- ⬜ Different guns (crossbow, cannon / artillery) = different `IWeapon` classes, no new character classes
- ⬜ Taking cover behind walls (line of sight)
- ⬜ An object pool for arrows (no new objects every shot)

## 5. Algorithms as mechanics
| mechanic | algorithm (NeetCode) |
|---|---|
| tower reload cooldowns, the strongest ready tower fires | Task Scheduler (heap + queue) |
| tower picks a target | K Closest Points to Origin (heap) |
| towers punish a player repeating the same moves | Longest Substring Without Repeating Characters (sliding window) |
| fast arrow hit checks | Valid Sudoku's cell key → spatial grid |
| enemy reaches you around walls | BFS / A* (Walls and Gates, Network Delay Time) |
| can the player still reach the gate? | Jump Game / BFS reachability |
| power-ups ordered by value | heap |
| more ideas for all 150 problems | [Docs/algorithms-in-the-real-world.html](Docs/algorithms-in-the-real-world.html) |

## 6. Stream game (later)
- ⬜ Viewers' chat commands: spawn enemies, give the player a gun, more armor (a `ChatBrain` / commands into `Game`)
- ⬜ Connect to the KinesinReef stream overlay
