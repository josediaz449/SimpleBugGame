import java.awt.*;

public class SnakeFood extends Sprite{
    private int x;
    private int y;
    final static private int W = 10;
    final static private int H = 10;
    private Color color = Color.GREEN;
    public SnakeFood(int x, int y, int width, int height) {
        super(x, y, width, height);
    }
}
