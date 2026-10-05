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
    private int[] dir = new int[]{1,1};

    public Bullet(int x, int y, int[] dir){
        this.x = x;
        this.y = y;
        this.dir = dir;
    }
    
    public void move(int dimensionBoundryX,int dimensionBoundryY){ 
        this.x = Math.max(0,Math.min(this.x + dir[0]*this.amount,dimensionBoundryX));
        this.y = Math.max(0,Math.min(this.y + dir[1]*this.amount,dimensionBoundryY));
    }

    public boolean isVisible() { return visible; }
    public void setVisible(boolean visible) { this.visible = visible; }
    public Color getColor() { return color; }
    public int getX() { return x; }
    public int getY() { return y; }
    public int getWidth() { return width; }
    public int getHeight() { return height; }
    public int[] getDir() { return dir; }
}
