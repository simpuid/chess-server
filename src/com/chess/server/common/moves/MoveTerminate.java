package com.chess.server.common.moves;

import com.chess.server.chessboard.pieces.Color;

public class MoveTerminate extends Move {
    public MoveTerminate(Color color, int gameId, int timeStamp) {
        this.color = color;
        this.gameId = gameId;
        this.timeStamp = timeStamp;
    }
}
