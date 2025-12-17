package L5Z1_Platformowka;

import java.awt.*;

public class Entity extends Rectangle {
    public boolean left,right,onGround;
    public int vy =0;
    public int groundY = 0, topY = 0;

    public Entity(int x, int y, int width, int height){
        super(x,y,width,height);
    }

    public void beginFrame(int panelHeight){
        groundY = panelHeight - height -10;
        topY = 0;
        onGround = false;
    }

    void collision(Rectangle r){
        int rLeft = r.x;
        int rRight = r.x + r.width;
        int rTop = r.y;
        int rBottom = r.y + r.height;

        boolean overlapX = x < rRight && x + width > rLeft;
        if (!overlapX) return;

        //kolizje góra dół
        if (vy >= 0) {
            int thisBottom = y + height;
            int nextBottom = y + height + vy;

            // jeśli dół przekroczy górę przeszkody
            if (thisBottom <= rTop && nextBottom >= rTop) {
                groundY = Math.min(groundY, rTop - height);
            }
        } else {
            int thisTop = y;
            int nextTop = y + vy;

            // jeśli góra przekroczy dół przeszkody
            if (thisTop >= rBottom && nextTop <= rBottom) {
                topY = Math.max(topY, rBottom);
            }
        }

        // kolizje lewo prawo
        boolean overlapY = y < rBottom && y + height > rTop;
        if (!overlapY) return;

        // minimalne wypchnięcie w lewo/prawo
        int pushLeft = (x + width) - rLeft;
        int pushRight = rRight - x;

        if (pushLeft > 0 && pushRight > 0) {
            if (pushLeft < pushRight) x -= pushLeft;
            else x += pushRight;
        }
    }


    public void useVerticalPhysics(int gravity){
        vy += gravity;
        y += vy;

        if (y < topY){
            y = topY;
            if(vy < 0) vy = 0;
        }

        if(y >= groundY){
            y = groundY;
            vy = 0;
            onGround = true;
        }
    }
}
