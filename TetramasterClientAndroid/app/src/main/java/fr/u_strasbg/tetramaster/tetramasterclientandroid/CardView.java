package fr.u_strasbg.tetramaster.tetramasterclientandroid;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.view.View;
import fr.u_strasbg.tetramaster.shared.Card;

/**
 * Created by Ricardo on 03/12/2015.
 */
public class CardView extends View {

    public fr.u_strasbg.tetramaster.shared.Card myCard;
    public int joueur;
    public boolean clickable;
    public CardView(Context context, Card card, int joueur, boolean clickable)
    {
        super(context);
        myCard=card;
        this.joueur=joueur;
        this.clickable=clickable;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int x=100;
        int y=100;
        Paint paintCard = new Paint();
        paintCard.setStyle(Paint.Style.FILL);
        if(this.joueur==0)
        {
            paintCard.setColor(Color.BLUE);
        }
        else if(this.joueur==1)
        {
            paintCard.setColor(Color.RED);
        }
        canvas.drawRect(0, 0, x, y, paintCard);
        Paint paintClickable = new Paint();
        paintClickable.setStyle(Paint.Style.FILL);
        paintClickable.setColor(Color.BLACK);
        if(this.clickable)
        {
            canvas.drawCircle(x/2,y/2,15,paintClickable);
        }

        Paint paintArray = new Paint();
        paintArray.setStyle(Paint.Style.FILL_AND_STROKE);
        paintArray.setStrokeWidth(2);
        paintArray.setColor(Color.YELLOW);
        paintArray.setAntiAlias(true);
        if(myCard.getArrays()[0])
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
        if (myCard.getArrays()[1])
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
        if (myCard.getArrays()[2])
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
        if (myCard.getArrays()[3])
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
        if (myCard.getArrays()[4])
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
        if (myCard.getArrays()[5])
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
        if (myCard.getArrays()[6])
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
        if (myCard.getArrays()[7])
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
        int def = myCard.getMagicalDef();
        int atq = myCard.getAttack();
        Paint paintText = new Paint();
        paintText.setColor(Color.YELLOW);
        paintText.setTextSize(16);
        canvas.drawText(String.valueOf(atq),70,y/2,paintText);
        canvas.drawText(String.valueOf(def),30,y/2,paintText);
    }

}
