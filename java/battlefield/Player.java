import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Player {
    private boolean visible = true;                     // the whole "game state": one true/false
    private Color color = Color.BLUE;
    private int x=125;
    private int y=125;
    private int width = 50;
    private int height = 50;

    public Player(){

    }
    public Player(int x, int y , int width, int height){
        this.x = x;
        this.y= y;
        this.width = width;
        this.height= height;
    }

    public boolean isVisible() { return visible; }          // boolean getters are usually named isX()
    public void setVisible(boolean visible) { this.visible = visible; }

    public Color getColor() { return color; }
    public void setColor(Color color) { this.color = color; }

    public int getX() { return x; }
    public void setX(int x) { this.x = x; }

    public int getY() { return y; }
    public void setY(int y) { this.y = y; }

    public int getWidth() { return width; }
    public void setWidth(int width) { this.width = width; }

    public int getHeight() { return height; }
    public void setHeight(int height) { this.height = height; }

}
