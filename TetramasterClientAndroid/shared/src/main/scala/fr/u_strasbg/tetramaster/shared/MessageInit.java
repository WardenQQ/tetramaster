package fr.u_strasbg.tetramaster.shared;

import java.io.Serializable;

public class MessageInit implements Message, Serializable {
    private static final long serialVersionUID = 1L;

    private int[][] grid;

    public int[][] getGrid() {
        return grid;
    }

    public void setGrid(int[][] grid) {
        this.grid = grid;
    }
}
