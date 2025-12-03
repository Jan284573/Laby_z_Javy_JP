package L3_Swing;

import javax.swing.*;

public class L3GUI_Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(()->{
            JFrame frame = new JFrame("Laby3Frame");
            frame.setContentPane(new L3Form().GUI);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}


