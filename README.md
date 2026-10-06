# OOP Arcade

Small games built to practise object-oriented design, SOLID, design patterns and algorithmic thinking, one step at a
time. Each game stays small on purpose: one new idea per commit, so the history shows how the design grew.

The same ideas are rebuilt in several languages.

![Battlefield gameplay](java/battlefield/images/battlefield.gif)

| game | language | what it practises | state |
|---|---|---|---|
| [Battlefield](java/battlefield) | Java (Swing) · [Python (tkinter)](python/battlefield) | encapsulation, inheritance, polymorphism, a game loop, projectiles; next: inheritance vs composition | in progress |
| [Gauntlet](csharp/gauntlet) | C# (Unity) | composition, SOLID, patterns, algorithms as real game mechanics | started |

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
