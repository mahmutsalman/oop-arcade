import java.awt.Color;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

// A cannon crew that stands still. Before every shot the crew must finish a list of jobs, one job per slot
// (each letter is a job type, e.g. 'L' = load, 'A' = aim). After doing a job type, the crew must wait
// `cooldown` slots before doing the SAME type again; other types, or idling, fill the gap.
// The cannon fires when the whole list is done, then the crew starts the list again.
public class ArtillerySoldier extends Soldier {
    private static final int TICKS_PER_SLOT = 5;        // one job slot lasts 5 game ticks (0.5 s)

    private final int slotsPerShot;                     // the fewest slots that finish the whole job list
    private int ticks = 0;                              // game ticks since the last shot

    public ArtillerySoldier(int x, int y, int width, int height, char[] jobs, int cooldown){
        super(x, y, width, height);
        this.slotsPerShot = leastInterval(jobs, cooldown);
        setColor(Color.ORANGE);
    }

    @Override
    public void update(Soldier player, int maxW, int maxH, List<Bullet> bullets){
        ticks++;
        if (ticks >= slotsPerShot * TICKS_PER_SLOT) {
            ticks = 0;
            int side = player.getX() >= this.getX() ? 1 : -1;
            bullets.add(new Bullet(this.getX(), this.getY(), new int[]{side, 0}));
        }
    }

    // the fewest slots needed to do every job, with `n` slots between two jobs of the same type
    static int leastInterval(char[] tasks, int n){
        Map<Character,Integer> map = new HashMap<>();

        for(char c : tasks){
            map.put(c,map.getOrDefault(c, 0)+1);
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b) -> b-a); //HEAP
        for(Map.Entry<Character,Integer> set : map.entrySet() ){
            pq.add(set.getValue());
        }
        int tick = 0;

        // need for int[]{remainingOccurence,nextAvaliableTime};
        Queue<int[]> room = new LinkedList<>(); //FIFO
        while(!pq.isEmpty() || !room.isEmpty()){
           
            int nextAvaliableTime = tick+n;
            int count = 0;
            if(!pq.isEmpty()){
                count = pq.poll()-1;
            }            
            if(count != 0){
                room.add(new int[]{count,nextAvaliableTime});
            }
            if(!room.isEmpty() && tick==room.peek()[1]){ 
                pq.add(room.poll()[0]);
            }
            
            tick++;
        }
        return tick;
    }

    // Run: javac -d out *.java && java -cp out ArtillerySoldier
    public static void main(String[] args){
        check("AAABBB", 2, 8);
        check("AAABBB", 0, 6);
        check("AAABBB", 3, 10);
        check("ACABDB", 1, 6);
        check("AAAAAABCDEFG", 2, 16);
        check("A", 5, 1);
    }

    private static void check(String jobs, int n, int expected){
        int got = leastInterval(jobs.toCharArray(), n);
        System.out.println((got == expected ? "PASS  " : "FAIL  ") + jobs + "  n=" + n
                + "  expected " + expected + ", got " + got);
    }
}
