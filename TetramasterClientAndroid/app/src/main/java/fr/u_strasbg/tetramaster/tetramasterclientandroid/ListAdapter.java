package fr.u_strasbg.tetramaster.tetramasterclientandroid;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import fr.u_strasbg.tetramaster.shared.Card;

public class ListAdapter extends BaseAdapter {
    private Context mContext;

    // Keep all Images in array
    private Card[] deck;
    private int team;
    public View[] cell =  { null,null,null,null,null

    };

    // Constructor
    public ListAdapter(Context c){
        mContext = c;
    }

    public Card[] getDeck() {
        return deck;
    }

    public void setDeck(Card[] deck) {
        this.deck = deck;
        notifyDataSetChanged();
    }

    public void setTeam(int team) {
        this.team = team;
    }

    @Override
    public int getCount() {
        if (deck == null) {
            return 0;
        }
        return deck.length;
    }

    @Override
    public Object getItem(int position) {
        return deck[position];
    }

    @Override
    public long getItemId(int position) {
        return deck[position].getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CardView card = new CardView(mContext, deck[position], team, false);
        card.setMinimumWidth(100);
        card.setMinimumHeight(100);
        return card;
    }
}