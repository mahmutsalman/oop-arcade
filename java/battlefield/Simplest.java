import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// THE SIMPLEST GAME: press UP → a square appears. Press DOWN → it disappears.     Run: java Simplest.java
public class Simplest extends JPanel implements KeyListener {
    boolean visible = false;                     // the whole "game state": one true/false
    Color color;
    private int x=125;
    private int y=125;
    private int width = 50;
    private int height = 50;
    Dimension dm = new Dimension(300, 300);
    Simplest() {
        setPreferredSize(dm);
        setFocusable(true);
        addKeyListener(this);                    // "Swing, call MY keyPressed when a key is pressed"
    }

    public void keyPressed(KeyEvent e) {         // Swing calls this (we never call it ourselves)
        if (e.getKeyCode() == KeyEvent.VK_UP){
            visible = true;
            y = Math.max(0,y-10);
        }   
        if (e.getKeyCode() == KeyEvent.VK_DOWN){
            
            y = Math.min(y+10,dm.height-height);
        } 
        if (e.getKeyCode() == KeyEvent.VK_LEFT){
            visible = true;
            color = Color.BLUE;
            x = Math.max(0,x-10);
        } 
        if (e.getKeyCode() == KeyEvent.VK_RIGHT){
            visible = true;
            color = Color.RED;
            x = Math.min(x + 10, dm.width-width);
            
        } 
        repaint();                               // "Swing, please draw me again"
    }
    public void keyReleased(KeyEvent e) {}
    public void keyTyped(KeyEvent e) {}

    protected void paintComponent(Graphics g) {  // Swing calls this whenever it draws the panel
        super.paintComponent(g);
        if (visible) {
            g.setColor(color);
            g.fillRect(x, y, width, height);
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
