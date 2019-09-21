package com.chess.server.common.moves;

import com.chess.server.chessboard.pieces.Color;
import com.chess.server.common.Position;

public class MoveNormal extends Move {
    public int pieceId;
    public Position source;
    public Position destination;

    public MoveNormal(Color color, int gameId, int timeStamp, int pieceId, Position source, Position destination) {
        this.gameId = gameId;
        this.color = color;
        this.timeStamp = timeStamp;
        this.pieceId = pieceId;
        this.source = source;
        this.destination = destination;
    }
}