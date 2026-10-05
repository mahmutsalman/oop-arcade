import java.awt.*;
// A bullet is NOT a soldier (his call), so it is its own class. moveX / moveY are COPIED from Soldier on purpose,
// to see what copying costs (inheritance experiment).
public class Bullet {
    private boolean visible = true;
    private Color color = Color.BLACK;
    private int x;
    private int y;
    private int width = 10;
    private int height = 10;
    private int amount = 10;                            // step size in pixels per move

    public Bullet(int x, int y){
        this.x = x;
        this.y = y;
    }
    // COPIED from Soldier: move one step in direction dir (-1 / +1), clamped inside 0..boundary
    public void moveX(int dir,int dimensionBoundryX){
        this.x = Math.max(0,Math.min(this.x+dir*this.amount,dimensionBoundryX-this.width));
    }
    public void moveY(int dir,int dimensionBoundryY){
        this.y = Math.max(0,Math.min(this.y+dir*this.amount,dimensionBoundryY-this.height));
    }

    public boolean isVisible() { return visible; }
    public void setVisible(boolean visible) { this.visible = visible; }
    public Color getColor() { return color; }
    public int getX() { return x; }
    public int getY() { return y; }
    public int getWidth() { return width; }
    public int getHeight() { return height; }
}
