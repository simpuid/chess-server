package com.chess.server;

public class IdGenerator {
    private static int counter;

    public IdGenerator() {
        counter = 0;
    }

    public int get() {
        return counter++;
    }
}
