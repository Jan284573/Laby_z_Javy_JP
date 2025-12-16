package L5_Swing_Animacje;

import java.awt.*;

public class Entity extends Rectangle {
    public boolean left,right,onGround;
    public int vy =0;
    public int bonusHeight = 0;

    public Entity(int x, int y, int width, int height){
        super(x,y,width,height);
    }

    void collision(int obLeft,int obRight, int obY, int obHeight){
        if (y < obY+obHeight && y+height > obY){
            if (x+width > obLeft && x<obLeft) x = obLeft-width;
            if (x < obRight && x+width> obRight) x = obRight;
        } else if (y-height < obY && x < obRight && x > obLeft) {
            bonusHeight = obY;
        }
    }
}
