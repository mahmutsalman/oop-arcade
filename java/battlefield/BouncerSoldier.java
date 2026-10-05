import java.util.List;
// Walks left and right, turning around at the edges. Ignores the player.
public class BouncerSoldier extends Soldier {
    
    public BouncerSoldier(int x, int y , int width, int height){
        super(x,y,width,height);
    }

    @Override 
    public void update(Soldier player, int maxW, int maxH,List<Bullet> bullets){
        int rightEdge = maxW - this.getWidth();               // the largest x it can have (300 - 50 = 250)
        if (this.getX() == rightEdge || this.getX() == 0) {    // on an edge? turn around
            this.setDirection(this.getDirection() * -1);
        }
        this.moveX(this.getDirection(), maxW);                 // moves ITSELF (this), not the player (bug 11)
    }
}
