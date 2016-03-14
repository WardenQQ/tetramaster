package fr.u_strasbg.tetramaster.tetramasterclientandroid;

import android.content.Context;
import android.graphics.*;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
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
        super.onDraw(canvas);
        int x=300;
        int y=300;
        Paint paintCard = new Paint();
        paintCard.setStyle(Paint.Style.FILL);
        canvas.drawRect(0, 0, x, y, paintCard);
        int def = myCard.getMagicalDef();
        int atq = myCard.getAttack();
        Paint paintText = new Paint();
        paintText.setColor(Color.YELLOW);
        paintText.setTextSize(16);
        canvas.drawText(String.valueOf(atq),70,y/2,paintText);
        canvas.drawText(String.valueOf(def),30,y/2,paintText);
    }
}
