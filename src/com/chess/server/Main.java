package com.chess.server;

import com.chess.server.chessboard.ChessBoard;

public class Main {
    public static void main(String[] args) {
        ChessBoard board = new ChessBoard();
        board.print();
        Console console = new Console();
        console.run();
    }
}
