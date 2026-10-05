import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Soldier {
    private boolean visible = true;                     // the whole "game state": one true/false
    private Color color = Color.BLUE;
    private int x=125;
    private int y=125;
    private int width = 50;
    private int height = 50;
    private int amount = 10;
    private int direction = 1;
    private String type = "player";                   // ⚠️ the BAD-ON-PURPOSE version: "player" / "bouncer" / "chaser" as a String                           // remembered between ticks: +1 = right, -1 = left (his "register")

    public Soldier(){

    }
    public Soldier(int x, int y , int width, int height){
        this.x = x;
        this.y= y;
        this.width = width;
        this.height= height;
    }

    public void moveX(int dir,int dimensionBoundryX){
        this.x = Math.max(0,Math.min(this.x+dir*this.amount,dimensionBoundryX-this.width));
    }
    public void moveY(int dir,int dimensionBoundryY){
        this.y = Math.max(0,Math.min(this.y+dir*this.amount,dimensionBoundryY-this.height));
    }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public int getDirection() { return direction; }
    public void setDirection(int direction) { this.direction = direction; }

    public boolean isVisible() { return visible; }          // boolean getters are usually named isX()
    public void setVisible(boolean visible) { this.visible = visible; }

    public Color getColor() { return color; }
    public void setColor(Color color) { this.color = color; }

    public int getX() { return x; }
    public void setX(int x) { this.x = x; }

    public int getY() { return y; }
    public void setY(int y) { this.y = y; }

    public int getWidth() { return width; }
    

    public int getHeight() { return height; }
    

}
