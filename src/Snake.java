import java.util.LinkedList;

public class Snake {
    private LinkedList<SnakePiece> snake;

    public Snake(){
        snake = new LinkedList<>();
        snake.add(new SnakePiece(200,200));
        //snake.add(new SnakePiece(snake.getLast().getX(), snake.getLast().getY()-30, 20,20));
    }
    public LinkedList<SnakePiece> getSnake() {
        return snake;
    }

    public void setSnake(LinkedList<SnakePiece> snake) {
        this.snake = snake;
    }
}
