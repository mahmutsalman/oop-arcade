import pygame
import tools
pygame.init()
screen = pygame.display.set_mode((800,450))
running = True
counter = 0
clock = pygame.time.Clock()
player_x = 100
player_y = 330
ground = pygame.Rect(0,370,800,80)
player = pygame.Rect(player_x,player_y,30,40) 

while(running):
    for event in pygame.event.get():
        counter+=1
        print(f"test {counter}")
        if event.type == pygame.QUIT:
            running = False
    
    screen.fill((110,170,255))
    keys = pygame.key.get_pressed()
    
    if keys[pygame.K_LEFT]:
        player.x = max(0,min(player.x - 10,800 - player.width))
    if keys[pygame.K_RIGHT]:
        player.x = max(0,min(player.x + 10,800 - player.width))

    pygame.draw.rect(screen,(220,40,40),player)
    pygame.draw.rect(screen,(90,60,30),ground)
    
    pygame.display.flip()
    clock.tick(60) #frame-limit
