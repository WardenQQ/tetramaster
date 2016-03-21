package android.game;

import android.graphics.Canvas;
import game.*;

import java.util.List;

public class GridEntity {
    private final int padding = 16;

    CellEntity[][] grid = new CellEntity[4][4];
    private int posX;
    private int posY;

    public GridEntity(int x, int y) {
        posX = x;
        posY = y;

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                grid[i][j] = new EmptyCell(posX + (CellEntity.size + padding) * i, posY + (CellEntity.size + padding) * j);
            }
        }
    }

    public void addBlocks(List<Pos> l) {
        for (Pos b : l) {
            grid[b.x()][b.y()] = new BlockCell(posX + (CellEntity.size + padding) * b.x(), posY + (CellEntity.size + padding) * b.y());
        }
    }

    public Pos intersect(int x, int y) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (grid[i][j].intersect(x, y))
                    return new Pos(i, j);
            }
        }

        return null;
    }

    public Cell get(Pos pos) {
        CellEntity cell = grid[pos.x()][pos.y()];

        if (cell instanceof CardCell) {
            Card card = ((CardCell) cell).card;
            int team = ((CardCell) cell).team;
            return new PlayedCard(pos, team, card);
        }
        else if (cell instanceof EmptyCell) {
            return new Empty();
        }
        else {
            return new Block();
        }

    }

    public void draw(Canvas c) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                grid[i][j].draw(c);
            }
        }
    }
}
