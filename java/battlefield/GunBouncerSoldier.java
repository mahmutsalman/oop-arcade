// A bouncer that will also shoot. Extends BouncerSoldier (it IS a bouncer), so the bounce logic is inherited, not copied.
public class GunBouncerSoldier extends BouncerSoldier {

    public GunBouncerSoldier(int x, int y , int width, int height){
        super(x,y,width,height);
    }
}
