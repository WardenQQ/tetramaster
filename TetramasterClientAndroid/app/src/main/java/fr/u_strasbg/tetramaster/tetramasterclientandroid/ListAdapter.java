package fr.u_strasbg.tetramaster.tetramasterclientandroid;

/**
 * Created by Ricardo on 04/12/2015.
 */
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.graphics.drawable.Drawable;

import java.util.Random;
import java.util.Vector;

public class ListAdapter extends BaseAdapter {
    private Context mContext;

    // Keep all Images in array

    public View[] cell =  { null,null,null,null,null

    };

    // Constructor
    public ListAdapter(Context c){
        mContext = c;

        setCardsInList();

    }

    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public Object getItem(int position) {
        return cell[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        /*CardView cardView = new CardView(mContext);
        cardView.setMinimumHeight(90);
        cardView.setMinimumWidth(90);

        //CardView.setImageResource(cell[position]);
        return cardView;*/

        View view = cell[position];
        view.setMinimumWidth(90);
        view.setMinimumHeight(90);
        return view;
    }

    public void setCardsInList()
    {

        Vector<Boolean>vect = new Vector<Boolean>();
        vect.addElement(true);
        vect.addElement(false);
        vect.addElement(false);
        vect.addElement(true);
        vect.addElement(false);
        vect.addElement(true);
        vect.addElement(false);
        vect.addElement(false);
        Card myCard=new Card(vect,1,1,1,"COUCOU");

        cell[0]=new CardView(mContext,myCard);
        cell[1]=new CardView(mContext,myCard);
        cell[2]=new CardView(mContext,myCard);
        cell[3]=new CardView(mContext,myCard);
        cell[4]=new CardView(mContext,myCard);
    }
}