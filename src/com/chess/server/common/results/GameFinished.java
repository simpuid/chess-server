package com.chess.server.common.results;

import com.chess.server.chessboard.pieces.Color;
import com.chess.server.parser.Decoder;
import com.chess.server.parser.Encoder;

import java.util.Scanner;

public class GameFinished extends Result {
    Color winner;

    public GameFinished(Color color) {
        this.winner = color;
    }

    public GameFinished(Scanner scanner) throws Exception {
        winner = Decoder.readColor(scanner);
    }

    public void write(StringBuilder builder) {
        Encoder.write("result finish", builder);
        Encoder.write(winner, builder);
    }

    @Override
    public String encode() {
        if (winner == Color.WHITE)
            return "result finish white";
        else
            return "result finish black";
    }
}
