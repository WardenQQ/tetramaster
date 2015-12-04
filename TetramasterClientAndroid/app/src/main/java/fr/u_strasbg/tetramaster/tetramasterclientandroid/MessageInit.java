package fr.u_strasbg.tetramaster.tetramasterclientandroid;

public class MessageInit implements Message {
    private int[][] grid;

    private boolean[] arrows;
    private int power;
    private int physicalDefense;
    private int magicalDefense;
    String powerType;

    public int[][] getGrid() {
        return grid;
    }

    public void setGrid(int[][] grid) {
        this.grid = grid;
    }

    public boolean[] getArrows() {
        return arrows;
    }

    public void setArrows(boolean[] arrows) {
        this.arrows = arrows;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getPhysicalDefense() {
        return physicalDefense;
    }

    public void setPhysicalDefense(int physicalDefense) {
        this.physicalDefense = physicalDefense;
    }

    public int getMagicalDefense() {
        return magicalDefense;
    }

    public void setMagicalDefense(int magicalDefense) {
        this.magicalDefense = magicalDefense;
    }

    public String getPowerType() {
        return powerType;
    }

    public void setPowerType(String powerType) {
        this.powerType = powerType;
    }
}
