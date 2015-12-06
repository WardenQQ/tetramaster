package fr.u_strasbg.tetramaster.tetramasterclientandroid;

import java.io.Serializable;

public class MessagePlaceCard implements Message, Serializable {
<<<<<<< Updated upstream:src/main/java/fr/u_strasbg/tetramaster/tetramasterclientandroid/MessagePlaceCard.java
    private static final long serialVersionUID = 1L;

=======
>>>>>>> Stashed changes:TetramasterClientAndroid/app/src/main/java/fr/u_strasbg/tetramaster/tetramasterclientandroid/MessagePlaceCard.java
    int xPos;
    int yPos;
    int idMap;

    public MessagePlaceCard(int xPos,int yPos,int idMap)
    {
        this.xPos = xPos;
        this.yPos = yPos;
        this.idMap = idMap;
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

    public int getIdMap() {
        return idMap;
    }

    public void setIdMap(int idMap) {
        this.idMap = idMap;
    }
}