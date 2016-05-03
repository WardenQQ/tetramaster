package fr.u_strasbg.tetramaster.shared;


import java.io.Serializable;

public class Deck implements Serializable{
    private int id;
    private Card[] listCards;
    private String name;

    public Deck(int id, Card[] listCards, String name)
    {
        setId(id);
        setListCards(listCards);
        setName(name);
    }

    public Card[] getListCards() {
        return listCards;
    }

    public void setListCards(Card[] listCards) {
        this.listCards = listCards;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }




}
