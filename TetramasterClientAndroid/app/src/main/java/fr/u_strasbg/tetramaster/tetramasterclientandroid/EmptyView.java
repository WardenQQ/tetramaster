package fr.u_strasbg.tetramaster.tetramasterclientandroid;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

/**
 * Created by Ricardo on 03/12/2015.
 */
public class EmptyView extends View {
    public boolean clickable;
    public EmptyView(Context context,boolean clickable)
    {
        super(context);
        this.clickable=clickable;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int x=90;
        int y=90;
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        if(this.clickable)
        {
            paint.setColor(Color.YELLOW);
        }
        else
        {
            paint.setColor(Color.WHITE);
        }
        canvas.drawRect(0,0,x,y,paint);
    }
}

