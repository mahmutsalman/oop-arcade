import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

// BATTLEFIELD (grown from "the simplest game"): you (blue) move with the arrow keys; a BOUNCER (red) walks left-right;
// a CHASER (yellow) walks toward you. Run: javac -d out *.java && java -cp out Simplest
public class Simplest extends JPanel implements KeyListener {
   Soldier soldier = new Soldier();
   Soldier enemy = new BouncerSoldier(50,50,50,50);
   Soldier chaser = new ChaserSoldier(250,250,50,50);
    List<Soldier> soldiers = new ArrayList<>();   // every soldier on the map (the interface on the left, the real object on the right)
    Dimension dm = new Dimension(300, 300);
    Timer timer;
    Simplest() {
        enemy.setColor(Color.red);
        enemy.setType("bouncer");               // leftover from the String-type version: inheritance made it unnecessary
        chaser.setColor(Color.yellow);
        chaser.setType("chaser");
        soldiers.add(soldier);                    // a statement: so it goes in the constructor, not the class body
        soldiers.add(enemy);
        soldiers.add(chaser);
        setPreferredSize(dm);
        setFocusable(true);
        addKeyListener(this);                    // "Swing, call MY keyPressed when a key is pressed"
        timer = new Timer(100,e -> tick());
        timer.start();
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
            s.update(soldier,dm.width,dm.height);
            // the keyboard player is a plain Soldier: its update() is empty, so the loop needs no special case
        }
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
    }

    public static void main(String[] args) {
        JFrame f = new JFrame("Simplest");
        f.add(new Simplest());
        f.pack();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }
}
