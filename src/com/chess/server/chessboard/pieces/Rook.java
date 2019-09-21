package com.chess.server.chessboard.pieces;

import com.chess.server.chessboard.ChessBoard;
import com.chess.server.common.Position;
import com.chess.server.common.moves.MoveNormal;

public class Rook extends Piece {
    public Rook(Color color, int pieceID, Position boxID,PieceType type) {
        this.color = color;
        this.pieceID = pieceID;
        this.boxID = boxID;
        this.type=type;
        this.moveCount=0;
    }

    public boolean checkValid(MoveNormal move, ChessBoard chessBoard) {
        int xs = move.source.x;
        int ys = move.source.y;
        int xd = move.destination.x;
        int yd = move.destination.y;
        int changex=xd-xs;
        int cahngey=yd-ys;
        if (move.color==Color.WHITE) {
            if (changex * changex != 0)
                return false;
            else {
                return true;
            }
        }
        return false;
    }
}
