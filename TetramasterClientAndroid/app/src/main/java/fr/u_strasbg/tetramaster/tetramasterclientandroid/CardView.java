package fr.u_strasbg.tetramaster.tetramasterclientandroid;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.View;

import java.util.Vector;

/**
 * Created by Ricardo on 03/12/2015.
 */
public class CardView extends View {

    Card myCard;
    public CardView(Context context,Card card)
    {
        super(context);
        myCard=card;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int x=90;
        int y=90;
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.GREEN);
        canvas.drawRect(0, 0, x, y, paint);
        Paint paintArray = new Paint();
        paintArray.setStyle(Paint.Style.FILL_AND_STROKE);
        paintArray.setStrokeWidth(2);
        paintArray.setColor(Color.YELLOW);
        paintArray.setAntiAlias(true);
        if (myCard.getArrays().elementAt(0)==true)
        {
            Point pt1=new Point(x/2,0);
            Point pt2=new Point(x/2+10,10);
            Point pt3=new Point(x/2-10,10);

            Path path = new Path();
            path.setFillType(Path.FillType.EVEN_ODD);
            path.moveTo(pt1.x, pt1.y);
            path.lineTo(pt2.x,pt2.y);
            path.lineTo(pt3.x,pt3.y);
            path.close();
            canvas.drawPath(path,paintArray);
        }
        if (myCard.getArrays().elementAt(1)==true)
        {
            Point pt1=new Point(x-10,0);
            Point pt2=new Point(x,0);
            Point pt3=new Point(x,10);

            Path path = new Path();
            path.setFillType(Path.FillType.EVEN_ODD);
            path.moveTo(pt1.x, pt1.y);
            path.lineTo(pt2.x,pt2.y);
            path.lineTo(pt3.x,pt3.y);
            path.close();
            canvas.drawPath(path,paintArray);
        }
        if (myCard.getArrays().elementAt(2)==true)
        {
            Point pt1=new Point(x,y/2);
            Point pt2=new Point(x-10,y/2-10);
            Point pt3=new Point(x-10,y/2+10);

            Path path = new Path();
            path.setFillType(Path.FillType.EVEN_ODD);
            path.moveTo(pt1.x, pt1.y);
            path.lineTo(pt2.x,pt2.y);
            path.lineTo(pt3.x,pt3.y);
            path.close();
            canvas.drawPath(path,paintArray);
        }
        if (myCard.getArrays().elementAt(3)==true)
        {
            Point pt1=new Point(x,y);
            Point pt2=new Point(x-10,y);
            Point pt3=new Point(x,y-10);

            Path path = new Path();
            path.setFillType(Path.FillType.EVEN_ODD);
            path.moveTo(pt1.x, pt1.y);
            path.lineTo(pt2.x,pt2.y);
            path.lineTo(pt3.x,pt3.y);
            path.close();
            canvas.drawPath(path,paintArray);
        }
        if (myCard.getArrays().elementAt(4)==true)
        {
            Point pt1=new Point(x/2,y);
            Point pt2=new Point(x/2+10,y-10);
            Point pt3=new Point(x/2-10,y-10);

            Path path = new Path();
            path.setFillType(Path.FillType.EVEN_ODD);
            path.moveTo(pt1.x, pt1.y);
            path.lineTo(pt2.x,pt2.y);
            path.lineTo(pt3.x,pt3.y);
            path.close();
            canvas.drawPath(path,paintArray);
        }
        if (myCard.getArrays().elementAt(5)==true)
        {
            Point pt1=new Point(0,y);
            Point pt2=new Point(10,y);
            Point pt3=new Point(0,y-10);

            Path path = new Path();
            path.setFillType(Path.FillType.EVEN_ODD);
            path.moveTo(pt1.x, pt1.y);
            path.lineTo(pt2.x,pt2.y);
            path.lineTo(pt3.x,pt3.y);
            path.close();
            canvas.drawPath(path,paintArray);
        }
        if (myCard.getArrays().elementAt(6)==true)
        {
            Point pt1=new Point(0,y/2);
            Point pt2=new Point(10,y/2+10);
            Point pt3=new Point(10,y/2-10);

            Path path = new Path();
            path.setFillType(Path.FillType.EVEN_ODD);
            path.moveTo(pt1.x, pt1.y);
            path.lineTo(pt2.x,pt2.y);
            path.lineTo(pt3.x,pt3.y);
            path.close();
            canvas.drawPath(path,paintArray);
        }
        if (myCard.getArrays().elementAt(7)==true)
        {
            Point pt1=new Point(0,0);
            Point pt2=new Point(10,0);
            Point pt3=new Point(0,10);

            Path path = new Path();
            path.setFillType(Path.FillType.EVEN_ODD);
            path.moveTo(pt1.x, pt1.y);
            path.lineTo(pt2.x,pt2.y);
            path.lineTo(pt3.x,pt3.y);
            path.close();
            canvas.drawPath(path,paintArray);
        }
    }

}
