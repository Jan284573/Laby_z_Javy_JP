package L4_Swing_Mysz_I_Klawiatura;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class L4Z1MouseApp extends JFrame {

    private JTextField textField;

    public L4Z1MouseApp() {
        setTitle("MouseApp");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400,100);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());

        textField = new JTextField(20);
        add(textField);

        textField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e){
                int x = e.getX();
                int y = e.getY();
                System.out.println("Mouse Click: x="+ x +", y="+ y);
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(()->{
            new L4Z1MouseApp().setVisible(true);
        });
    }
}
