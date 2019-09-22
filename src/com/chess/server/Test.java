package com.chess.server;

import com.chess.server.chessboard.pieces.Color;
import com.chess.server.chessboard.pieces.PieceType;
import com.chess.server.common.Position;
import com.chess.server.common.moves.*;
import com.chess.server.common.results.*;
import com.chess.server.parser.Decoder;
import com.chess.server.parser.Encoder;

import java.util.ArrayList;

public class Test {
    public void test(boolean b) {
        System.out.println(b ? "pass" : "failed");
    }

    public boolean testMove(Move move) {
        String s = Encoder.encode(move);
        Move mMove = Decoder.decodeMove(s);
        String s2 = Encoder.encode(mMove);
        return s.equals(s2);
    }

    public boolean testResult(Result result) {
        String s = Encoder.encode(result);
        Result result1 = Decoder.decodeResult(s);
        String s2 = Encoder.encode(result1);
        return s.equals(s2);
    }

    public boolean testParsers() {
        Move m = new MoveNormal(Color.BLACK, 10, 100045, 1, new Position(1, 1), new Position(1, 1));
        Move m1 = new MoveCastle(1, Color.WHITE, 2, 3, new Position(5, 5), new Position(6, 6), 4, new Position(7, 7), new Position(6, 6));
        Move m2 = new MoveTerminate(Color.BLACK, 1, 2);
        Move m3 = new MoveUpgrade(Color.WHITE, 2, 6, 7, new Position(4, 4), new Position(7, 6), PieceType.ROOK);

        Result r = new InvalidMove();
        Result r2 = new GameFinished(Color.BLACK);
        ArrayList<Delta> deltas = new ArrayList<>();
        deltas.add(new Delta(1, 2));
        deltas.add(new Delta(3, 4));
        deltas.add(new Delta(5, 6));
        Result r3 = new StateChange(deltas);

        return testMove(m) && testMove(m1) && testMove(m2) && testMove(m3) && testResult(r) && testResult(r2) && testResult(r3);
    }
}
