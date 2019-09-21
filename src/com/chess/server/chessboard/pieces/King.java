package com.chess.server.chessboard.pieces;

import com.chess.server.common.Position;

public class King extends Piece {
    public King(Color color, int pieceID, Position boxID) {
        this.color = color;
        this.pieceID = pieceID;
        this.boxID = boxID;
    }

    @Override
    public boolean checkValid() {
        return false;
    }
}
