package android.game;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import game.Pos;

/**
 * Created by mmichels on 3/20/2016.
 */
public class EmptyCell implements Drawable {
    Paint p;

    public EmptyCell() {
        p = new Paint();
        p.setColor(Color.rgb(255, 255, 255));
    }

    @Override
    public void draw(Canvas c, int x, int y) {

        c.drawRect(x, y, x + 196, y + 196, p);
    }
}
