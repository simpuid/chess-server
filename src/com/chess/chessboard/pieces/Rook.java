package com.chess.chessboard.pieces;

import com.chess.chessboard.ChessBoard;
import com.chess.common.Position;
import com.chess.common.moves.MoveCastle;
import com.chess.common.moves.MoveNormal;

public class Rook extends Piece {
    public Rook(Color color, int pieceID, Position boxID, PieceType type) {
        this.color = color;
        this.pieceID = pieceID;
        this.boxID = boxID;
        this.type = type;
        this.moveCount = 0;
    }

    public boolean checkValid(MoveNormal move, ChessBoard chessBoard) {
        if (!super.checkValid(move, chessBoard))
            return false;
        int sx = move.source.x, sy = move.source.y;
        int dx = move.destination.x, dy = move.destination.y;
        int absX = Math.abs(dx - sx), absY = Math.abs(dy - sy);
        int signX = sign(dx - sx), signY = sign(dy - sy);
        if (signX * signY != 0)
            return false;
        return signX != 0 || signY != 0;
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
