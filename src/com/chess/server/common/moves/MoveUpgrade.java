package com.chess.server.common.moves;

import com.chess.server.chessboard.pieces.Color;
import com.chess.server.chessboard.pieces.PieceType;
import com.chess.server.common.Position;

public class MoveUpgrade extends Move {
    public int pieceId;
    public Position source;
    public Position destination;
    public PieceType upgradeUnit;

    public MoveUpgrade(Color color, int gameId, int timeStamp, int pieceId, Position source, Position destination, PieceType upgradeUnit) {
        this.gameId = gameId;
        this.color = color;
        this.timeStamp = timeStamp;
        this.pieceId = pieceId;
        this.source = source;
        this.destination = destination;
        this.upgradeUnit = upgradeUnit;
    }

    public MoveUpgrade() {
    }

    @Override
    public void decode(String[] tokens) throws Exception {
        super.decode(tokens);
        pieceId = Integer.parseInt(tokens[5]);
        source = new Position(Integer.parseInt(tokens[6]));
        destination = new Position(Integer.parseInt(tokens[7]));

        if (tokens[8].equals("pawn"))
            upgradeUnit = PieceType.PAWN;
        else if (tokens[8].equals("rook"))
            upgradeUnit = PieceType.ROOK;
        else if (tokens[8].equals("bishop"))
            upgradeUnit = PieceType.BISHOP;
        else if (tokens[8].equals("knight"))
            upgradeUnit = PieceType.KNIGHT;
        else if (tokens[8].equals("king"))
            upgradeUnit = PieceType.KING;
        else if (tokens[8].equals("queen"))
            upgradeUnit = PieceType.QUEEN;
        else
            throw new Exception("piece type unknown");

    }
}
