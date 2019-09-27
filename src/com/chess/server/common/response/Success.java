package com.chess.server.common.response;

import com.chess.chessboard.pieces.Color;
import com.chess.server.parser.Decoder;
import com.chess.server.parser.Encoder;

import java.util.Scanner;

public class Success extends Response {
    public int gameId;
    public Color color;

    public Success(int id, Color col) {
        gameId = id;
        color = col;
    }

    public Success(Scanner scanner) throws Exception {
        gameId = Decoder.readInt(scanner);
        color = Decoder.readColor(scanner);
    }

    public void write(StringBuilder builder) {
        Encoder.write("response success", builder);
        Encoder.write(gameId, builder);
        Encoder.write(color, builder);
    }
}
