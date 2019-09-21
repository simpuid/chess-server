package com.chess.server;

public class Rook extends Piece{
    Rook(Color color,int pieceID,Position boxID){
        this.color=color;
        this.pieceID=pieceID;
        this.boxID=boxID;
    }
    @Override
    public boolean checkValid(){
        return false;
    }
}
