package com.chess.server;

import com.chess.server.chessboard.ChessBoard;
import com.chess.server.common.moves.Move;
import com.chess.server.common.results.Result;

class Server {
    private IdGenerator idGenerator;
    public ChessBoard board;

    Server(){
        idGenerator = new IdGenerator();
        board = new ChessBoard();
    }

    Result getResult(Move move) {
        return board.evaluate(move);
    }
}
