// Walks toward the player, one step per tick, along the axis with the bigger gap.
public class ChaserSoldier extends Soldier {
    public ChaserSoldier(int x, int y , int width, int height){
        super(x,y,width,height);
    }
    int normal = super.getAmount();                       // the normal step, saved so a short "arrive" step can be undone (bug 13)
    @Override 
    public void update(Soldier player, int maxW, int maxH){
        // DEBUG log (bug 12 was found with it); remove or turn into a real logger later
        System.out.println("chaser x=" + getX() + "  me x=" + player.getX() + "  dx=" + (player.getX() - getX()));
        int dx = player.getX() - this.getX();                    // + = I'm to its right
        int dy = player.getY() - this.getY();                    // + = I'm below it
        
        if (Math.abs(dx) >= Math.abs(dy)) {                     // greedy: close the BIGGER gap first
            if(Math.abs(dx) < this.getAmount() ){               // closer than one step? take only the gap left ("arrive", bug 12)
                this.setAmount(Math.abs(dx));
            }
            this.moveX(Integer.signum(dx), maxW);
        } else {
            if(Math.abs(dy) < this.getAmount() ){               // same on the y axis (dy, not dx: bug 15)
                this.setAmount(Math.abs(dy));
            }
            this.moveY(Integer.signum(dy), maxH);
        }
        this.setAmount(normal);                                // back to the normal step: a changed field would PERSIST (bug 13)
       
    }
}
