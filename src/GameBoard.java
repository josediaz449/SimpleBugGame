import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.Random;

public class GameBoard extends JPanel implements Runnable, ActionListener {
    private final int B_WIDTH = 400;
    private final int B_HEIGHT = 400;
    private final int INITIAL_X = 200;
    private final int INITIAL_Y = 200;
    private final int DELAY = 25;
    //private JButton startButton;

    private Snake snake;
    private ArrayList<SnakeFood> snakeFoods;
    private SnakeFood snakeFood;
    private Thread animator;
    //private boolean gameStarted = false;

    public GameBoard() {

        initBoard();
    }

    private void initBoard() {
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        addKeyListener(new TAdapter());
        setFocusable(true);
        Random random = new Random(490);
        snake = new Snake();
        snakeFood = new SnakeFood(random.nextInt(390), random.nextInt(390));
    }

    @Override
    public void addNotify() {
        super.addNotify();

        animator = new Thread(this);
        animator.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawSnake(g);
        drawFood(g);
    }

    private void drawSnake(Graphics g) {
        for(SnakePiece snakePiece : snake.getSnake()){
            g.setColor(snakePiece.getColor());
            g.fillOval(snakePiece.getX(),snakePiece.getY(),20,20);
            Toolkit.getDefaultToolkit().sync();
        }
    }
    private void drawFood(Graphics g){
        g.setColor(snakeFood.getColor());
        g.fillRect(snakeFood.getX(), snakeFood.getY(), snakeFood.getWidth(), snakeFood.getHeight());
        //System.out.println(snakeFood.getX()+", "+snakeFood.getY());
        Toolkit.getDefaultToolkit().sync();
    }

    private void cycle() {
        for(SnakePiece snakePiece:snake.getSnake()){
            snakePiece.move();

        }

        if(snake.getSnake().getFirst().getBounds().getMinY()==0) {
            snake.getSnake().getFirst().setDy(2);
            snake.getSnake().getFirst().setDx(0);
        }
        if(snake.getSnake().getFirst().getBounds().getMaxY()==400) {
            snake.getSnake().getFirst().setDy(-2);
            snake.getSnake().getFirst().setDx(0);
        }
        if(snake.getSnake().getFirst().getBounds().getMinX()==0) {
            snake.getSnake().getFirst().setDy(0);
            snake.getSnake().getFirst().setDx(2);
        }
        if(snake.getSnake().getFirst().getBounds().getMaxX()==400) {
            snake.getSnake().getFirst().setDy(0);
            snake.getSnake().getFirst().setDx(-2);
        }
        if (snake.getSnake().getFirst().getBounds().intersects(snakeFood.getBounds())){
            snakeFood.changeLocation();
        }


    }

    @Override
    public void run() {

        long beforeTime, timeDiff, sleep;

        beforeTime = System.currentTimeMillis();

        while (true) {

            cycle();
            repaint();

            timeDiff = System.currentTimeMillis() - beforeTime;
            sleep = DELAY - timeDiff;

            if (sleep < 0) {
                sleep = 2;
            }

            try {
                Thread.sleep(sleep);
            } catch (InterruptedException e) {

                String msg = String.format("Thread interrupted: %s", e.getMessage());

                JOptionPane.showMessageDialog(this, msg, "Error",
                        JOptionPane.ERROR_MESSAGE);
            }

            beforeTime = System.currentTimeMillis();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //e.notify();
    }
    private class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            for (SnakePiece snakePiece : snake.getSnake()) {
                snakePiece.keyPressed(e);
            }
        }
    }
}
