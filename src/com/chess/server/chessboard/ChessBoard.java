package com.chess.server.chessboard;

import com.chess.server.chessboard.pieces.Color;
import com.chess.server.chessboard.pieces.Pawn;
import com.chess.server.common.Position;
import com.chess.server.common.moves.Move;
import com.chess.server.common.moves.MoveTerminate;
import com.chess.server.common.results.GameFinished;
import com.chess.server.common.results.Result;

public class ChessBoard {
    public Box[][] boxArray;
    public ChessBoard(){
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
    public Result giveResult(Move move){
        if (move instanceof MoveTerminate){
            Result result=new GameFinished();
            return  result;
        }
        Result result=new GameFinished();
        return  result;
    }
}
