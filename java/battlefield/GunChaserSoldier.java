import java.util.List;

public class GunChaserSoldier extends Soldier{
    private final int normalStep = 1;   

    public GunChaserSoldier(int x, int y , int width, int height){
        super(x,y,width,height);
    }
    
    @Override 
    public void update(Soldier player,int maxW,int maxH, List<Bullet> bullets){
        int dx = player.getX() - this.getX();                    // + = the player is to the right
        int dy = player.getY() - this.getY();                    // + = the player is below

        if (Math.abs(dx) >= Math.abs(dy)) {                     // close the bigger gap first
            setAmount(Math.min(normalStep, Math.abs(dx)));      // arrive: never step past the player
            moveX(Integer.signum(dx), maxW);
        } else {
            setAmount(Math.min(normalStep, Math.abs(dy)));
            moveY(Integer.signum(dy), maxH);
        }
        setAmount(normalStep);
        if (isDetected(player) ) {
            int[] dir = {sideOf(player), 0};                  // horizontal only: toward the player's side
            bullets.add(new Bullet(this.getX(), this.getY(), dir));
        }   
    }
     // true when the player is on (roughly) the same row
    private boolean isDetected(Soldier player){
        return Math.abs(player.getY() - this.getY()) <= player.getHeight();
    }
      // -1 = the player is to the left, 1 = to the right, 0 = same x
    private int sideOf(Soldier player){
        return Integer.signum(player.getX() - this.getX());
    }
}
