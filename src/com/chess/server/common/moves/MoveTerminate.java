package com.chess.server.common.moves;

import com.chess.server.chessboard.pieces.Color;
import com.chess.server.parser.Encoder;

import java.util.Scanner;

public class MoveTerminate extends Move {
    public MoveTerminate(Color color, int gameId, int timeStamp) {
        super(gameId, color, timeStamp);
    }

    public MoveTerminate(Scanner scanner) throws Exception {
        super(scanner);
    }

    public void write(StringBuilder builder) {
        Encoder.write("move terminate", builder);
        super.write(builder);
    }
}
