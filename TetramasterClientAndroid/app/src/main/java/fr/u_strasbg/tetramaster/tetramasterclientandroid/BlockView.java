package fr.u_strasbg.tetramaster.tetramasterclientandroid;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

/**
 * Created by Ricardo on 03/12/2015.
 */
public class BlockView extends View {
    public BlockView(Context context)
    {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int x=100;
        int y=100;
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.BLACK);
        canvas.drawRect(0,0,x,y,paint);
    }
}
