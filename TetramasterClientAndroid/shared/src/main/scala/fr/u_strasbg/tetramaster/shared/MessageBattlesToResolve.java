package fr.u_strasbg.tetramaster.shared;

import java.io.Serializable;

public class MessageBattlesToResolve implements Message, Serializable {
    private static final long serialVersionUID = 1L;

    private int[][] battles;

    public int[][] getBattles() {
        return battles;
    }

    public void setBattles(int[][] battles) {
        this.battles = battles;
    }
}
