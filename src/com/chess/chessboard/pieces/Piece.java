package com.chess.chessboard.pieces;

import com.chess.chessboard.ChessBoard;
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
        boxID = move.destination;
        int xd = move.destination.x;
        int yd = move.destination.y;
        int xs = move.source.x;
        int ys = move.source.y;
        StateChange change=new StateChange();
        moveCount++;
        if (chessBoard.getPiece(xd, yd) != null) {
            int pid = chessBoard.getPiece(xd, yd).pieceID;
            chessBoard.pieceArray[pid].boxID = new Position(64);
            change.deltas.add(new Delta(pid,64));
        }
        chessBoard.boxArray[xd][yd].piece = chessBoard.pieceArray[move.pieceId];
        change.deltas.add(new Delta(move.pieceId, xd + yd * 8));
        chessBoard.boxArray[xs][ys].piece = null;
        return change;
    }
}
