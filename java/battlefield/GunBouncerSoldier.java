import java.util.List;
import java.util.ArrayList;
// A bouncer that will also shoot. Extends BouncerSoldier (it IS a bouncer), so the bounce logic is inherited, not copied.
public class GunBouncerSoldier extends BouncerSoldier {
    private int counter = 0; // 
    List<Bullet> bullets = new ArrayList<>();
    public GunBouncerSoldier(int x, int y , int width, int height){
        super(x,y,width,height);
    }
    @Override 
    public void update(Soldier player,int maxW,int maxH){
        counter+=1;
        if(isDetected(player) ){//&& counter >= 5
            System.out.println("Bullet created");
           bullets.add(new Bullet(this.getX(),this.getY(),this.getDirection()));
           this.counter = 0;
        }
        int rightEdge = maxW - this.getWidth();               // the largest x it can have (300 - 50 = 250)
        if (this.getX() == rightEdge || this.getX() == 0) {    // on an edge? turn around
            this.setDirection(this.getDirection() * -1);
        }
        this.moveX(this.getDirection(), maxW);     
        //move existing bullets
        if(!bullets.isEmpty()){
            for(Bullet bullet : bullets){
                //after a bullet goes beyond limits we can remove it.
                if(bullet.getX()>maxW){
                    bullets.remove(bullet);
                }
                bullet.moveX(maxW);
            }
        }
    }
    private boolean isDetected(Soldier player){
        if(Math.abs(player.getY()-this.getY())<=player.getHeight()){
            return true;
        }
        else{
            return false;
        }
    }
}
