package com.chess.server;

import com.chess.server.common.moves.Move;
import com.chess.server.common.results.InvalidMove;
import com.chess.server.common.results.Result;

class Server {
    private IdGenerator idGenerator;

    Server(){
        idGenerator = new IdGenerator();
    }

    Result getResult(Move move) {
        return new InvalidMove();
    }
}
