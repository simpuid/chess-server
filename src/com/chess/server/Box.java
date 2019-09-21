package com.chess.server;

public class Box {
    Piece piece;
    Position position;
    public boolean isEmpty() {
        if (piece == null)
            return true;
        else return false;
    }
    Box(Piece piece,Position position){
        this.piece=piece;
        this.position=position;
    }
}
