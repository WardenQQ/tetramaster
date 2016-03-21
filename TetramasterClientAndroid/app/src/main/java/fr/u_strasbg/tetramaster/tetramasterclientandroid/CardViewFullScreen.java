package fr.u_strasbg.tetramaster.tetramasterclientandroid;

import android.content.Context;
import android.graphics.*;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import fr.u_strasbg.tetramaster.shared.Card;


public class CardViewFullScreen extends View {
    private static final String TAG = "MyDebug";
    private int windowWidth, windowHeight;
    public fr.u_strasbg.tetramaster.shared.Card myCard;
    public CardViewFullScreen(Context context, Card card, int windowWidth, int windowHeight)
    {
        super(context);
        setWindowWidth(windowWidth);
        setWindowHeight(windowHeight);
        myCard=card;
        Log.d(TAG, "Im here :");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int x=getWindowWidth();
        int y=getWindowHeight();
        Paint paintCard = new Paint();
        paintCard.setStyle(Paint.Style.FILL);
        canvas.drawRect(0, 0, x, y, paintCard);
        String atqHex=Integer.toHexString(myCard.getAttack()).toUpperCase();
        String physDefHex=Integer.toHexString(myCard.getPhysicalDef()).toUpperCase();
        String magDefHex=Integer.toHexString(myCard.getMagicalDef()).toUpperCase();
        String type = myCard.getPowerType().toUpperCase();
        String attributes = atqHex+type+physDefHex+magDefHex;
        Paint paintText = new Paint();
        paintText.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
        paintText.setColor(Color.YELLOW);
        paintText.setTextSize(32);
        Rect bounds = new Rect();
        paintText.getTextBounds(attributes, 0, attributes.length(), bounds);
        int offset = (canvas.getWidth() / 2) - (bounds.width() / 2);
        canvas.drawText(attributes, offset,y-20,paintText);
    }

    public int getWindowWidth() {
        return this.windowWidth;
    }

    public void setWindowWidth(int windowWidth){
        this.windowWidth = windowWidth;
    }

    public int getWindowHeight() {
        return this.windowHeight;
    }

    public void setWindowHeight(int windowHeight){
        this.windowHeight = windowHeight;
    }
}
