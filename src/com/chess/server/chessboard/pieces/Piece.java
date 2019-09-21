package com.chess.server.chessboard.pieces;

import com.chess.server.common.Position;

public class Piece {
    public Color color;
    public int pieceID;
    public PieceType type;
    public Position boxID;

    public int getPieceID() {
        return pieceID;
    }
    public Color getColor(){
        return color;
    }
    public  boolean checkValid(){
        return  false;
    }
    public PieceType getType() {
        return type;
    }
}
