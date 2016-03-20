package fr.u_strasbg.tetramaster.tetramasterclientandroid;

import android.content.Context;
import android.graphics.*;
import android.util.Log;
import android.view.View;
import fr.u_strasbg.tetramaster.shared.Card;


public class CardViewFullScreen extends View {
    private static final String TAG = "MyDebug";
    public fr.u_strasbg.tetramaster.shared.Card myCard;
    public CardViewFullScreen(Context context, Card card)
    {
        super(context);
        myCard=card;
        Log.d(TAG, "Im here :");

    }

    @Override
    protected void onDraw(Canvas canvas) {
        int x=400;
        int y=400;
        Paint paintCard = new Paint();
        paintCard.setStyle(Paint.Style.FILL);
        canvas.drawRect(0, 0, x, y, paintCard);
        String atqHex=Integer.toHexString(myCard.getAttack()).toUpperCase();
        String physDefHex=Integer.toHexString(myCard.getPhysicalDef()).toUpperCase();
        String magDefHex=Integer.toHexString(myCard.getMagicalDef()).toUpperCase();
        String type = myCard.getPowerType().toUpperCase();
        String attributes = atqHex+type+physDefHex+magDefHex;
        Paint paintText = new Paint();
        paintText.setColor(Color.YELLOW);
        paintText.setTextSize(16);
        canvas.drawText(attributes,x/2-30,y-20,paintText);
    }
}
