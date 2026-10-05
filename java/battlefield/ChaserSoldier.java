import java.util.List;
// Walks toward the player, one step per tick, along the axis with the bigger gap.
public class ChaserSoldier extends Soldier {
    public ChaserSoldier(int x, int y , int width, int height){
        super(x,y,width,height);
    }
    int normal = super.getAmount();                       // the normal step, kept so a shorter "arrive" step can be undone
    @Override 
    public void update(Soldier player, int maxW, int maxH, List<Bullet> bullets){
        int dx = player.getX() - this.getX();                    // + = the player is to the right
        int dy = player.getY() - this.getY();                    // + = the player is below
        
        if (Math.abs(dx) >= Math.abs(dy)) {                     // close the bigger gap first
            if(Math.abs(dx) < this.getAmount() ){               // closer than one step: take only the remaining gap
                this.setAmount(Math.abs(dx));
            }
            this.moveX(Integer.signum(dx), maxW);
        } else {
            if(Math.abs(dy) < this.getAmount() ){               // same on the y axis
                this.setAmount(Math.abs(dy));
            }
            this.moveY(Integer.signum(dy), maxH);
        }
        this.setAmount(normal);                                // restore the normal step (a changed field persists)
    }
}
