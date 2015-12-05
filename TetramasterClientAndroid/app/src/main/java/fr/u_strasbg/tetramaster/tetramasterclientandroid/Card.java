package fr.u_strasbg.tetramaster.tetramasterclientandroid;

import java.util.Vector;

/**
 * Created by Ricardo on 03/12/2015.
 */
public class Card {
    private Boolean[] arrays;
    private int magicalDef;
    private int physicalDef;
    private int attack;
    private String powerType;

    public Boolean[] getArrays() {
        return arrays;
    }

    public void setArrays(Boolean[] arrays) {
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

    public Card(Boolean[] arrays,int magicalDef,int physicalDef,int attack,String powerType)
    {
        this.setArrays(arrays);
        this.setMagicalDef(magicalDef);
        this.setAttack(attack);
        this.setPhysicalDef(physicalDef);
        this.setPowerType(powerType);
    }

}
