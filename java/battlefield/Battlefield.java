import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/*
 * BATTLEFIELD, STEP 1: the simplest thing possible. One soldier (blue) that moves with the arrow keys.
 * Run: java Battlefield.java          Next steps: README.md
 */
public class Battlefield extends JPanel implements KeyListener {
    static final int TILE = 32, COLS = 20, ROWS = 14;
    Soldier player = new Soldier(2, 2);

    Battlefield() {
        setPreferredSize(new Dimension(COLS * TILE, ROWS * TILE));
        setBackground(new Color(70, 90, 50));      // grass
        setFocusable(true);
        addKeyListener(this);
        setRequestFocusEnabled(true);
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT)  player.move(-1, 0);
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) player.move(1, 0);
        if (e.getKeyCode() == KeyEvent.VK_UP)    player.move(0, -1);
        if (e.getKeyCode() == KeyEvent.VK_DOWN)  player.move(0, 1);
        repaint();                                 // draw again
    }
    public void keyReleased(KeyEvent e) {}
    public void keyTyped(KeyEvent e) {}

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        player.draw(g);
    }

    public static void main(String[] args) {
        JFrame f = new JFrame("Battlefield");
        f.add(new Battlefield());
        f.pack();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }
}

class Soldier {
    int x, y;
    Soldier(int x, int y) { this.x = x; this.y = y; }

    void move(int dx, int dy) { x += dx; y += dy; }

    void draw(Graphics g) {
        g.setColor(new Color(80, 140, 255));
        g.fillRect(x * Battlefield.TILE, y * Battlefield.TILE, Battlefield.TILE, Battlefield.TILE);
    }
}
