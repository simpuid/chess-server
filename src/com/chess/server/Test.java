package com.chess.server;

import com.chess.chessboard.pieces.Color;
import com.chess.chessboard.pieces.PieceType;
import com.chess.common.Position;
import com.chess.common.moves.*;
import com.chess.common.request.Host;
import com.chess.common.request.Join;
import com.chess.common.request.Request;
import com.chess.common.response.Error;
import com.chess.common.response.Response;
import com.chess.common.response.Success;
import com.chess.common.results.*;
import com.chess.parser.Decoder;
import com.chess.parser.Encoder;

import java.util.ArrayList;

public class Test {
    public void test(boolean b) {
        System.out.println(b ? "pass" : "failed");
    }

    public boolean testMove(Move move) {
        String s = Encoder.encode(move);
        Move mMove = Decoder.decodeMove(s);
        String s2 = Encoder.encode(mMove);
        System.out.println(s);
        System.out.println(s2);
        return s.equals(s2);
    }

    public boolean testResult(Result result) {
        String s = Encoder.encode(result);
        Result result1 = Decoder.decodeResult(s);
        String s2 = Encoder.encode(result1);
        System.out.println(s);
        System.out.println(s2);

        return s.equals(s2);
    }

    public boolean testRequest(Request request) {
        String s = Encoder.encode(request);
        Request request1 = Decoder.decodeRequest(s);
        String s2 = Encoder.encode(request1);
        System.out.println(s);
        System.out.println(s2);
        return s.equals(s2);
    }

    public boolean testResponse(Response response) {
        String s = Encoder.encode(response);
        Response response1 = Decoder.decodeResponse(s);
        String s2 = Encoder.encode(response1);
        System.out.println(s);
        System.out.println(s2);
        return s.equals(s2);
    }

    public boolean testParsers() {
        Move m = new MoveNormal(Color.BLACK, 10, 100045, 1, new Position(1, 1), new Position(1, 1));
        Move m1 = new MoveCastle(1, Color.WHITE, 2, 3, new Position(5, 5), new Position(6, 6), 4, new Position(7, 7), new Position(6, 6));
        Move m2 = new MoveTerminate(Color.BLACK, 1, 2);
        Move m3 = new MoveUpgrade(Color.WHITE, 2, 6, 7, new Position(4, 4), new Position(7, 6), PieceType.ROOK);
        Result r2 = new GameFinished(Color.BLACK);
        ArrayList<Delta> deltas = new ArrayList<>();
        deltas.add(new Delta(1, 2));
        deltas.add(new Delta(3, 4));
        deltas.add(new Delta(5, 6));
        Result r3 = new StateChange(deltas);
        Result r4 = new SetTurn(Color.BLACK);

        Request req1 = new Host();
        Request req2 = new Join(5);

        Response res1 = new Success(5, Color.WHITE);
        Response res2 = new Error();


        return testMove(m) && testMove(m1) && testMove(m2) && testMove(m3) && testResult(r2) && testResult(r3) && testResult(r4) && testRequest(req1) && testRequest(req2) && testResponse(res1) && testResponse(res2);
    }
}
