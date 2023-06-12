import javax.swing.*;
import java.awt.*;

public class GameMain extends JFrame {
    public GameMain() {
        initUI();
    }

    private void initUI() {

        add(new GameBoard());
        setFrameSettings();
    }

    private void setFrameSettings() {
        setResizable(false);
        pack();
        setTitle("Bug Game");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            JFrame gameFrame = new GameMain();
            gameFrame.setVisible(true);
        });
    }
}