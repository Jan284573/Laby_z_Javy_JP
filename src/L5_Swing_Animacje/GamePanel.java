package L5_Swing_Animacje;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class GamePanel extends JPanel implements ActionListener {

    // bufor
    Image image;
    // wykreslacz ekranowy
    Graphics2D device;
    // wykreslacz bufora
    Graphics2D buffer;

    private final Timer timer;
    private static final int score = 0;

    private final Entity player = new Entity(10,530,64,52);
    private Image playerImg;

    private final ArrayList<Stone> stoneList = new ArrayList<>();


    public GamePanel(){
        setBackground(Color.lightGray);
        setSize(800,600);
        setFocusable(true);

        int delay = 14;
        timer = new Timer(delay, this);


        stoneList.add(new Stone(100,520,100,30));
        stoneList.add(new Stone(100,30,120,35));
        stoneList.add(new Stone(400,230,40,30));


        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_A -> player.left = true;
                    case KeyEvent.VK_D -> player.right = true;
                    case KeyEvent.VK_W -> {
                        if (player.onGround){
                            player.vy -=12;
                            player.onGround= false;
                        }
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_A -> player.left = false;
                    case KeyEvent.VK_D -> player.right = false;
                }
            }
        });


    }

    //Mądra inicjalizacje do tego buforowania
    public void initialize() {
        int width = getWidth();
        int height = getHeight();

        image = createImage(width, height);
        buffer = (Graphics2D) image.getGraphics();
        buffer.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        requestFocusInWindow();
        device = (Graphics2D) getGraphics();
        device.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        try{
            playerImg = ImageIO.read(getClass().getResource("player.png"));
        }catch (Exception e){
            e.printStackTrace();
        }

        if (timer.isRunning()) {
            timer.stop();
        } else {
            timer.start();
        }
    }


//wszelkie akcje do timera i buforu
    @Override
    public void actionPerformed(ActionEvent e) {
        //animacja lewo prawo
        if (player.left) player.x -= 3;
        if (player.right) player.x += 3;
        player.collision(getWidth()-10,10, getY(),getHeight());

        //kamienie rys. + kolizje
        buffer.setColor(Stone.kolor);
        for(Stone s :stoneList) {
            buffer.fill(s);

            player.collision(s.x,s.x+s.width,s.y,s.height);
        }

        //animacja skakanie
        int GRAVITY = 1;
        player.vy += GRAVITY;
        player.y += player.vy;

        int groundY = getHeight() - player.height - 10 - Stone.stoneHeight;
        if (player.y >= groundY) {
            player.y = groundY;
            player.vy = 0;
            player.onGround = true;
        }

        //Rysowanie

        if (playerImg != null) {
            buffer.drawImage(
                    playerImg,
                    player.x, player.y,
                    player.width, player.height,
                    null
            );
        } else {
            // fallback – gdyby obrazek się nie załadował
            buffer.setColor(Color.red);
            buffer.fill(player);
        }


        device.drawImage(image, 0, 0, null);
        buffer.clearRect(0, 0, getWidth(), getHeight());
    }
}