package android.game;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import game.Pos;

/**
 * Created by mmichels on 3/20/2016.
 */
public class EmptyCell extends CellEntity implements Drawable {
    Paint p = new Paint();

    public EmptyCell(int x, int y) {
        super(x, y);

        p.setColor(Color.rgb(255, 255, 255));
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
