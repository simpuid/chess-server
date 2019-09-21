package com.chess.server;

import javafx.geometry.Pos;

public class Position {
    public int x;
    public int y;

    public Position(int xx, int yy) {
        x = xx;
        y = yy;
    }

    public Position(int id) {
        x = id % 8;
        y = id / 8;
    }

    public int getID() {
        return (x + y * 8);
    }

    public boolean isDead() {
        if (x >= 0 && x < 8 && y >= 0 && y < 8)
            return false;
        return true;
    }
}
