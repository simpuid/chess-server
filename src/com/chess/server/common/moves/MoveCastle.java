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

    public MoveCastle() {
    }

    @Override
    public void setAttributes(String[] tokens) throws Exception {
        super.setAttributes(tokens);
        kingId = Integer.parseInt(tokens[5]);
        kingSource = new Position(Integer.parseInt(tokens[6]));
        kingDestination = new Position(Integer.parseInt(tokens[7]));

        rookId = Integer.parseInt(tokens[8]);
        rookSource = new Position(Integer.parseInt(tokens[9]));
        rookDestination = new Position(Integer.parseInt(tokens[10]));
    }
}
