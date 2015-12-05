package fr.u_strasbg.tetramaster.tetramasterclientandroid;


import java.io.Serializable;

public class MessageSetDeckList implements Message, Serializable {
    private static final long serialVersionUID = 1L;

    public int team;
    public Card[] deck;
}
