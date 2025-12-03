package L4_Swing_Mysz_I_Klawiatura;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class L4Z6KeyPaintApp extends JFrame {

    private JPanel panel;
    private JLabel info;

    public L4Z6KeyPaintApp(){
        setTitle("Kolejna aplikacja do rysowania");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600,600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        info = new JLabel("Naciśnij 'C', żeby zmienić kolor figury. Użyj 'CRTL+C', aby zachować kolor");
        info.setPreferredSize(new Dimension(400,50));
        info.setBackground(Color.lightGray);
        add(info,BorderLayout.NORTH);

        panel = new DrawingPanel();
        add(panel,BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(()->{
            new L4Z6KeyPaintApp().setVisible(true);
        });
    }
}

class DrawingPanel extends JPanel{
    private Rectangle kwadrat = new Rectangle(100,100,60,60);
    private Color kolorAlt = Color.green;
    private Color kolorDef = Color.red;
    private Color kolorKwadratu = Color.red;

    public DrawingPanel(){
        setFocusable(true);
        setBackground(Color.blue);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                char key = Character.toUpperCase(e.getKeyChar());
                if(key=='C'){
                    kolorKwadratu = kolorAlt;
                }
                repaint();
            }

            @Override
            public void keyReleased(KeyEvent e) {
                char key = Character.toUpperCase(e.getKeyChar());
                if(key=='C'){
                    kolorKwadratu = kolorDef;
                }
                repaint();
            }
        });


        getInputMap().put(KeyStroke.getKeyStroke("control C"), "ctrlC");
        getActionMap().put("ctrlC", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                kolorKwadratu = kolorAlt;
                kolorAlt = kolorDef;
                kolorDef = kolorKwadratu;
                repaint();
            }
        });

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(kolorKwadratu);
        g2.fill(kwadrat);

    }
}