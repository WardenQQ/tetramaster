package fr.u_strasbg.tetramaster.shared;

import java.io.Serializable;

public class MessageReplaceableCellList implements Message, Serializable {
    private static final long serialVersionUID = 1L;

    public int[][] replaceable;
}
