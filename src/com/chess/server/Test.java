package com.chess.server;

import com.chess.server.chessboard.pieces.Color;
import com.chess.server.chessboard.pieces.PieceType;
import com.chess.server.common.Position;
import com.chess.server.common.moves.*;
import com.chess.server.parser.Decoder;
import com.chess.server.parser.Encoder;

public class Test {
    public void test(boolean b) {
        System.out.println(b ? "pass" : "failed");
    }

    public boolean parserTest(Move move) {
        Encoder encoder = new Encoder();
        Decoder decoder = new Decoder();
        String s = encoder.encode(move);
        Move mMove = decoder.decode(s);
        String s2 = encoder.encode(mMove);
        return s.equals(s2);
    }

    public boolean moveTest() {
        Encoder encoder = new Encoder();
        Decoder decoder = new Decoder();
        Move m = new MoveNormal(Color.BLACK, 10, 100045, 1, new Position(1, 1), new Position(1, 1));
        Move m1 = new MoveCastle(Color.WHITE, 1, 2, 3, new Position(5, 5), new Position(6, 6), 4, new Position(7, 7), new Position(6, 6));
        Move m2 = new MoveTerminate(Color.BLACK, 1, 2);
        Move m3 = new MoveUpgrade(Color.WHITE, 2, 6, 7, new Position(4, 4), new Position(7, 6), PieceType.ROOK);
        return parserTest(m) && parserTest(m1) && parserTest(m2) && parserTest(m3);
    }
}
