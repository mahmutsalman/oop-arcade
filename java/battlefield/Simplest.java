import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

// BATTLEFIELD (grown from "the simplest game"): you (blue) move with the arrow keys; a BOUNCER (red) walks left-right;
// a CHASER (yellow) walks toward you. The "Next map" button switches between the maps. Run: javac -d out *.java && java -cp out Simplest
public class Simplest extends JPanel implements KeyListener {
    static final int MAP_COUNT = 2;
    Soldier soldier = new Soldier();              // the player: the same object on every map
    List<Soldier> soldiers = new ArrayList<>();   // every soldier on the current map (the interface on the left, the real object on the right)
    List<Bullet> bullets = new ArrayList<>();
    Dimension dm = new Dimension(300, 300);
    Timer timer;
    int map = 1;                                  // the map being played: 1..MAP_COUNT
    Simplest() {
        loadMap(1);
        setPreferredSize(dm);
        setFocusable(true);
        addKeyListener(this);                    // "Swing, call MY keyPressed when a key is pressed"
        timer = new Timer(100,e -> tick());
        timer.start();
    }

    // Empties the battlefield and fills it with the soldiers of one map. The player is kept and goes back to the centre.
    void loadMap(int number) {
        map = number;
        soldiers.clear();
        bullets.clear();
        soldier.setX(125);
        soldier.setY(125);
        soldiers.add(soldier);
        if (map == 1) {                           // map 1: every kind of soldier
            setBackground(new Color(238, 238, 238));
            Soldier enemy = new BouncerSoldier(50,50,50,50);
            Soldier chaser = new ChaserSoldier(250,250,50,50);
            Soldier gunBouncer = new GunBouncerSoldier(50, 100, 50, 50);
            Soldier gunChaser = new GunChaserSoldier(100,100,50,50);
            Soldier artillery = new ArtillerySoldier(0, 250, 50, 50, "AAABBB".toCharArray(), 2);
            enemy.setColor(Color.red);
            chaser.setColor(Color.yellow);
            gunBouncer.setColor(Color.CYAN);
            gunChaser.setColor(Color.MAGENTA);
            soldiers.add(enemy);
            soldiers.add(chaser);
            soldiers.add(gunBouncer);
            soldiers.add(gunChaser);
            soldiers.add(artillery);
        } else {                                  // map 2: the gun chaser alone
            setBackground(new Color(222, 236, 222));
            Soldier gunChaser = new GunChaserSoldier(0, 100, 50, 50);
            gunChaser.setColor(Color.MAGENTA);
            soldiers.add(gunChaser);
        }
        repaint();
    }

    // the button calls this: 1 -> 2 -> 1 ...
    void nextMap() {
        loadMap(map % MAP_COUNT + 1);
    }

    public void keyPressed(KeyEvent e) {    
             // Swing calls this (we never call it ourselves)
        if (e.getKeyCode() == KeyEvent.VK_UP){
            soldier.moveY(-1,dm.height);
        }   
        if (e.getKeyCode() == KeyEvent.VK_DOWN){
             soldier.moveY(1,dm.height);
        } 
        if (e.getKeyCode() == KeyEvent.VK_LEFT){
            soldier.setColor(Color.BLUE);
            soldier.moveX(-1,dm.width);
        } 
        if (e.getKeyCode() == KeyEvent.VK_RIGHT){
            soldier.setColor(Color.RED);
            soldier.moveX(1,dm.width);
        } 
        repaint();                               // "Swing, please draw me again"
    }
    public void keyReleased(KeyEvent e) {}
    public void keyTyped(KeyEvent e) {}
    public void tick(){                          // called by the lambda every 100 ms (it doesn't need the event)
        // POLYMORPHISM: one call, each soldier runs ITS OWN update (Bouncer / Chaser override it, the plain Soldier does nothing).
        // No if-chain on a type any more (that was the bad-on-purpose version, commit 20238eb).
        for (Soldier s : soldiers) {
            s.update(soldier,dm.width,dm.height,bullets);
            // the keyboard player is a plain Soldier: its update() is empty, so the loop needs no special case
    
        }
        // the game owns the bullets: it moves them, then removes the ones that left the map (move() only moves)
        for(Bullet bullet : bullets){
            bullet.move(dm.width,dm.height);
        }
        bullets.removeIf(b -> b.isOut(dm.width));
        repaint();
    }

    protected void paintComponent(Graphics g) {  // Swing calls this whenever it draws the panel
        super.paintComponent(g);
        for (Soldier s : soldiers) {              // one loop draws them all, 2 or 2,000
            if (s.isVisible()) {
                g.setColor(s.getColor());
                g.fillRect(s.getX(), s.getY(), s.getWidth(), s.getHeight());
            }
        }
        for(Bullet b : bullets){
            g.setColor(b.getColor());
            g.fillRect(b.getX(), b.getY(), b.getWidth(), b.getHeight());
        }
    }

    public static void main(String[] args) {
        JFrame f = new JFrame("Simplest");
        Simplest game = new Simplest();
        JButton next = new JButton("Next map");
        next.setFocusable(false);                // the panel keeps the keyboard, so the arrow keys still work after a click
        next.addActionListener(e -> game.nextMap());
        f.add(game, BorderLayout.CENTER);
        f.add(next, BorderLayout.SOUTH);         // under the map, so it never covers the game
        f.pack();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }
}
