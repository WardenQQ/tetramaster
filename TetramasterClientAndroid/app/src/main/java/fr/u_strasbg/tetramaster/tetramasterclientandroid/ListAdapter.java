package fr.u_strasbg.tetramaster.tetramasterclientandroid;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import fr.u_strasbg.tetramaster.shared.Card;
import fr.u_strasbg.tetramaster.shared.Deck;

public class ListAdapter extends BaseAdapter {
    private Context mContext;
    private int cardWidth;
    private int cardHeight;
    private int windowWidth;
    private Deck deck;
    private int team;
    private boolean viewCardName, viewButtonAddCard;

    public ListAdapter(Context c){
        mContext = c;
    }

    public Deck getDeck() {
        return deck;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
        notifyDataSetChanged();
    }

    public void setTeam(int team) {
        this.team = team;
    }

    @Override
    public int getCount() {
        if (deck.getListCards() == null) {
            return 0;
        }
        return deck.getListCards().length;
    }

    @Override
    public Object getItem(int position) {
        return deck.getListCards()[position];
    }

    @Override
    public long getItemId(int position) {
        return deck.getListCards()[position].getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CardView card = new CardView(mContext, deck.getListCards()[position], team, false, isViewCardName(), isViewButtonAddCard());
        card.setCardWidth(getCardWidth());
        card.setCardHeight(getCardHeight());
        card.setMinimumWidth(getCardWidth());
        card.setMinimumHeight(getCardHeight());
        card.setWindowWidth(getWindowWidth());
        return card;
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

    public int getWindowWidth() {
        return windowWidth;
    }

    public void setWindowWidth(int windowWidth) {
        this.windowWidth = windowWidth;
    }

    public boolean isViewCardName() {
        return viewCardName;
    }

    public void setViewCardName(boolean viewCardName) {
        this.viewCardName = viewCardName;
    }

    public boolean isViewButtonAddCard() {
        return viewButtonAddCard;
    }

    public void setViewButtonAddCard(boolean viewButtonAddCard) {
        this.viewButtonAddCard = viewButtonAddCard;
    }
}