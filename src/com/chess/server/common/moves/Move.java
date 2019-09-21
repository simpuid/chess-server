package com.chess.server.common.moves;

import com.chess.server.common.Position;

public class Move {
    public MoveType type;
    public int pieceId;
    public Position source;
    public Position destination;
    public int timeStamp;

    Move(MoveType type,int pieceId,Position source,Position destination,int timeStamp){
        this.type = type;
        this.pieceId = pieceId;
        this.source = source;
        this.destination = destination;
        this.timeStamp = timeStamp;
    }
}
