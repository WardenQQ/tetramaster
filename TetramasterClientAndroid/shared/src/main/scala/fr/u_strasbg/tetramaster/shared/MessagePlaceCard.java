package fr.u_strasbg.tetramaster.shared;

import java.io.Serializable;

public class MessagePlaceCard implements Message, Serializable {
    private static final long serialVersionUID = 1L;

    public int x;
    public int y;
    int idMap;
    public int team;
    public Card card;

    public MessagePlaceCard() {
    }

    public MessagePlaceCard(int xPos,int yPos,int idMap)
    {
        x = xPos;
        y = yPos;
        this.idMap = idMap;
    }

    public int getIdMap() {
        return idMap;
    }

    public void setIdMap(int idMap) {
        this.idMap = idMap;
    }
}