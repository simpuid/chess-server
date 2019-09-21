package com.chess.server.common.moves;

import com.chess.server.chessboard.pieces.Color;
import com.chess.server.common.Position;

public class MoveCastle extends Move {
    public int kingId;
    public Position kingSource;
    public Position kingDestination;
    public int rookId;
    public Position rookSource;
    public Position rookDestination;

    public MoveCastle(Color color, int gameId, int timeStamp, int kingId, Position kingSource, Position kingDestination, int rookId, Position rookSource, Position rookDestination) {
        this.gameId = gameId;
        this.color = color;
        this.timeStamp = timeStamp;
        this.kingId = kingId;
        this.kingSource = kingSource;
        this.kingDestination = kingDestination;
        this.rookId = rookId;
        this.rookSource = rookSource;
        this.rookDestination = rookDestination;
    }
}
