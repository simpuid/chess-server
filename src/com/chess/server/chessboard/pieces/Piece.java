package com.chess.server.chessboard.pieces;

import com.chess.server.chessboard.ChessBoard;
import com.chess.server.common.Position;
import com.chess.server.common.moves.MoveNormal;
import com.chess.server.common.results.Delta;
import com.chess.server.common.results.StateChange;

public class Piece {
    public Color color;
    public int pieceID;
    public PieceType type;
    public Position boxID;
    public int moveCount;

    public int getPieceID() {
        return pieceID;
    }

    public Color getColor() {
        return color;
    }

    public boolean checkValid(MoveNormal move, ChessBoard chessBoard) {
        return false;
    }

    public PieceType getType() {
        return type;
    }

    public int getMoveCount() {
        return moveCount;
    }

    public StateChange movePiece(MoveNormal move, ChessBoard chessBoard) {
        this.boxID = move.destination;
        int xd = move.destination.x;
        int yd = move.destination.y;
        int xs = move.source.x;
        int ys = move.source.y;
        StateChange change=new StateChange();
        this.moveCount = this.moveCount + 1;
        if (chessBoard.boxArray[xd][yd].piece == null) {
            chessBoard.boxArray[xd][yd].piece = chessBoard.pieceArray[move.pieceId];
            chessBoard.boxArray[xs][ys].piece = null;

            if (chessBoard.pieceArray[move.pieceId].type == PieceType.PAWN) {
                if (move.color == Color.BLACK) {
                    int pid;
                    if ((xd == xs + 1) && (yd == ys + 1)) {
                        pid = chessBoard.boxArray[xd][ys].piece.pieceID;
                        chessBoard.pieceArray[pid].boxID = new Position(64);
                        chessBoard.boxArray[xd][ys].piece = null;
                    }
                    if ((xd == xs - 1) && (yd == ys + 1)) {
                        pid = chessBoard.boxArray[xd][ys].piece.pieceID;
                        chessBoard.pieceArray[pid].boxID = new Position(64);
                        chessBoard.boxArray[xd][ys].piece = null;
                    }
                }
                if (move.color == Color.WHITE) {
                    int pid;
                    if ((xd == xs + 1) && (yd == ys - 1)) {
                        pid = chessBoard.boxArray[xd][ys].piece.pieceID;
                        chessBoard.pieceArray[pid].boxID = new Position(64);
                        chessBoard.boxArray[xd][ys].piece = null;
                    }
                    if ((xd == xs - 1) && (yd == ys - 1)) {
                        pid = chessBoard.boxArray[xd][ys].piece.pieceID;
                        chessBoard.pieceArray[pid].boxID = new Position(64);
                        chessBoard.boxArray[xd][ys].piece = null;
                    }
                }
            }
        }
        else{
            int pid = chessBoard.boxArray[xd][yd].piece.pieceID;
            chessBoard.pieceArray[pid].boxID = new Position(64);
            chessBoard.boxArray[xd][yd].piece = chessBoard.pieceArray[move.pieceId];
            chessBoard.boxArray[xs][ys].piece = null;
        }
        return null;
    }
}
