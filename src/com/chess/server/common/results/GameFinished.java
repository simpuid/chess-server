package com.chess.server.common.results;

import com.chess.server.chessboard.pieces.Color;

public class GameFinished extends Result {
    Color winner;

    public GameFinished(Color color) {
        this.winner = color;
    }

    @Override
    public String encode() {
        if (winner == Color.WHITE)
            return "result finish white";
        else
            return "result finish black";
    }
}
