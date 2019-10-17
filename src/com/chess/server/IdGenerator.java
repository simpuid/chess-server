package com.chess.server;

class IdGenerator {
    private static int counter;

    IdGenerator() {
        counter = 0;
    }

    int get() {
        return counter++;
    }
}
