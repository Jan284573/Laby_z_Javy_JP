package L5Z1_Platformowka;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.*;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class GamePanel extends JPanel implements ActionListener {

    // bufor
    Image image;
    // wykreslacz ekranowy
    Graphics2D device;
    // wykreslacz bufora
    Graphics2D buffer;

    private final Timer timer;

    private final AtomicInteger score = new AtomicInteger(0);
    private final BlockingQueue<Integer> scoreEvents = new LinkedBlockingQueue<>();
    private Thread scoreThread;

    private final Entity player = new Entity(10,530,64,52);
    private Image playerImg;

    private final ArrayList<Stone> stoneList = new ArrayList<>();

    private final List<Supplier> suppliers = Collections.synchronizedList(new ArrayList<>());
    private final List<Thread> supplierThreads = new ArrayList<>();

    private final List<Bone> boneList = Collections.synchronizedList(new ArrayList<>());

    private boolean running = true;


    public GamePanel(){
        setBackground(Color.lightGray);
        setSize(800,600);
        setFocusable(true);

        int delay = 14;
        timer = new Timer(delay, this);

        stoneList.add(new Stone(400,270,40,30));
        stoneList.add(new Stone(330,500,40,5));
        stoneList.add(new Stone(450,450,250,10));
        stoneList.add(new Stone(510,400,78,12));
        stoneList.add(new Stone(0,375,250,27));
        stoneList.add(new Stone(100,550,100,20));
        stoneList.add(new Stone(600,340,100,22));
        stoneList.add(new Stone(280,440,13,6));
        stoneList.add(new Stone(100,250,500,30));
        stoneList.add(new Stone(0,315,30,40));
        stoneList.add(new Stone(0,100,350,50));
        stoneList.add(new Stone(430,100,370,50));
        stoneList.add(new Stone(426,190,4,4));
        stoneList.add(new Stone(350,140,4,4));
        stoneList.add(new Stone(670,300,40,30));


        suppliers.add(new Supplier());
        suppliers.add(new Supplier());

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
        device = (Graphics2D) getGraphics();
        device.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        requestFocusInWindow();

        try{
            playerImg = ImageIO.read(getClass().getResource("player.png"));
        }catch (Exception e){
            e.printStackTrace();
        }

        try{
            Bone.boneIMG = ImageIO.read(getClass().getResource("bone.png"));
        }catch (Exception e){
            e.printStackTrace();
        }

        startThreads();
        timer.start();
    }

    private void startThreads(){
        running = true;

        scoreThread = new Thread(new ScoreUpdater(score, scoreEvents, running), "score-updater");
        scoreThread.start();

        supplierThreads.clear();
        synchronized (suppliers) {
            for (int i = 0; i < suppliers.size(); i++) {
                Supplier s = suppliers.get(i);
                Thread t = new Thread(() -> supplierLoop(s), "supplier-" + i);
                supplierThreads.add(t);
                t.start();
            }
        }
    }

    private void supplierLoop(Supplier s){
        Random r = new Random();


        while (running && !Thread.currentThread().isInterrupted()){
            try {
                Thread.sleep(1800 + r.nextInt(1800));
            }
            catch (InterruptedException ignored){}

            if(!running) break;
            if(boneList.size()>=8) continue;

            for (int attempt = 0; attempt<40;attempt++){
                int boneX = r.nextInt(Math.max(1, getWidth() - Bone.width));
                int boneY = r.nextInt(Math.max(1, getHeight() - Bone.height));

                Bone candidate = new Bone(boneX,boneY);

                if(candidate.intersects(player)) continue;
                if(intersectsObstacle(candidate)) continue;

                boneList.add(candidate);
                break;

            }
        }
    }

    private boolean intersectsObstacle(Rectangle r){
        for (Stone s: stoneList) {
            if (r.intersects(s)) return true;
        }
        for (Bone b: boneList) {
            if(r.intersects(b)) return true;
        }
        return false;
    }




    //wszelkie akcje do timera i buforu
    @Override
    public void actionPerformed(ActionEvent e) {
        //animacja lewo prawo
        if (player.left) player.x -= 3;
        if (player.right) player.x += 3;

        player.beginFrame(getHeight());

        player.collision(new Rectangle(0,0,10,getHeight())); //lewa
        player.collision(new Rectangle(getWidth() - 10, 0 ,10 , getHeight())); // prawa

        //kamienie rys. + kolizje
        buffer.setColor(Stone.kolor);
        for(Stone s :stoneList) {
            buffer.fill(s);
            player.collision(s);
        }

        player.useVerticalPhysics(1);

        //Kosci
        synchronized (boneList) {
            for (int i = boneList.size() - 1; i >= 0; i--) {
                Bone b = boneList.get(i);
                buffer.drawImage(Bone.boneIMG, b.x,b.y,Bone.width,Bone.height, null);

                if (player.intersects(b)) {
                    scoreEvents.offer(10);
                    boneList.remove(i);
                }
            }
        }

        //Rysowanie gracza
        buffer.drawImage(
                playerImg,
                player.x, player.y,
                player.width, player.height,
                null
        );

        //Wynik
        buffer.setColor(Color.black);
        buffer.setFont(new Font("Arial",Font.BOLD,18));
        buffer.drawString("Wynik: "+score.get(),10,22);


        device.drawImage(image, 0, 0, null);
        buffer.clearRect(0, 0, getWidth(), getHeight());
    }
}