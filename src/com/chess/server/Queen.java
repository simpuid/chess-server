package com.chess.server;

public class Queen extends Piece{
    Queen(Color c,int pid,int bid){
        color=c;
        pieceID=pid;
        boxID=bid;
    }
    @Override
    public boolean checkValid(){
        return false;
    }
}
