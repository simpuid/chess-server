package com.chess.server.chessboard.pieces;

import com.chess.server.common.Position;

public class Knight extends Piece {
    public Knight(Color color, int pieceID, Position boxID,PieceType type) {
        this.color = color;
        this.pieceID = pieceID;
        this.boxID = boxID;
        this.type=type;
    }

    public boolean checkValid() {
        return false;
    }
}
