package com.chess.server.common.moves;

import com.chess.server.chessboard.pieces.Color;
import com.chess.server.chessboard.pieces.PieceType;
import com.chess.server.common.Position;
import com.chess.server.parser.Decoder;
import com.chess.server.parser.Encoder;

import java.util.Scanner;

public class MoveUpgrade extends Move {
    public int pieceId;
    public Position source;
    public Position destination;
    public PieceType upgradeUnit;

    public MoveUpgrade(Color color, int gameId, int timeStamp, int pieceId, Position source, Position destination, PieceType upgradeUnit) {
        super(gameId, color, timeStamp);
        this.pieceId = pieceId;
        this.source = source;
        this.destination = destination;
        this.upgradeUnit = upgradeUnit;
    }

    public MoveUpgrade(Scanner scanner) throws Exception {
        super(scanner);
        pieceId = Decoder.readInt(scanner);
        source = Decoder.readPosition(scanner);
        destination = Decoder.readPosition(scanner);
        upgradeUnit = Decoder.readPieceType(scanner);
    }

    public void write(StringBuilder builder) {
        Encoder.write("move upgrade", builder);
        super.write(builder);
        Encoder.write(pieceId, builder);
        Encoder.write(source, builder);
        Encoder.write(destination, builder);
        Encoder.write(upgradeUnit, builder);
    }
}
