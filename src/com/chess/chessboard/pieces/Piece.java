package com.chess.chessboard.pieces;

import com.chess.chessboard.ChessBoard;
import com.chess.common.Position;
import com.chess.common.moves.MoveNormal;
import com.chess.common.results.Delta;
import com.chess.common.results.StateChange;

import javax.swing.*;

public class Piece {
    public Color color;
    public int pieceID;
    PieceType type;
    public Position boxID;
    int moveCount;
    public Icon icon;

    public int getPieceID() {
        return pieceID;
    }

    public Color getColor() {
        return color;
    }

    public boolean checkValid(MoveNormal move, ChessBoard chessBoard) {
        if (chessBoard.getPiece(move.destination.x, move.destination.y) != null)
            return chessBoard.getPiece(move.destination.x, move.destination.y).color != color;
        else
            return true;
    }

    protected int sign(int x) {
        if (x == 0) return 0;
        return x > 0 ? 1 : -1;
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
