package com.chess.common.results;

import com.chess.chessboard.pieces.Color;
import com.chess.parser.Decoder;
import com.chess.parser.Encoder;

import java.util.Scanner;

public class InvalidMove extends Result {
    public Color color;

    public InvalidMove(Color color) {
        this.color = color;
    }

    public InvalidMove(Scanner scanner) throws Exception {
        color = Decoder.readColor(scanner);
    }

    public void write(StringBuilder builder) {
        Encoder.write("result invalid", builder);
        Encoder.write(color, builder);
    }
}
