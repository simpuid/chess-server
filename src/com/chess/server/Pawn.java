package com.chess.server;

public class Pawn extends Piece{
    Pawn(Color color,int pieceID,Position boxID){
        this.color=color;
        this.pieceID=pieceID;
        this.boxID=boxID;
    }
    @Override
    public boolean checkValid(){
        return false;
    }
}
