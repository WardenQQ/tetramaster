package android.game;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import game.Card;
import game.Pos;

/**
 * Created by mmichels on 3/20/2016.
 */
public class CardCell extends CellEntity implements Drawable {
    public Card card;
    public int team;

    private Paint p;

    public CardCell(int x, int y, Card c, int team) {
        super(x, y);

        card = c;
        this.team = team;

        p = new Paint();
    }

    @Override
    public void draw(Canvas c, int x, int y) {
        p.setColor(Color.rgb(16, 16, 192));
        c.drawRect(super.rect, p);

        p.setColor(Color.rgb(192, 192, 192));

        int xCen = super.rect.centerX();
        int yCen = super.rect.centerY();

        for (Pos arr : scala.collection.JavaConversions.seqAsJavaList(card.arrows())) {
            int xoff = arr.x() >= 0 ? arr.x() * 76 : arr.x() * 75;
            int yoff = arr.x() >= 0 ? arr.y() * 76 : arr.y() * 75;
            c.drawCircle(xCen + xoff, yCen + yoff, 15.0f, p);
        }
    }

    @Override
    public void draw(Canvas c) {
        draw(c, 0, 0);
    }
}
