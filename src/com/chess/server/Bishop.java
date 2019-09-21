package com.chess.server;

public class Bishop extends Piece{
    Bishop(Color c,int pid,int bid){
        color=c;
        pieceID=pid;
        boxID=bid;
    }
    @Override
    public boolean checkValid(){
        return false;
    }
}
