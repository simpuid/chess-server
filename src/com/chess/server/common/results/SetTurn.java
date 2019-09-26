package com.chess.server.common.results;

import com.chess.server.chessboard.pieces.Color;
import com.chess.server.parser.Decoder;
import com.chess.server.parser.Encoder;

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
