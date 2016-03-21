package android.game;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import game.Card;
import game.Pos;

/**
 * Created by mmichels on 3/20/2016.
 */
public class CardCell implements Drawable {
    private Card card;

    private Paint p;

    public CardCell(Card c) {
        card = c;

        p = new Paint();

    }

    @Override
    public void draw(Canvas c, int x, int y) {
        p.setColor(Color.rgb(16, 16, 192));
        c.drawRect(x, y, x + 196, y + 196, p);

        p.setColor(Color.rgb(192, 192, 192));

        for (Pos arr : scala.collection.JavaConversions.seqAsJavaList(card.arrows())) {
            int xoff = arr.x() >= 0 ? arr.x() * 76 : arr.x() *75;
            int yoff = arr.x() >= 0 ? arr.y() * 76 : arr.y() *75;
            c.drawCircle(x + 96 + xoff, y + 96 + yoff, 15.0f, p);
        }
    }
}
