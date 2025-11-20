
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class L2Form {
    public JPanel GUI;
    private JButton UserLoginSubmitButton;
    private JTextArea outputTextArea;
    private JPasswordField passwordField1;
    private JButton moveLeftButton;
    private JButton moveRightButton;
    private JButton scaleChangeButton;
    private JPanel movePanel;
    private JLabel TestObject;
    private JButton colorButton;


    public L2Form() {
        UserLoginSubmitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = JOptionPane.showInputDialog(
                        UserLoginSubmitButton,
                        "Podaj swoje imię: ",
                        "Okno dialogowe",
                        JOptionPane.QUESTION_MESSAGE
                );
                String password = new String(passwordField1.getPassword());

                String text = outputTextArea.getText();
                if (text.length()-text.replace("\n","").length() >= 21){
                    outputTextArea.setText("");
                }

                outputTextArea.append("\nWitaj");
                outputTextArea.append("\nName: "+name);
                outputTextArea.append("\nPassword: "+password);

                passwordField1.setText("");



            }
        });

        movePanel.setLayout(null);
        TestObject.setOpaque(true);
        if(TestObject.getWidth() ==0 || TestObject.getHeight() ==0){
            TestObject.setBounds(100,20,100,25);
        }


        scaleChangeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(TestObject.getWidth()==100 && TestObject.getHeight()==25) {
                    TestObject.setSize(200, 50);
                    TestObject.setText("200");
                } else {
                    TestObject.setSize(100, 25);
                    TestObject.setText("100");
                }
                TestObject.revalidate();
                TestObject.repaint();
            }
        });


        moveLeftButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int x = TestObject.getX();
                int y = TestObject.getY();
                if (x > 0) {
                    TestObject.setLocation((x - 50), y);
                }
                //outputTextArea.append("\n"+(x-50));
                TestObject.repaint();
            }
        });

        moveRightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int x = TestObject.getX();
                int y = TestObject.getY();
                if (x+50 < movePanel.getWidth()-TestObject.getWidth()) {
                    TestObject.setLocation((x + 50), y);
                }
                //outputTextArea.append("\n"+(x+50));
                TestObject.repaint();
            }
        });


        colorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color actual = TestObject.getBackground();
                Color chosen = JColorChooser.showDialog(
                        TestObject,
                        "Wybierz kolor tła",
                        actual
                );
                if (chosen != null) {
                    TestObject.setBackground(chosen);
                }
            }
        });
    }


}
