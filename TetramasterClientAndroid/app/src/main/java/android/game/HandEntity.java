package android.game;

import android.graphics.Canvas;
import game.Card;

import java.util.ArrayList;
import java.util.List;


public class HandEntity {
    private final int padding = 16;
    private List<CardCell> hand = new ArrayList<>(5);
    private int centerX;
    private int posY;

    private CardCell selected = null;

    public HandEntity(int centerX, int y) {
        this.centerX = centerX;
        posY = y;
    }

    public void add(Card card) {
        hand.add(new CardCell(0, 0, card, 0));

        int startX = centerX - (hand.size() * CellEntity.size + (hand.size() - 1) * padding) / 2;
        for (int i = 0; i < hand.size(); i++) {
            hand.get(i).setPosition(startX + (CellEntity.size + padding) * i, posY);
        }
    }

    public boolean hasSelected() {
        return selected != null;
    }

    public void setSelectedPosition(int x, int y) {
        selected.setPosition(x - CellEntity.size / 2, y - CellEntity.size / 2);
    }

    public void select(int c) {
        if (!hasSelected()) {
            selected = hand.remove(c);
            int startX = centerX - (hand.size() * CellEntity.size + (hand.size() - 1) * padding) / 2;
            for (int i = 0; i < hand.size(); i++) {
                hand.get(i).setPosition(startX + (CellEntity.size + padding) * i, posY);
            }
        }
    }

    public void cancelSelect() {
        hand.add(selected);
        selected = null;
        int startX = centerX - (hand.size() * CellEntity.size + (hand.size() - 1) * padding) / 2;
        for (int i = 0; i < hand.size(); i++) {
            hand.get(i).setPosition(startX + (CellEntity.size + padding) * i, posY);
        }
    }

    public CardCell removeSelect() {
        CardCell ret = selected;
        selected = null;
        return ret;
    }

    public int intersect(int x, int y) {
        for (int i = 0; i < hand.size(); i++) {
            if (hand.get(i).intersect(x, y))
                return i;
        }

        return -1;
    }

    public void draw(Canvas c) {
        for (CardCell card : hand) {
            card.draw(c);
        }

        if (hasSelected()) {
            selected.draw(c);
        }
    }
}
