package fr.u_strasbg.tetramaster.tetramasterclientandroid;

/**
 * Created by Ricardo on 02/12/2015.
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

public class GridAdapter extends BaseAdapter {
    private Context mContext;
    public Vector<Integer> positions=new Vector<Integer>();

    // Keep all Images in array

    public View[] cell =  { null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null

    };

    // Constructor
    public GridAdapter(Context c){
        mContext = c;
        for (int i=0;i<8;i++)
        {
            cell[i]=new EmptyView(mContext,true);
        }
        for (int i=8;i<16;i++)
        {
            cell[i]=new EmptyView(mContext,false);
        }
        setObstacles();
        getPosClickableFromServ();
        for(int j=0;j<positions.size();j++)
        {

        }

    }

    @Override
    public int getCount() {
        return 16;
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

    public void setObstacles()
    {
        for(int i=0;i<4;i++)
        {
            Random rand = new Random();
            int nombreAleatoire = rand.nextInt(16);
            cell[nombreAleatoire] = new BlockView(mContext);
        }
        Boolean[] vect = new Boolean[8];
        vect[0]=true;
        vect[1]=true;
        vect[2]=true;
        vect[3]=true;
        vect[4]=true;
        vect[5]=true;
        vect[6]=true;
        vect[7]=true;
        Card myCard=new Card(vect,1,1,1,"COUCOU");
        cell[12]=new CardView(mContext,myCard,1,true);
        cell[13]=new CardView(mContext,myCard,2,false);
    }

    public Vector<Integer> getPosClickableFromServ()
    {
        positions.add(0);
        positions.add(3);
        positions.add(5);
        positions.add(8);
        positions.add(9);
        return positions;
    }
}