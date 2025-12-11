package L4_Swing_Mysz_I_Klawiatura;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

public class L4ZDodatkowe extends JFrame {

    private JPanel panelDoUciekania;
    private JPanel kanwa;
    private JPanel infoGrid;
    private JLabel infokanwa;
    private JLabel infoucieczka;

    public L4ZDodatkowe(){
        setTitle("Zadanie Dodatkowe");
        setSize(800,600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        panelDoUciekania = new EscapePanel();
        panelDoUciekania.setBackground(Color.orange);
        panelDoUciekania.setLayout(null);
        panelDoUciekania.setPreferredSize(new Dimension(400,600));
        panelDoUciekania.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        add(panelDoUciekania,BorderLayout.EAST);

        kanwa = new CanvaPanel();
        kanwa.setLayout(null);
        kanwa.setBorder(BorderFactory.createLineBorder(Color.gray, 2));
        add(kanwa,BorderLayout.CENTER);

        infoGrid = new JPanel();
        infoGrid.setLayout(new GridLayout(1,2));
        infoGrid.setPreferredSize(new Dimension(400,14));
        add(infoGrid, BorderLayout.NORTH);

        infokanwa = new JLabel("Kanwa do rysowania. 'O'- koło, 'K'- kwadrat");
        infoGrid.add(infokanwa);

        infoucieczka = new JLabel("Uciekający Przycisk");
        infoGrid.add(infoucieczka);

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(()->{
            new L4ZDodatkowe().setVisible(true);
        });
    }
}

class CanvaPanel extends JPanel {
    private ArrayList<Shape> figureList = new ArrayList<>();
    private Shape actualShape = null;
    private Rectangle kwadrat = new Rectangle(0,0,60,60);
    private Ellipse2D.Double kolko = new Ellipse2D.Double(0,0,60,60);
    private int x=0, y=0;

    public CanvaPanel() {
        setBackground(Color.white);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                requestFocusInWindow();
                if (actualShape != null) {
                    figureList.add(actualShape);
                    actualShape = null;
                }
                repaint();
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                x = e.getX() -30;
                y = e.getY() -30;
                if (actualShape == kwadrat) {
                    kwadrat.x = x;
                    kwadrat.y = y;
                } else if (actualShape == kolko) {
                    kolko.x = x;
                    kolko.y = y;
                }else{
                    return;
                }
                repaint();
            }
        });

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char key = Character.toUpperCase(e.getKeyChar());
                if (key == 'O') {
                    kolko = new Ellipse2D.Double(x, y, 60, 60);
                    actualShape = kolko;
                } else if (key == 'K') {
                    kwadrat = new Rectangle(x, y, 60, 60);
                    actualShape = kwadrat;
                }
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(Color.gray);
        for (int i = 0; i < figureList.size(); i++) {
            g2.fill(figureList.get(i));
        }

        g2.setColor(Color.cyan);
        if(actualShape == kwadrat){
            g2.fill(kwadrat);
        } else if (actualShape == kolko) {
            g2.fill(kolko);
        }
    }
}

class EscapePanel extends JPanel{
    private JButton escapeButton = new JButton("Klik");
    private boolean trap = false;
    private int klik = 0;

    public EscapePanel(){
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                requestFocusInWindow();
            }
        });

        escapeButton.setBounds(100,100,100,30);
        add(escapeButton);

        escapeButton.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                int btnW = escapeButton.getWidth();
                int btnX = escapeButton.getX();
                if(btnX+btnW>=getWidth()-20){
                    trap = true;
                }

                if(!trap){
                    int mX = e.getX();

                    int mY = e.getY();
                    int nX;
                    int nY;

                    if(escapeButton.contains(mX,mY)){
                        int btnH = escapeButton.getHeight();
                        int btnY = escapeButton.getY();

                        if (mX>=btnW/2){
                            nX = btnX-5;
                        }else {
                            nX = btnX+5;
                        }

                        if(nX<0){
                            nX=0;
                        } else if (nX>getWidth()-btnW) {
                            nX=getWidth()-btnW;
                        }

                        if (mY>=btnH/2){
                            nY = btnY-5;
                        }else {
                            nY = btnY+5;
                        }

                        if(nY<0){
                            nY=0;
                        } else if (nY>getHeight()-btnH) {
                            nY=getHeight()-btnH;
                        }

                        escapeButton.setLocation(nX,nY);
                        repaint();
                    }
                }
            }
        });

        escapeButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (trap){
                    trap = false;
                    escapeButton.setLocation(100,100);
                    escapeButton.setText("Klik"+ ++klik);
                }
            }


        });

    }
}
