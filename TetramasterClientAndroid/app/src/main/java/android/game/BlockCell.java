package android.game;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;


public class BlockCell extends CellEntity implements Drawable {
    Paint p = new Paint();

    public BlockCell(int x, int y) {
        super(x, y);

        p.setColor(Color.rgb(16, 16, 16));
    }

    @Override
    public void draw(Canvas c) {
        draw(c, 0, 0);
    }

    @Override
    public void draw(Canvas c, int x, int y) {
        c.drawRect(super.rect, p);
    }
}
