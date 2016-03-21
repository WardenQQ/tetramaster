package android.game;

import android.graphics.Canvas;
import android.graphics.Rect;
import game.PlayedCard;

/**
 * Created by mmichels on 3/21/2016.
 */
public abstract class CellEntity {
    public static final int size = 196;

    protected int posX;
    protected int posY;
    protected Rect rect;
    private Rect boundingBox;

    public CellEntity(int x, int y) {
        posX = x;
        posY = y;
        rect = new Rect(0, 0, 196, 196);
        rect.offset(x, y);
        boundingBox = new Rect(-8, -8, 204, 204);
        boundingBox.offset(x, y);
    }

    public void setPosition(int x, int y) {
        rect.offset(x - posX, y - posY);
        boundingBox.offset(x - posX, y - posY);
        posX = x;
        posY = y;
    }

    abstract public void draw(Canvas c);

    public boolean intersect(int x, int y) {
        return boundingBox.contains(x, y);
    }
}
