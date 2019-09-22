package com.chess.server;

import com.chess.server.chessboard.ChessBoard;
import com.chess.server.common.Position;

public class Main {
    public static void main(String[] args) {
        ChessBoard board = new ChessBoard();
        System.out.println(board.lineTest(new Position(2, 1), new Position(2, 6)));
        board.print();
        Console console = new Console();
        console.run();

//        Test test = new Test();
//        test.test(test.testParsers());
    }
}
