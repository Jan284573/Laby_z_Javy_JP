import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class L2Form {
    public JPanel GUI;
    private JButton UserLoginSubmitButton;
    private JTextField UsernameTextField;
    private JTextArea outputTextArea;
    private JPasswordField passwordField1;


    public L2Form() {
        UserLoginSubmitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = UsernameTextField.getText();
                String password = new String(passwordField1.getPassword());

                outputTextArea.append("Name: "+name+"\n");
                outputTextArea.append("Password: "+password+"\n");

                UsernameTextField.setText("Username");
                passwordField1.setText("");

            }
        });
    }


}
