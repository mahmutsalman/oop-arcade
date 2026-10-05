import java.util.List;
// Walks toward the player, one step per tick, along the axis with the bigger gap.
public class ChaserSoldier extends Soldier {
    private final int normalStep = getAmount();               // the usual step, restored after a shorter "arrive" step

    public ChaserSoldier(int x, int y , int width, int height){
        super(x,y,width,height);
    }
    @Override 
    public void update(Soldier player, int maxW, int maxH, List<Bullet> bullets){
        int dx = player.getX() - this.getX();                    // + = the player is to the right
        int dy = player.getY() - this.getY();                    // + = the player is below

        if (Math.abs(dx) >= Math.abs(dy)) {                     // close the bigger gap first
            setAmount(Math.min(normalStep, Math.abs(dx)));      // arrive: never step past the player
            moveX(Integer.signum(dx), maxW);
        } else {
            setAmount(Math.min(normalStep, Math.abs(dy)));
            moveY(Integer.signum(dy), maxH);
        }
        setAmount(normalStep);                                  // a changed field persists, so put the step back
    }
}
