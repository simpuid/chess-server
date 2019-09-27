package com.chess.common.results;

import com.chess.chessboard.pieces.Color;
import com.chess.parser.Decoder;
import com.chess.parser.Encoder;

import java.util.Scanner;

public class SetTurn extends Result {
    Color color;

    public SetTurn(Color color) {
        this.color = color;
    }

    public SetTurn(Scanner scanner) throws Exception {
        color = Decoder.readColor(scanner);
    }

    public void write(StringBuilder builder) {
        Encoder.write("result turn", builder);
        Encoder.write(color, builder);
    }
}
