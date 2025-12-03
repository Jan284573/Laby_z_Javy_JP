package L4_Swing_Mysz_I_Klawiatura;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;

public class L4Z4Paint2 extends JFrame {

    public L4Z4Paint2(){
        setTitle("Paint");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600,600);
        setLocationRelativeTo(null);

        add(new Paint2Panel());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(()->{
            new L4Z4Paint2().setVisible(true);

        });
    }
}

class Paint2Panel extends JPanel {

    private Rectangle kwadrat = new Rectangle(100,100,60,60);
    private Ellipse2D.Double kolko = new Ellipse2D.Double(300,300,80,80);

    private Shape animatedShape = null;
    private Timer timer;

    private int dx, dy, stepsLeft;

    public Paint2Panel(){
        setBackground(Color.lightGray);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int mx = e.getX();
                int my = e.getY();

                if (kwadrat.contains(mx,my)) {
                    animatedShape = kwadrat;
                    dx = mx - kwadrat.x;
                    dy = my - kwadrat.y;
                } else if (kolko.contains(mx,my)) {
                    animatedShape = kolko;
                    dx = (int) (mx - kolko.x);
                    dy = (int) (my - kolko.y);
                } else{
                    animatedShape = null;
                }

                if (timer != null && timer.isRunning()){
                    timer.stop();
                }

                Rectangle bounds = animatedShape.getBounds();
                double centerX = bounds.getCenterX();

                if (mx < centerX){
                    dx = 0;
                    dy = 5;
                }else{
                    dx = 5;
                    dy = 0;
                }

                stepsLeft = 20;

                timer = new Timer(30, ae ->{
                    if (stepsLeft<=0){
                        timer.stop();
                        return;
                    }
                    if (animatedShape == kwadrat){
                        kwadrat.x += dx;
                        kwadrat.y += dy;
                    } else if (animatedShape == kolko) {
                        kolko.x += dx;
                        kolko.y += dy;
                    }

                    stepsLeft--;
                    repaint();
                });
                timer.start();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(Color.green);
        g2.fill(kolko);

        g2.setColor(Color.red);
        g2.fill(kwadrat);

    }
}
