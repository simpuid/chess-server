package com.chess.server;

public class Pawn extends Piece{
    Pawn(Color c,int pid,int bid){
        color=c;
        pieceID=pid;
        boxID=bid;
    }
    @Override
    public boolean checkValid(){
        return false;
    }
}
