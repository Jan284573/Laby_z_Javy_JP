package L4_Swing_Mysz_I_Klawiatura;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class L4Z3KeyApp extends JFrame {

    private JPanel mainPanel;
    private JTextField textField;
    private JLabel info;

    public L4Z3KeyApp(){
        setTitle("KeyApp");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400,300);
        setLocationRelativeTo(null);

        mainPanel = new JPanel(new BorderLayout());
        setContentPane(mainPanel);

        textField = new JTextField("Wpisz coś: ");
        mainPanel.add(textField,BorderLayout.CENTER);

        info = new JLabel(
          "A- red, B- blue, C- clear"     ,
          SwingConstants.CENTER
        );
        info.setPreferredSize(new Dimension(400,50));
        info.setBackground(Color.lightGray);
        mainPanel.add(info, BorderLayout.SOUTH);

        mainPanel.setFocusable(true);

        mainPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mainPanel.requestFocusInWindow();
            }
        });

        mainPanel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                char c = Character.toUpperCase(e.getKeyChar());

                if (c == 'A'){
                    textField.setForeground(Color.RED);
                } else if (c =='B') {
                    textField.setForeground(Color.BLUE);
                } else if (c == 'C') {
                    textField.setText("");
                }
            }
        });

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(()->{
            new L4Z3KeyApp().setVisible(true);
        });
    }
}
