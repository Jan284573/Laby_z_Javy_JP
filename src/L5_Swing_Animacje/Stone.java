package L5_Swing_Animacje;

import java.awt.*;

public class Stone extends Rectangle {
    public static Color kolor = Color.gray;

    public Stone(int x, int y, int width, int height){
        super(x,y,width,height);
    }

    void ifHitbox(Rectangle entity){
        int eX = entity.x;
        int eY = entity.y;
        int eW = entity.width;
        int eH = entity.height;

        if (eX+eW==x){
            eX= x+width;
        }else {
            eX= x-entity.width;
        }

        if (eY>=y/2){
            eY = y+height;
        }else {
            eY = y-entity.height;
        }

        entity.setLocation(eX,eY);

    }

}
