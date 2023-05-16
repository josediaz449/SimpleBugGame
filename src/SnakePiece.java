import java.awt.*;
import java.awt.event.KeyEvent;


public class SnakePiece extends Sprite {
    private int dx = 0;
    private int dy = -2;
    final static private int W = 20;
    final static private int H = 20;
    private Color color = Color.BLUE;

    /*Constructor with parameters*/
    public SnakePiece(int x, int y) {
        super(x, y, W, H);
    }
    public void move() {

        x += dx;
        y += dy;
    }

    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            dx = -2;
            dy = 0;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 2;
            dy = 0;
        }

        if (key == KeyEvent.VK_UP) {
            dy = -2;
            dx = 0;
        }

        if (key == KeyEvent.VK_DOWN) {
            dy = 2;
            dx = 0;
        }
    }

    public int getDx() {
        return dx;
    }

    public void setDx(int dx) {
        this.dx = dx;
    }

    public int getDy() {
        return dy;
    }

    public void setDy(int dy) {
        this.dy = dy;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
