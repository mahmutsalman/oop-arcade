# OOP Arcade

Small games built to practise object-oriented design, SOLID, design patterns and algorithmic thinking, one step at a
time. Each game stays small on purpose: one new idea per commit, so the history shows how the design grew.

The same ideas are rebuilt in several languages.

![Battlefield gameplay](java/battlefield/images/battlefield.gif)

| game | language | what it practises | state |
|---|---|---|---|
| [Battlefield](java/battlefield) | Java (Swing) · [Python (tkinter)](python/battlefield) | encapsulation, inheritance, polymorphism, a game loop, projectiles; next: inheritance vs composition | in progress |
| [Gauntlet](csharp/gauntlet) | C# (Unity) | composition, SOLID, patterns, algorithms as real game mechanics | started |
| [Hop](python/hop) | Python (pygame) | a Mario-style platformer: Python essentials, then classes, inheritance vs composition | started |

## Remakes with a twist
Many of these games are small remakes of well-known classics (a platformer, a tower defense, ...), drawn with plain
shapes and no original assets, then changed with new features. A known game means the rules are clear, so the focus
stays on the code: OOP, SOLID, patterns, and an algorithm behind every new feature.

## Layout
```
java/         Java games
csharp/       C# games
python/       Python games
javascript/   JavaScript games
go/           Go games
```

Every game folder has its own README with what it is, what it practises and how to run it.
To see the history of one game only: `git log --oneline -- java/battlefield`.

## Commit conventions
- Subject: `<game>: what changed`, e.g. `gauntlet: the camera follows the player`.
- Body: one trailer line naming the ideas the commit practises, so they can be searched later:
  ```
  Concepts: composition, isp, srp, ioc, strategy, frame-rate, greedy
  ```
- Find every commit about an idea: `git log --oneline -i --grep "Concepts:.*composition"`
- Common tags: `oop` · `inheritance` · `polymorphism` · `composition` · `srp` `ocp` `lsp` `isp` `dip` (SOLID) ·
  `ioc` · `pattern-<name>` (strategy, observer, pool...) · `algo-<name>` (greedy, bfs, heap, sliding-window...) ·
  `perf` · `unity` · `bugfix`.
