import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.LinkedList;
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

    public GameBoard() {

        initBoard();
    }

    private void initBoard() {
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        addKeyListener(new TAdapter());
        setFocusable(true);
        Random random = new Random(490);
        bugFood = new BugFood(random.nextInt(390), random.nextInt(390));
        bug = new Bug(INITIAL_X,INITIAL_Y);
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

        drawBug(g);
        drawFood(g);
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

    private void cycle() {
        bug.move();

        if(bug.getBounds().getMinY()==0) {
            bug.setDy(2);
            bug.setDx(0);
        }
        if(bug.getBounds().getMaxY()==400) {
            bug.setDy(-2);
            bug.setDx(0);
        }
        if(bug.getBounds().getMinX()==0) {
            bug.setDy(0);
            bug.setDx(2);
        }
        if(bug.getBounds().getMaxX()==400) {
            bug.setDy(0);
            bug.setDx(-2);
        }
        if (bug.getBounds().intersects(bugFood.getBounds())){
            bugFood.changeLocation();
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

    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            bug.keyPressed(e);
        }
    }

}
