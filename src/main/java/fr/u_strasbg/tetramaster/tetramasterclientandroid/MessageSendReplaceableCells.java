package fr.u_strasbg.tetramaster.tetramasterclientandroid;

import java.io.Serializable;

public class MessageSendReplaceableCells  implements Message, Serializable {
    private static final long serialVersionUID = 1L;

    public int[][] replaceableCells;
}
