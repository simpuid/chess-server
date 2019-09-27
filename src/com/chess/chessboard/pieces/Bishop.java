package com.chess.chessboard.pieces;

import com.chess.common.Position;

public class Bishop extends Piece {
    public Bishop(Color color, int pieceID, Position boxID,PieceType type) {
        this.color = color;
        this.pieceID = pieceID;
        this.boxID = boxID;
        this.type=type;
        this.moveCount=0;
    }

    public boolean checkValid() {
        return false;
    }
}