# HOP: a tiny Mario-style platformer, step 1: a window, the ground, and a player square.     Run: python3 main.py
# Java twin: the Swing JFrame + paintComponent from java/battlefield. C# twin: Unity's Update().
import pygame

WIDTH, HEIGHT = 800, 450          # Python: two names assigned at once (a tuple, unpacked)
SKY = (110, 170, 255)             # colours are (red, green, blue) tuples, 0..255
GROUND = (90, 60, 30)
PLAYER = (220, 40, 40)

pygame.init()
screen = pygame.display.set_mode((WIDTH, HEIGHT))
pygame.display.set_caption("Hop")
clock = pygame.time.Clock()

player = pygame.Rect(100, 330, 30, 40)   # x, y, width, height (y grows DOWN, like Swing)
ground = pygame.Rect(0, 370, WIDTH, 80)

running = True
while running:                            # THE GAME LOOP: Battlefield's Timer tick, Unity's Update, all in one loop
    for event in pygame.event.get():      # every key / mouse / window event since the last frame
        if event.type == pygame.QUIT:     # the window's close button
            running = False

    screen.fill(SKY)                      # clear: Swing's super.paintComponent(g)
    pygame.draw.rect(screen, GROUND, ground)
    pygame.draw.rect(screen, PLAYER, player)
    pygame.display.flip()                 # show the finished frame: Swing's repaint()
    clock.tick(60)                        # wait so the loop runs at most 60 times a second

pygame.quit()
