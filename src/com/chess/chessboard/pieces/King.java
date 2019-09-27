package com.chess.chessboard.pieces;

import com.chess.chessboard.ChessBoard;
import com.chess.common.Position;
import com.chess.common.moves.MoveNormal;

public class King extends Piece {
    public boolean inCheck;
    public King(Color color, int pieceID, Position boxID,PieceType type) {
        this.color = color;
        this.pieceID = pieceID;
        this.boxID = boxID;
        this.type=type;
        this.moveCount=0;
        this.inCheck=false;
    }

    public boolean checkValid(MoveNormal move, ChessBoard chessBoard) {
        int xs=move.source.x;
        int ys=move.source.y;
        int xd=move.destination.x;
        int yd=move.destination.y;
        return true;
    }
}
