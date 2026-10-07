import pygame

pygame.init()
screen = pygame.display.set_mode((800,450))
running = True
counter = 0
while(running):
    for event in pygame.event.get():
        counter+=1
        print(f"test {counter}")
        if event.type == pygame.QUIT:
            running = False
    screen.fill((110,170,255))
    pygame.display.flip()
