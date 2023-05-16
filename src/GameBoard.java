import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

public class GameBoard extends JPanel implements Runnable, ActionListener {
    private final int B_WIDTH = 400;
    private final int B_HEIGHT = 400;
    private final int INITIAL_X = 200;
    private final int INITIAL_Y = 200;
    private final int DELAY = 25;

    private Snake snake;
    private ArrayList<SnakeFood> snakeFoods;
    private Thread animator;
    private Random random;

    public GameBoard() {

        initBoard();
    }

    private void initBoard() {

        //setBackground(Color.BLACK);
        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        addKeyListener(new TAdapter());
        setFocusable(true);

        //loadImage();
        snake = new Snake();

        //x = INITIAL_X;
        //y = INITIAL_Y;
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
    }

    private void drawSnake(Graphics g) {

        //g.fillOval((int)circle.getX(),(int)circle.getY(),20,20);
        for(Sprite snakePiece : snake.getSnake()){
            g.fillOval((int)snakePiece.getX(),(int)snakePiece.getY(),20,20);
            Toolkit.getDefaultToolkit().sync();
            //Graphics2D graphics2D = new Graphics()
        }
        //Toolkit.getDefaultToolkit().sync();
    }

    private void cycle() {

        //x += 1;
        //y += 1;
        //circle.move();
        for(SnakePiece snakePiece:snake.getSnake()){
            snakePiece.move();

        }
        if(snake.getSnake().getFirst().getY()==0){
            //snake.getSnake().getFirst().setY(INITIAL_Y);
            //snake.getSnake().getFirst().setY(INITIAL_Y);
            //snake.getSnake().add(new SnakePiece(snake.getSnake().getLast().getX(), snake.getSnake().getLast().getY()+20,20,20));
        }
        /*
        if (circle.getY() < -25) {

            circle.setY(INITIAL_Y);
            circle.setX(INITIAL_X);
        }*/
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
        e.notify();
    }
    private class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            for (SnakePiece snakePiece : snake.getSnake()) {
                snakePiece.keyPressed(e);
            }
            //sn.keyPressed(e);
        }
    }
}
