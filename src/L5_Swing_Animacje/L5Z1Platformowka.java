package L5_Swing_Animacje;

import javax.swing.*;
import java.awt.*;

public class L5Z1Platformowka extends JFrame {

    private GamePanel gamePane;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    final L5Z1Platformowka frame = new L5Z1Platformowka();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public L5Z1Platformowka(){
        setTitle("Game");
        setSize(800,600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        gamePane = new GamePanel();
        setContentPane(gamePane);
        gamePane.setLayout(null);

        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                gamePane.initialize();
            }
        });
    }
}


