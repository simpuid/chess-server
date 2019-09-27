package com.chess.server.common.moves;

import com.chess.chessboard.pieces.Color;
import com.chess.server.parser.Decoder;
import com.chess.server.parser.Encoder;

import java.util.Scanner;

public abstract class Move {
    public int gameId;
    public Color color;
    public int timeStamp;

    public Move(int gameId, Color color, int timeStamp) {
        this.color = color;
        this.gameId = gameId;
        this.timeStamp = timeStamp;
    }

    public Move(Scanner scanner) throws Exception {
        gameId = Decoder.readInt(scanner);
        color = Decoder.readColor(scanner);
        timeStamp = Decoder.readInt(scanner);
    }

    public void write(StringBuilder builder) {
        Encoder.write(gameId, builder);
        Encoder.write(color, builder);
        Encoder.write(timeStamp, builder);
    }
}