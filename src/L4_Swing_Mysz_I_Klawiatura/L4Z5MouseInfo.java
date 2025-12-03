package L4_Swing_Mysz_I_Klawiatura;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public class L4Z5MouseInfo extends JFrame {

    private Paint3Panel drawPanel;
    private JTextArea logs;

    public L4Z5MouseInfo(){
        setTitle("Mouse Info App");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600,600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        drawPanel = new Paint3Panel();
        drawPanel.setBackground(Color.white);
        drawPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
        add(drawPanel, BorderLayout.CENTER);

        logs = new JTextArea("Logs: \n");
        logs.setPreferredSize(new Dimension(120,600));
        logs.setBackground(Color.gray);
        logs.setForeground(Color.white);
        add(logs, BorderLayout.EAST);

        drawPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                logs.setText("Logs:\nNajechano na\nobszar rysowania\n");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                logs.setText("Logs:\nOpuszczono\nobszar rysowania\n");
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                logs.append("Kliknięto w\nobszar rysowania\n");
            }

            @Override
            public void mouseWheelMoved(MouseWheelEvent e){
                int scroll = e.getWheelRotation();
                if (scroll>0){

                }
            }

        });

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(()->{
            new L4Z5MouseInfo().setVisible(true);
        });
    }
}

class Paint3Panel extends JPanel{
    private Rectangle kwadrat = new Rectangle(100,100,60,60);

    public Paint3Panel(){
        addMouseWheelListener(new MouseAdapter() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                int scroll = e.getWheelRotation();
                if (scroll<0){
                    kwadrat.setSize(kwadrat.width+10,kwadrat.height+10);
                } else if (scroll>0) {
                    kwadrat.setSize(kwadrat.width-10,kwadrat.height-10);
                }
                repaint();
            }
        });

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(Color.red);
        g2.fill(kwadrat);

    }
}
