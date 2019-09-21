package com.chess.server;

public class Move {
    public MoveType type;
    public int pieceId;
    public Position source;
    public Position target;

    Move(MoveType type,int pieceId,Position source,Position target){
        this.type = type;
        this.pieceId = pieceId;
        this.source = source;
        this.target = target;
    }
}
