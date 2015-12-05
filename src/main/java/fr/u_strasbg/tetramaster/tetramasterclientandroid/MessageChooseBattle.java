package fr.u_strasbg.tetramaster.tetramasterclientandroid;

import java.io.Serializable;

public class MessageChooseBattle implements Message, Serializable {
    private static final long serialVersionUID = 1L;

    private int xPos;
    private int yPos;

    public MessageChooseBattle() {

    }

    public MessageChooseBattle(int xPos,int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public int getxPos() {
        return xPos;
    }

    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public void setyPos(int yPos) {
        this.yPos = yPos;
    }

}