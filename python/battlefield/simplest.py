# THE SIMPLEST GAME: press UP -> a square appears. Press DOWN -> it disappears.     Run: python3 simplest.py
# Python port of java/battlefield, step 1. tkinter ships with Python (no install), like Swing ships with Java.
import tkinter as tk


class Simplest:
    def __init__(self, root):                    # Java: the constructor Simplest()
        self.visible = False                     # the whole "game state": one True/False
        self.canvas = tk.Canvas(root, width=300, height=300, bg="white")   # Java: JPanel + setPreferredSize
        self.canvas.pack()
        root.bind("<KeyPress>", self.key_pressed)  # Java: addKeyListener(this): "tkinter, call MY key_pressed"

    def key_pressed(self, event):                # tkinter calls this (we never call it ourselves)
        if event.keysym == "Up":
            self.visible = True
        if event.keysym == "Down":
            self.visible = False
        self.draw()                              # Java: repaint() (here we draw directly)

    def draw(self):                              # Java: paintComponent(Graphics g)
        self.canvas.delete("all")                # Java: super.paintComponent(g) clears the panel
        if self.visible:
            self.canvas.create_rectangle(125, 125, 175, 175, fill="blue", outline="")   # x1, y1, x2, y2 (not width/height)


if __name__ == "__main__":                       # Java: public static void main
    root = tk.Tk()
    root.title("Simplest")
    Simplest(root)
    root.mainloop()                              # hands control to tkinter: it waits for keys and calls us
