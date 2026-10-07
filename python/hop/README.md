# Hop (Python, pygame)

A tiny Mario-style platformer, rebuilt from scratch with plain shapes (no original assets), step by step, to learn
Python and practise OOP, SOLID and algorithms. Each step uses one Python essential.

## Run
```
python3 main.py
```
Requires Python 3 and pygame (`pip install pygame`).

## Steps
| # | feature | the Python it practises |
|---|---|---|
| 1 | a window, the ground, a player square | a `while` loop, tuples, calling functions |
| 2 | ← → move the player | `if`, key state, `+=` |
| 3 | jump + gravity | variables that change every frame, a function with `return` |
| 4 | several platforms | a `list`: append, index `[0]` / `[-1]`, `for` loops, `len` |
| 5 | the player stands on platforms | a function returning a `bool`, collision |
| 6 | coins to collect | removing from a list while looping (the safe way), a ternary `x if cond else y` |
| 7 | classes: `Player`, `Platform`, `Coin` | `class`, `__init__`, `self`, methods |
| 8 | enemies that walk and turn | inheritance, then composition (the Battlefield lesson again) |
| 9 | score and best scores | `dict`, `sorted`, `heapq` (top scores) |
| 10 | an undo / respawn stack, a spawn queue | `list` as a stack, `collections.deque` as a queue |
