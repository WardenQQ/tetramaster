package android.game;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;


public class BlockCell implements Drawable {
    Paint p;

    public BlockCell() {
        p = new Paint();
        p.setColor(Color.rgb(16, 16, 16));
    }

    @Override
    public void draw(Canvas c, int x, int y) {

        c.drawRect(x, y, x + 196, y + 196, p);
    }
}
