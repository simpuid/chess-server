package com.chess.server.common.results;

public class Delta {
    public int pieceId;
    public int positionId;

    public Delta(int pieceId, int positionId) {
        this.pieceId = pieceId;
        this.positionId = positionId;
    }
}
