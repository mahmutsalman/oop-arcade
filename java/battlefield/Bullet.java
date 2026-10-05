import java.awt.*;
// A projectile fired by a soldier. Not a Soldier subclass: it shares no behaviour with one.
// It knows its own direction (set once, at birth), so the game only has to call move().
public class Bullet {
    private boolean visible = true;
    private Color color = Color.BLACK;
    private int x;
    private int y;
    private int width = 10;
    private int height = 10;
    private int amount = 10;                            // step size in pixels per move
    private int[] dir;                                  // {dx, dy}: {1, 0} = right, {0, -1} = up; both are applied on every move

    public Bullet(int x, int y, int[] dir){
        this.x = x;
        this.y = y;
        this.dir = dir;
    }
    
    // one step along dir; a 0 leaves that axis unchanged, so one method covers every direction (no if)
    public void move(int dimensionBoundryX,int dimensionBoundryY){ 
        this.x = Math.max(-this.width,Math.min(this.x + dir[0]*this.amount,dimensionBoundryX));
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
    // answers a question about itself; removing it from the list is the owner's job (Simplest)
    public boolean isOut(int maxW) { return x >= maxW || x <= 0 ; }
}
