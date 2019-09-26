package com.chess.server;

import com.chess.server.chessboard.ChessBoard;
import com.chess.server.common.Position;
import com.chess.server.common.moves.Move;
import com.chess.server.common.results.Result;

class Server {
    private IdGenerator idGenerator;
    public ChessBoard board;

    Server(int port) {
        idGenerator = new IdGenerator();
        board = new ChessBoard();
    }

    void run() {
        ChessBoard board = new ChessBoard();
        System.out.println(board.lineTest(new Position(2, 1), new Position(2, 6)));
        board.print();
        Console console = new Console();
        console.run(this);
    }

    Result getResult(Move move) {
        return board.evaluate(move);
    }
}
