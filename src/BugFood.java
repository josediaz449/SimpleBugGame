import java.awt.*;
import java.util.Random;

public class BugFood extends Sprite{
    final static private int W = 10;
    final static private int H = 10;

    private final Random random;
    private Color color = Color.GREEN;
    public BugFood(int x, int y) {
        super(x, y, W, H);
        random = new Random();
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
    public void changeLocation(){
        this.x = random.nextInt(390);
        this.y = random.nextInt(390);
    }
}
