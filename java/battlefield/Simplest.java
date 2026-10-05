import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// THE SIMPLEST GAME: press UP → a square appears. Press DOWN → it disappears.     Run: java Simplest.java
public class Simplest extends JPanel implements KeyListener {
   Player player = new Player();
    Dimension dm = new Dimension(300, 300);
    Simplest() {
        setPreferredSize(dm);
        setFocusable(true);
        addKeyListener(this);                    // "Swing, call MY keyPressed when a key is pressed"
    }

    public void keyPressed(KeyEvent e) {    
             // Swing calls this (we never call it ourselves)
        if (e.getKeyCode() == KeyEvent.VK_UP){
            player.moveY(-1,dm.height);
        }   
        if (e.getKeyCode() == KeyEvent.VK_DOWN){
             player.moveY(1,dm.height);
        } 
        if (e.getKeyCode() == KeyEvent.VK_LEFT){
            player.setColor(Color.BLUE);
            player.moveX(-1,dm.width);
        } 
        if (e.getKeyCode() == KeyEvent.VK_RIGHT){
            player.setColor(Color.RED);
            player.moveX(1,dm.width);
        } 
        repaint();                               // "Swing, please draw me again"
    }
    public void keyReleased(KeyEvent e) {}
    public void keyTyped(KeyEvent e) {}

    protected void paintComponent(Graphics g) {  // Swing calls this whenever it draws the panel
        super.paintComponent(g);
        if (player.isVisible()) {
            g.setColor(player.getColor());
            g.fillRect(player.getX(), player.getY(), player.getWidth(), player.getHeight());
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
