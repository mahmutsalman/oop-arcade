import java.util.List;
// A bouncer that also shoots when the player is on its row. Creates bullets and hands them to the game's list.
public class GunBouncerSoldier extends BouncerSoldier {
    private static final int TICKS_BETWEEN_SHOTS = 5;        // the timer ticks every 100 ms, so 5 ticks = one shot per 0.5 s
    private int counter = 0;                                  // ticks since the last shot

    public GunBouncerSoldier(int x, int y , int width, int height){
        super(x,y,width,height);
    }
    @Override 
    public void update(Soldier player,int maxW,int maxH,List<Bullet> bullets){
        counter++;
        if (isDetected(player) && counter >= TICKS_BETWEEN_SHOTS) {
            int[] dir = {sideOf(player), 0};                  // horizontal only: toward the player's side
            bullets.add(new Bullet(this.getX(), this.getY(), dir));
            counter = 0;
        }
        super.update(player, maxW, maxH, bullets);            // the bounce itself is inherited from BouncerSoldier
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
