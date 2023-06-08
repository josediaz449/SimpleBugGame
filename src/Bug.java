import java.awt.*;
import java.awt.event.KeyEvent;


public class Bug extends Sprite {
    private int dx = 0;
    private int dy = -2;
    final static private int W = 20;
    final static private int H = 20;
    private Color color = Color.BLUE;
    private float speedMultiplier = 1;

    /*Constructor with parameters*/
    public Bug(int x, int y) {
        super(x, y, W, H);
    }
    public void move() {

        x += (dx*speedMultiplier);
        y += (dy*speedMultiplier);
    }
    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
            setDx(-2);
            setDy(0);
        }

        if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
            setDx(2);
            setDy(0);
        }

        if (key == KeyEvent.VK_UP || key == KeyEvent.VK_W) {
            setDx(0);
            setDy(-2);
        }

        if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) {
            setDx(0);
            setDy(2);
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
    public float getSpeedMultiplier() {
        return speedMultiplier;
    }
    public void setSpeedMultiplier(float speedMultiplier) {
        this.speedMultiplier = speedMultiplier;
    }

}
