package com.chess.server;

public class Knight extends Piece{
    Knight(Color c,int pid,int bid){
        color=c;
        pieceID=pid;
        boxID=bid;
    }
    @Override
    public boolean checkValid(){
        return false;
    }
}
