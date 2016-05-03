package fr.u_strasbg.tetramaster.tetramasterclientandroid;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import fr.u_strasbg.tetramaster.shared.Card;

import java.util.Random;
import java.util.Vector;

public class GridAdapter extends BaseAdapter {
    private Context mContext;
    public Vector<Integer> positions=new Vector<Integer>();

    // Keep all Images in array

    public View[] cell =  new View[16];

    // Constructor
    public GridAdapter(Context c){
        mContext = c;
        for (int i=0;i<16;i++)
        {
            cell[i]=new EmptyView(mContext, false);
        }
    }

    public void addBlockCells(int[][] blockCells){
        for (int[] pos:
                blockCells) {
            cell[pos[1] * 4 + pos[0]] = new BlockView(mContext);
        }
    }

    public void placeCardCell(Card card, int x, int y, int team) {
        cell[y * 4 + x] = new CardView(mContext, card, team, false, false, false);
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
        View view = cell[position];
        view.setMinimumWidth(100);
        view.setMinimumHeight(100);
        return view;
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