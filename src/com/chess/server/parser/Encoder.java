package com.chess.server.parser;

import com.chess.server.common.moves.Move;
import com.chess.server.common.results.Result;

public class Encoder {
    public String encode(Move move) {
        return move.encode();
    }

    public String encode(Result result) {
        return result.encode();
    }
}
