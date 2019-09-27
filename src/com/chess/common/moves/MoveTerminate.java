package com.chess.common.moves;

import com.chess.chessboard.pieces.Color;
import com.chess.parser.Encoder;

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
