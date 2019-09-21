package com.chess.server.chessboard;

import com.chess.server.chessboard.pieces.Piece;
import com.chess.server.common.Position;

public class Box {
    public Piece piece;
    public Position position;

    public boolean isEmpty() {
        return piece == null;
    }

    Box(Piece piece, Position position) {
        this.piece = piece;
        this.position = position;
    }
}
