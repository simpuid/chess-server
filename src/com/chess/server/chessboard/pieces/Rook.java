package com.chess.server.chessboard.pieces;

import com.chess.server.chessboard.ChessBoard;
import com.chess.server.common.Position;
import com.chess.server.common.moves.MoveCastle;
import com.chess.server.common.moves.MoveNormal;

public class Rook extends Piece {
    public Rook(Color color, int pieceID, Position boxID, PieceType type) {
        this.color = color;
        this.pieceID = pieceID;
        this.boxID = boxID;
        this.type = type;
        this.moveCount = 0;
    }

    public boolean checkValid(MoveNormal move, ChessBoard chessBoard) {
        int xs = move.source.x;
        int ys = move.source.y;
        int xd = move.destination.x;
        int yd = move.destination.y;
        int changex = xd - xs;
        int changey = yd - ys;
        if (changex * changey != 0)
            return false;
        else {
            if (changex == 0) {
                if (ys > yd) {
                    for (int i = yd; i < ys;i++){
                        if (chessBoard.boxArray[xs][i].piece!=null)
                            return false;
                    }
                    return true;
                }
                if (ys < yd) {
                    for (int i = ys+1; i <= yd;i++){
                        if (chessBoard.boxArray[xs][i].piece!=null)
                            return false;
                    }
                    return true;
                }
            }
            if (changey == 0) {
                if (xs > xd) {
                    for (int i = xd; i < xs;i++){
                        if (chessBoard.boxArray[i][ys].piece!=null)
                            return false;
                    }
                    return true;
                }
                if (xs < xd) {
                    for (int i = xs+1; i <= xd;i++){
                        if (chessBoard.boxArray[i][ys].piece!=null)
                            return false;
                    }
                    return true;
                }
            }
        }
        return false;
    }
    public boolean checkValidCastle(MoveCastle move,ChessBoard chessBoard){
        int rxs = move.rookSource.x;
        int rys = move.rookSource.y;
        int rxd = move.rookDestination.x;
        int ryd = move.rookDestination.y;
        int kxs = move.kingSource.x;
        int kys = move.kingSource.y;
        int kxd = move.kingDestination.x;
        int kyd = move.kingDestination.y;
        return true;
    }
}
