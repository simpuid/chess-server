package com.chess.server.chessboard;

import com.chess.server.chessboard.pieces.Color;
import com.chess.server.chessboard.pieces.Pawn;
import com.chess.server.common.Position;

public class ChessBoard {
    public Box[][] boxArray;
    ChessBoard(){
        boxArray=new Box[8][8];
        for (int i=0;i<8;i++){
            for (int j=0;j<8;j++){
                Position pos=new Position(i,j);
                boxArray[i][j]=new Box(null,pos);
            }
        }
        Position pos=new Position(0);
        boxArray[0][0].piece=new Pawn(Color.BLACK,0,pos);

    }

}
