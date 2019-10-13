package com.chess.chessboard.pieces;

import com.chess.chessboard.ChessBoard;
import com.chess.common.Position;
import com.chess.common.moves.MoveNormal;

public class Bishop extends Piece {
    public Bishop(Color color, int pieceID, Position boxID,PieceType type) {
        this.color = color;
        this.pieceID = pieceID;
        this.boxID = boxID;
        this.type=type;
        this.moveCount=0;
    }

    public boolean checkValid(MoveNormal move, ChessBoard chessBoard) {
        if (!super.checkValid(move, chessBoard))
            return false;
        int sx = move.source.x, sy = move.source.y;
        int dx = move.destination.x, dy = move.destination.y;
        int absX = Math.abs(dx - sx), absY = Math.abs(dy - sy);
        int signX = sign(dx - sx), signY = sign(dy - sy);
        return absX == absY && signX * signY != 0;
    }
}
