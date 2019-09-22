package com.chess.server.common.moves;

import com.chess.server.chessboard.pieces.Color;
import com.chess.server.common.Position;
import com.chess.server.parser.Decoder;
import com.chess.server.parser.Encoder;

import java.util.Scanner;

public class MoveNormal extends Move {
    public int pieceId;
    public Position source;
    public Position destination;

    public MoveNormal(Color color, int gameId, int timeStamp, int pieceId, Position source, Position destination) {
        super(gameId, color, timeStamp);
        this.pieceId = pieceId;
        this.source = source;
        this.destination = destination;
    }

    public MoveNormal(Scanner scanner) throws Exception {
        super(scanner);
        pieceId = Decoder.readInt(scanner);
        source = Decoder.readPosition(scanner);
        destination = Decoder.readPosition(scanner);
    }

    public void write(StringBuilder builder) {
        Encoder.write("move normal", builder);
        super.write(builder);
        Encoder.write(pieceId, builder);
        Encoder.write(source, builder);
        Encoder.write(destination, builder);
    }
}
