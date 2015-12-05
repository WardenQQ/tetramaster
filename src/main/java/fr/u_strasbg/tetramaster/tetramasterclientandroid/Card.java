package fr.u_strasbg.tetramaster.tetramasterclientandroid;

import java.io.Serializable;
import java.util.Vector;


public class Card implements Serializable {
    private int id;
    private boolean[] arrays;
    private int magicalDef;
    private int physicalDef;
    private int attack;
    private String powerType;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean[] getArrays() {
        return arrays;
    }

    public void setArrays(boolean[] arrays) {
        this.arrays = arrays;
    }

    public int getMagicalDef() {
        return magicalDef;
    }

    public void setMagicalDef(int magicalDef) {
        this.magicalDef = magicalDef;
    }

    public int getPhysicalDef() {
        return physicalDef;
    }

    public void setPhysicalDef(int physicalDef) {
        this.physicalDef = physicalDef;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public String getPowerType() {
        return powerType;
    }

    public void setPowerType(String powerType) {
        this.powerType = powerType;
    }

    public Card(boolean[] arrays,int magicalDef,int physicalDef,int attack,String powerType)
    {
        this.arrays = arrays;
        this.setMagicalDef(magicalDef);
        this.setAttack(attack);
        this.setPhysicalDef(physicalDef);
        this.setPowerType(powerType);
    }
}
