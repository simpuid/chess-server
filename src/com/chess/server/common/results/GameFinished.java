package com.chess.server.common.results;

import com.chess.server.chessboard.pieces.Color;

public class GameFinished extends Result {
    Color winner;
    public GameFinished(Color color){
        this.winner=color;
    }
}
