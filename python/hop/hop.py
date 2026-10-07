import pygame
import tools
pygame.init()
screen = pygame.display.set_mode((800,450))
running = True
counter = 0
clock = pygame.time.Clock()
ground = pygame.Rect(0,370,800,80)
player = pygame.Rect(100,330,30,40) 

while(running):
    for event in pygame.event.get():
        counter+=1
        print(f"test {counter}")
        if event.type == pygame.QUIT:
            running = False
    
    screen.fill((110,170,255))
    pygame.draw.rect(screen,(220,40,40),player)
    pygame.draw.rect(screen,(90,60,30),ground)
    
    pygame.display.flip()
    clock.tick(60) #frame-limit
