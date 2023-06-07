import javax.swing.*;
import java.awt.*;

public class GameMain extends JFrame {
    public GameMain() {

        initUI();
    }

    private void initUI() {

        add(new GameBoard());

        setResizable(false);
        pack();

        setTitle("Snake Game");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            JFrame ex = new GameMain();
            ex.setVisible(true);
        });
    }
}