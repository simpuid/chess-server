package com.chess.server;

public class King extends Piece{
    King(Color c,int pid,int bid){
        color=c;
        pieceID=pid;
        boxID=bid;
    }
    @Override
    public boolean checkValid(){
        return false;
    }
}
