import java.awt.*;
// A projectile fired by a soldier. Not a Soldier subclass: it shares no behaviour with one.
// moveX / moveY are copied from Soldier for now.
public class Bullet {
    private boolean visible = true;
    private Color color = Color.BLACK;
    private int x;
    private int y;
    private int width = 10;
    private int height = 10;
    private int amount = 10;                            // step size in pixels per move
    private int dir = 1;

    public Bullet(int x, int y, int dir){
        this.x = x;
        this.y = y;
        this.dir = dir;
    }
    // COPIED from Soldier: move one step in direction dir (-1 / +1), clamped inside 0..boundary
    public void moveX(int dir,int dimensionBoundryX){
        this.x = Math.max(0,Math.min(this.x+dir*this.amount,dimensionBoundryX-this.width));
    }
    public void moveY(int dir,int dimensionBoundryY){
        this.y = Math.max(0,Math.min(this.y+dir*this.amount,dimensionBoundryY-this.height));
    }
    public void moveX(int dimensionBoundryX){
        this.x = Math.max(0,Math.min(this.x+this.dir*this.amount,dimensionBoundryX-this.width));
    }
    public void moveY(int dimensionBoundryY){
        this.y = Math.max(0,Math.min(this.y+this.dir*this.amount,dimensionBoundryY-this.height));
    }

    public boolean isVisible() { return visible; }
    public void setVisible(boolean visible) { this.visible = visible; }
    public Color getColor() { return color; }
    public int getX() { return x; }
    public int getY() { return y; }
    public int getWidth() { return width; }
    public int getHeight() { return height; }
    public int getDir() { return dir; }
}
