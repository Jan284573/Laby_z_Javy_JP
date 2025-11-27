//SWING
//Zadanie 4

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class L2CalculatorGUI extends JFrame {

    private JTextField display;

    private double firstNumber = 0;
    private String currentOp = null;
    private boolean startNewNumber = true;

    public L2CalculatorGUI() {
        setTitle("Kalkulator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 400);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout(5, 5));

        // Pole wyświetlające wynik / wprowadzane dane
        display = new JTextField("0");
        display.setEditable(false);
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setFont(new Font("Arial", Font.BOLD, 24));
        add(display, BorderLayout.NORTH);

        // Panel z przyciskami 4x4
        JPanel buttonPanel = new JPanel(new GridLayout(4, 4, 5, 5));
        add(buttonPanel, BorderLayout.CENTER);

        String[] buttons = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "C", "0", "=", "+"
        };

        for (String text : buttons) {
            JButton btn = new JButton(text);
            btn.setBackground(Color.lightGray);
            btn.setFont(new Font("Arial", Font.BOLD, 18));
            buttonPanel.add(btn);

            btn.addActionListener(new ButtonClickListener());
        }
    }

    private class ButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String cmd = ((JButton) e.getSource()).getText();

            if (cmd.matches("[0-9]")) {
                // Cyfry
                if (startNewNumber || display.getText().equals("0")) {
                    display.setText(cmd);
                    startNewNumber = false;
                } else {
                    display.setText(display.getText() + cmd);
                }
            } else if (cmd.matches("[+\\-*/]")) {
                // Operatory
                try {
                    firstNumber = Double.parseDouble(display.getText());
                } catch (NumberFormatException ex) {
                    firstNumber = 0;
                }
                currentOp = cmd;
                startNewNumber = true;
            } else if (cmd.equals("=")) {
                // Równa się
                if (currentOp != null) {
                    try {
                        double secondNumber = Double.parseDouble(display.getText());
                        double result = calculate(firstNumber, secondNumber, currentOp);
                        display.setText(removeTrailingZeros(result));
                    } catch (ArithmeticException ex) {
                        display.setText("Błąd");
                    }
                    currentOp = null;
                    startNewNumber = true;
                }
            } else if (cmd.equals("C")) {
                // Clear
                firstNumber = 0;
                currentOp = null;
                display.setText("0");
                startNewNumber = true;
            }
        }
    }

    private double calculate(double a, double b, String op) {
        return switch (op) {
            case "+" -> a + b;
            case "-" -> a - b;
            case "*" -> a * b;
            case "/" -> {
                if (b == 0) throw new ArithmeticException("dzielenie przez zero");
                yield a / b;
            }
            default -> b;
        };
    }

    private String removeTrailingZeros(double value) {
        String s = Double.toString(value);
        if (s.contains(".")) {
            // ucinamy niepotrzebne zera
            s = s.replaceAll("0+$", "").replaceAll("\\.$", "");
        }
        return s;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new L2CalculatorGUI().setVisible(true);
        });
    }
}
