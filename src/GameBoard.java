import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class GameBoard extends JPanel implements Runnable, ActionListener {
    private final int B_WIDTH = 400;
    private final int B_HEIGHT = 400;
    private final int INITIAL_X = 200;
    private final int INITIAL_Y = 200;
    private final int DELAY = 25;
    private BugFood bugFood;
    private Thread animator;
    private Bug bug;
    private boolean inGame;
    public static int score = 0;

    public GameBoard() {
        initBoard();
    }

    private void initBoard() {
        setBoardSettings();
        addKeyListener(new TAdapter());
        initializeVariables();
    }

    private void initializeVariables() {
        Random random = new Random(490);
        bugFood = new BugFood(random.nextInt(390), random.nextInt(390));
        bug = new Bug(INITIAL_X,INITIAL_Y);
        inGame = true;
    }

    private void setBoardSettings() {
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        setFocusable(true);
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
        if (inGame) {
            drawGamePieces(g);
        } else {
            drawGameOver(g);
        }
        Toolkit.getDefaultToolkit().sync();
    }

    private void drawGamePieces(Graphics g) {
        drawBug(g);
        drawFood(g);
        drawScore(g);
    }

    private static void drawScore(Graphics g) {
        g.setColor(Color.WHITE);
        g.drawString("Score: " + score, 5, 15);
    }
    private void drawBug(Graphics g) {
        g.setColor(bug.getColor());
        g.fillOval(bug.getX(),bug.getY(),20,20);
        Toolkit.getDefaultToolkit().sync();
    }
    private void drawFood(Graphics g){
        g.setColor(bugFood.getColor());
        g.fillRect(bugFood.getX(), bugFood.getY(), bugFood.getWidth(), bugFood.getHeight());
        Toolkit.getDefaultToolkit().sync();
    }
    private void drawGameOver(Graphics g) {

        String msg = "Game Over. Score: "+score;
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics fm = getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msg, (B_WIDTH - fm.stringWidth(msg)) / 2,
                B_HEIGHT / 2);
    }

    private void cycle() {
        bug.move();

        if(bug.getBounds().getMinY()<=0) {
            inGame = false;
        }
        if(bug.getBounds().getMaxY()>=400) {
            inGame = false;
        }
        if(bug.getBounds().getMinX()<=0) {
            inGame = false;;
        }
        if(bug.getBounds().getMaxX()>=400) {
            inGame = false;
        }
        if (bug.getBounds().intersects(bugFood.getBounds())){
            score+=1;
            bug.setSpeedMultiplier((float) (bug.getSpeedMultiplier()+0.1));
            bugFood.changeLocation();
        }


    }

    @Override
    public void run() {

        long beforeTime, timeDiff, sleep;

        beforeTime = System.currentTimeMillis();

        while (inGame) {

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
    }

    private class TAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            bug.keyPressed(e);
        }
    }

}
