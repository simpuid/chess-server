package com.chess.server.common.moves;

import com.chess.chessboard.pieces.Color;
import com.chess.server.common.Position;
import com.chess.server.parser.Decoder;
import com.chess.server.parser.Encoder;

import java.util.Scanner;

public class MoveCastle extends Move {
    public int kingId;
    public Position kingSource;
    public Position kingDestination;
    public int rookId;
    public Position rookSource;
    public Position rookDestination;

    public MoveCastle(int gameId, Color color, int timeStamp, int kingId, Position kingSource, Position kingDestination, int rookId, Position rookSource, Position rookDestination) {
        super(gameId, color, timeStamp);
        this.kingId = kingId;
        this.kingSource = kingSource;
        this.kingDestination = kingDestination;
        this.rookId = rookId;
        this.rookSource = rookSource;
        this.rookDestination = rookDestination;
    }

    public MoveCastle(Scanner scanner) throws Exception {
        super(scanner);
        kingId = Decoder.readInt(scanner);
        kingSource = Decoder.readPosition(scanner);
        kingDestination = Decoder.readPosition(scanner);
        rookId = Decoder.readInt(scanner);
        rookSource = Decoder.readPosition(scanner);
        rookDestination = Decoder.readPosition(scanner);
    }

    public void write(StringBuilder builder) {
        Encoder.write("move castle", builder);
        super.write(builder);
        Encoder.write(kingId, builder);
        Encoder.write(kingSource, builder);
        Encoder.write(kingDestination, builder);
        Encoder.write(rookId, builder);
        Encoder.write(rookSource, builder);
        Encoder.write(rookDestination, builder);
    }
}
