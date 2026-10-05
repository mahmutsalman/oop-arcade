import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// THE SIMPLEST GAME: press UP → a square appears. Press DOWN → it disappears.     Run: java Simplest.java
public class Simplest extends JPanel implements KeyListener {
    boolean visible = false;                     // the whole "game state": one true/false
    Color color;

    Simplest() {
        setPreferredSize(new Dimension(300, 300));
        setFocusable(true);
        addKeyListener(this);                    // "Swing, call MY keyPressed when a key is pressed"
    }

    public void keyPressed(KeyEvent e) {         // Swing calls this (we never call it ourselves)
        if (e.getKeyCode() == KeyEvent.VK_UP)   visible = true;
        if (e.getKeyCode() == KeyEvent.VK_DOWN) visible = false;
        if (e.getKeyCode() == KeyEvent.VK_LEFT){
            visible = true;
            color = Color.BLUE;
        } 
        if (e.getKeyCode() == KeyEvent.VK_RIGHT){
            visible = true;
            color = Color.RED;
        } 
        repaint();                               // "Swing, please draw me again"
    }
    public void keyReleased(KeyEvent e) {}
    public void keyTyped(KeyEvent e) {}

    protected void paintComponent(Graphics g) {  // Swing calls this whenever it draws the panel
        super.paintComponent(g);
        if (visible) {
            g.setColor(color);
            g.fillRect(125, 125, 50, 50);
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
