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
        pos=new Position(0);
        pieceArray[0]=new Rook(Color.WHITE,0,pos,PieceType.ROOK);
        pos=new Position(7);
        pieceArray[7]=new Rook(Color.WHITE,7,pos,PieceType.ROOK);
        pos=new Position(1);
        pieceArray[1]=new Knight(Color.WHITE,1,pos,PieceType.KNIGHT);
        pos=new Position(6);
        pieceArray[6]=new Knight(Color.WHITE,6,pos,PieceType.KNIGHT);
        pos=new Position(2);
        pieceArray[2]=new Bishop(Color.WHITE,2,pos,PieceType.BISHOP);
        pos=new Position(5);
        pieceArray[5]=new Bishop(Color.WHITE,5,pos,PieceType.BISHOP);
        pos=new Position(3);
        pieceArray[3]=new King(Color.WHITE,3,pos,PieceType.KING);
        pos=new Position(4);
        pieceArray[4]=new Queen(Color.WHITE,4,pos,PieceType.QUEEN);
        for (int i = 16; i < 24; i++) {
            pos = new Position(i);
            pieceArray[i] = new Pawn(Color.BLACK, i, pos, PieceType.PAWN);
        }
        pos=new Position(24);
        pieceArray[24]=new Rook(Color.BLACK,24,pos,PieceType.ROOK);
        pos=new Position(31);
        pieceArray[31]=new Rook(Color.BLACK,31,pos,PieceType.ROOK);
        pos=new Position(25);
        pieceArray[25]=new Knight(Color.BLACK,25,pos,PieceType.KNIGHT);
        pos=new Position(30);
        pieceArray[30]=new Knight(Color.BLACK,30,pos,PieceType.KNIGHT);
        pos=new Position(26);
        pieceArray[26]=new Bishop(Color.BLACK,26,pos,PieceType.BISHOP);
        pos=new Position(29);
        pieceArray[29]=new Bishop(Color.BLACK,29,pos,PieceType.BISHOP);
        pos=new Position(27);
        pieceArray[27]=new King(Color.BLACK,27,pos,PieceType.KING);
        pos=new Position(28);
        pieceArray[28]=new Queen(Color.BLACK,28,pos,PieceType.QUEEN);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Position posn = new Position(i, j);
                boxArray[i][j] = new Box(null, posn);
            }
        }
        Position posn = new Position(0);
        boxArray[0][0].piece = new Pawn(Color.BLACK, 0, posn, PieceType.PAWN);
    }

    public Result giveResult(Move move,ChessBoard chessBoard) {
        Result result = null;
        if (move instanceof MoveTerminate) {
            if (move.color == Color.BLACK)
                result = new GameFinished(Color.WHITE);
            else
                result = new GameFinished(Color.BLACK);
            return result;
        } else {
            if (move instanceof MoveNormal) {
                int xs = ((MoveNormal) move).source.x;
                int ys = ((MoveNormal) move).source.y;
                if ((xs >= 8) || (xs < 0)) {
                    result = new InvalidMove();
                    return result;
                } else if ((ys >= 8) || (ys < 0)) {
                    result = new InvalidMove();
                    return result;
                }
                int xd = ((MoveNormal) move).destination.x;
                int yd = ((MoveNormal) move).destination.y;
                if ((xd >= 8) || (xd < 0)) {
                    result = new InvalidMove();
                    return result;
                } else if ((yd >= 8) || (yd < 0)) {
                    result = new InvalidMove();
                    return result;
                }
                if (((MoveNormal) move).source.equals((boxArray[xs][ys].piece.boxID))) {
                    if (((MoveNormal) move).source.equals(pieceArray[((MoveNormal) move).pieceId].boxID)){
                        if (chessBoard.pieceArray[((MoveNormal) move).pieceId].checkValid(((MoveNormal) move),chessBoard)){
                            result=new GameFinished(Color.WHITE);
                            return result;
                        }
                    }
                }
            }
            return result;
        }
    }
}
