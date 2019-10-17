package com.chess.common.moves;

import com.chess.chessboard.pieces.Color;
import com.chess.chessboard.pieces.PieceType;
import com.chess.common.Position;
import com.chess.parser.Decoder;
import com.chess.parser.Encoder;

import java.util.Scanner;

public class MoveUpgrade extends Move {
    private int pieceId;
    public Position source;
    public Position destination;
    private PieceType upgradeUnit;

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
