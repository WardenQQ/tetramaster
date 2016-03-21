package fr.u_strasbg.tetramaster.tetramasterclientandroid;

import android.content.Context;
import android.graphics.*;
import android.view.View;
import android.widget.ListView;
import fr.u_strasbg.tetramaster.shared.Card;

public class CardView extends View {

    public fr.u_strasbg.tetramaster.shared.Card myCard;
    public int joueur;
    public boolean clickable;
    private int cardWidth, cardHeight, arrowSize;
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
        int x=getCardWidth();
        int y=getCardHeight();
        arrowSize = x/20;
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
            Point pt2=new Point(x/2+arrowSize,arrowSize);
            Point pt3=new Point(x/2-arrowSize,arrowSize);

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
            Point pt1=new Point(x-arrowSize,0);
            Point pt2=new Point(x,0);
            Point pt3=new Point(x,arrowSize);

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
            Point pt2=new Point(x-arrowSize,y/2-arrowSize);
            Point pt3=new Point(x-arrowSize,y/2+arrowSize);

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
            Point pt2=new Point(x-arrowSize,y);
            Point pt3=new Point(x,y-arrowSize);

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
            Point pt2=new Point(x/2+arrowSize,y-arrowSize);
            Point pt3=new Point(x/2-arrowSize,y-arrowSize);

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
            Point pt2=new Point(arrowSize,y);
            Point pt3=new Point(0,y-arrowSize);

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
            Point pt2=new Point(arrowSize,y/2+arrowSize);
            Point pt3=new Point(arrowSize,y/2-arrowSize);

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
            Point pt2=new Point(arrowSize,0);
            Point pt3=new Point(0,arrowSize);

            Path path = new Path();
            path.setFillType(Path.FillType.EVEN_ODD);
            path.moveTo(pt1.x, pt1.y);
            path.lineTo(pt2.x,pt2.y);
            path.lineTo(pt3.x,pt3.y);
            path.close();
            canvas.drawPath(path,paintArray);
        }
        Paint paintText = new Paint();
        paintText.setColor(Color.YELLOW);
        paintText.setTextSize(16);
        String atqHex=Integer.toHexString(myCard.getAttack()).toUpperCase();
        String physDefHex=Integer.toHexString(myCard.getPhysicalDef()).toUpperCase();
        String magDefHex=Integer.toHexString(myCard.getMagicalDef()).toUpperCase();
        String type = myCard.getPowerType().toUpperCase();
        String attributes = atqHex+type+physDefHex+magDefHex;
        Rect bounds = new Rect();
        paintText.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
        paintText.setTextSize(20);
        paintText.getTextBounds(attributes, 0, attributes.length(), bounds);
        int offset = (getCardWidth() / 2) - (bounds.width() / 2);
        canvas.drawText(attributes,offset,y/2,paintText);
    }

    public int getCardWidth() {
        return this.cardWidth;
    }

    public void setCardWidth(int windowWidth){
        this.cardWidth = windowWidth;
    }

    public int getCardHeight() {
        return this.cardHeight;
    }

    public void setCardHeight(int windowHeight){
        this.cardHeight = windowHeight;
    }

}
