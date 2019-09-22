package com.chess.server.chessboard;

import com.chess.server.chessboard.pieces.*;
import com.chess.server.common.Position;
import com.chess.server.common.moves.Move;
import com.chess.server.common.moves.MoveNormal;
import com.chess.server.common.moves.MoveTerminate;
import com.chess.server.common.results.GameFinished;
import com.chess.server.common.results.InvalidMove;
import com.chess.server.common.results.Result;

public class ChessBoard {
    public Box[][] boxArray;
    public Piece[] pieceArray;

    public ChessBoard() {
        boxArray = new Box[8][8];
        pieceArray = new Piece[32];
        Position pos;
        for (int i = 8; i < 16; i++) {
            pos = new Position(i);
            pieceArray[i] = new Pawn(Color.WHITE, i, pos, PieceType.PAWN);
        }
        pos = new Position(0);
        pieceArray[0] = new Rook(Color.WHITE, 0, pos, PieceType.ROOK);
        pos = new Position(7);
        pieceArray[7] = new Rook(Color.WHITE, 7, pos, PieceType.ROOK);
        pos = new Position(1);
        pieceArray[1] = new Knight(Color.WHITE, 1, pos, PieceType.KNIGHT);
        pos = new Position(6);
        pieceArray[6] = new Knight(Color.WHITE, 6, pos, PieceType.KNIGHT);
        pos = new Position(2);
        pieceArray[2] = new Bishop(Color.WHITE, 2, pos, PieceType.BISHOP);
        pos = new Position(5);
        pieceArray[5] = new Bishop(Color.WHITE, 5, pos, PieceType.BISHOP);
        pos = new Position(3);
        pieceArray[3] = new King(Color.WHITE, 3, pos, PieceType.KING);
        pos = new Position(4);
        pieceArray[4] = new Queen(Color.WHITE, 4, pos, PieceType.QUEEN);


        for (int i = 16; i < 24; i++) {
            pos = new Position(i + 32);
            pieceArray[i] = new Pawn(Color.BLACK, i, pos, PieceType.PAWN);
        }
        pos = new Position(24 + 32);
        pieceArray[24] = new Rook(Color.BLACK, 24, pos, PieceType.ROOK);
        pos = new Position(31 + 32);
        pieceArray[31] = new Rook(Color.BLACK, 31, pos, PieceType.ROOK);
        pos = new Position(25 + 32);
        pieceArray[25] = new Knight(Color.BLACK, 25, pos, PieceType.KNIGHT);
        pos = new Position(30 + 32);
        pieceArray[30] = new Knight(Color.BLACK, 30, pos, PieceType.KNIGHT);
        pos = new Position(26 + 32);
        pieceArray[26] = new Bishop(Color.BLACK, 26, pos, PieceType.BISHOP);
        pos = new Position(29 + 32);
        pieceArray[29] = new Bishop(Color.BLACK, 29, pos, PieceType.BISHOP);
        pos = new Position(27 + 32);
        pieceArray[27] = new King(Color.BLACK, 27, pos, PieceType.KING);
        pos = new Position(28 + 32);
        pieceArray[28] = new Queen(Color.BLACK, 28, pos, PieceType.QUEEN);


        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Position posn = new Position(i, j);
                boxArray[i][j] = new Box(null, posn);
            }
        }
        Position posn = new Position(0);
        //boxArray[0][0].piece = new Pawn(Color.BLACK, 0, posn, PieceType.PAWN);
        for (int i = 0; i < 32; i++) {
            boxArray[pieceArray[i].boxID.x][pieceArray[i].boxID.y].piece = pieceArray[i];
        }
//        boxArray[1][2].piece = pieceArray[16];
    }

    public void print() {
        for (int y = 7; y >= 0; y--) {
            for (int x = 0; x < 8; x++) {
                if (!boxArray[x][y].isEmpty()) {
                    int i = boxArray[x][y].piece.getPieceID();
                    System.out.print("[" + ((i / 10 == 0) ? "0" : "") + boxArray[x][y].piece.getPieceID() + "]");
                } else
                    System.out.print("[  ]");
            }
            System.out.println();
        }
    }

    private int sign(int x) {
        if (x == 0) return 0;
        return x > 0 ? 1 : -1;
    }

    public boolean lineTest(Position source, Position destination) {
        int sx = source.x, sy = source.y;
        int dx = destination.x, dy = destination.y;
        int absX = Math.abs(sx - dx), absY = Math.abs(sy - dy);
        int signX = sign(dx - sx), signY = sign(dy - sy);
        if (absX == 0 && absY == 0)
            return false;
        if (absX != absY && absX * absY != 0)
            return false;

        for (int i = 1; i < (absX > absY ? absX : absY); i++) {
            if (!boxArray[sx + signX * i][sy + signY * i].isEmpty())
                return true;
        }
        return false;
    }

    public Piece getPiece(int x, int y) {
        if (x < 0 || x > 7 || y < 0 || y > 7)
            return null;
        return boxArray[x][y].piece;
    }

    private Result evaluate(MoveTerminate move) {
        return new GameFinished(move.color == Color.BLACK ? Color.WHITE : Color.BLACK);
    }

    private boolean checkBounds(Position pos) {
        return pos.isDead();
    }

    private Result evaluate(MoveNormal move) {
        int sx = move.source.x;
        int sy = move.source.y;
        if (checkBounds(move.source) || checkBounds(move.destination))
            return new InvalidMove();
        if (lineTest(move.source, move.destination))
            return new InvalidMove();
        if (getPiece(sx, sy) == null)
            return new InvalidMove();
        if (getPiece(sx, sy).pieceID != move.pieceId)
            return new InvalidMove();
        if (!pieceArray[move.pieceId].checkValid(move, this))
            return new InvalidMove();
        return pieceArray[move.pieceId].movePiece(move, this);
    }

    public Result evaluate(Move move) {
        if (move instanceof MoveTerminate)
            return evaluate((MoveTerminate) move);
        if (move instanceof MoveNormal)
            return evaluate((MoveNormal) move);
        return new InvalidMove();
    }
}
