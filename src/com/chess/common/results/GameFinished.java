package com.chess.common.results;

import com.chess.chessboard.pieces.Color;
import com.chess.parser.Decoder;
import com.chess.parser.Encoder;

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
}
