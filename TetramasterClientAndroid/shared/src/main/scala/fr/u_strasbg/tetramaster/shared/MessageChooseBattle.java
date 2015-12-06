package fr.u_strasbg.tetramaster.shared;

import java.io.Serializable;

public class MessageChooseBattle implements Message, Serializable {
    private static final long serialVersionUID = 1L;

    public int x;
    public int y;

    public MessageChooseBattle() {
    }

    public MessageChooseBattle(int xPos,int yPos) {
        this.x = xPos;
        this.y = yPos;
    }
}