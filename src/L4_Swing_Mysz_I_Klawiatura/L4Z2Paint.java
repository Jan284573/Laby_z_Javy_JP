package L4_Swing_Mysz_I_Klawiatura;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;

public class L4Z2Paint extends JFrame {

    public L4Z2Paint(){
        setTitle("Paint");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600,600);
        setLocationRelativeTo(null);

        add(new PaintPanel());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(()->{
            new L4Z2Paint().setVisible(true);

        });
    }
}

class PaintPanel extends JPanel {

    private Rectangle kwadrat = new Rectangle(100,100,80,80);
    private Ellipse2D.Double kolko = new Ellipse2D.Double(300,300,60,60);

    private Shape selectedShape = null;
    private int X,Y;

    public PaintPanel(){
        setBackground(Color.lightGray);

        MouseAdapter mouseHandler = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int mx = e.getX();
                int my = e.getY();

                if (kwadrat.contains(mx,my)) {
                    selectedShape = kwadrat;
                    X = mx - kwadrat.x;
                    Y = my - kwadrat.y;
                } else if (kolko.contains(mx,my)) {
                    selectedShape = kolko;
                    X = (int) (mx - kolko.x);
                    Y = (int) (my - kolko.y);
                } else{
                    selectedShape = null;
                }
            }

            @Override
            public void mouseDragged(MouseEvent e){
                if (selectedShape == null) return;
                int mx = e.getX();
                int my = e.getY();

                if(selectedShape == kwadrat){
                    kwadrat.x = mx-X;
                    kwadrat.y = my-Y;
                } else if (selectedShape == kolko) {
                    kolko.x = mx-X;
                    kolko.y = my-Y;
                }
                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e){
                selectedShape = null;
            }
        };

        addMouseListener(mouseHandler);
        addMouseMotionListener(mouseHandler);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(Color.red);
        g2.fill(kwadrat);

        g2.setColor(Color.green);
        g2.fill(kolko);

        repaint();
    }
}
