package com.chess.server.common;

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

    public void decode(StringBuilder builder) {
        builder.append(' ');
        builder.append(getID());
    }

    public int getID() {
        return (x + y * 8);
    }

    public boolean isDead() {
        return x < 0 || x >= 8 || y < 0 || y >= 8;
    }
}
