package com.chess.chessboard;

import com.chess.chessboard.pieces.Piece;
import com.chess.common.Position;

public class Box {
    public Piece piece;
    public Position position;

    boolean isEmpty() {
        return piece == null;
    }

    Box(Piece piece, Position position) {
        this.piece = piece;
        this.position = position;
    }
}
