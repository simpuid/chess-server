package com.chess.server.parser;

import com.chess.chessboard.pieces.Color;
import com.chess.chessboard.pieces.PieceType;
import com.chess.server.common.Position;
import com.chess.server.common.moves.Move;
import com.chess.server.common.request.Request;
import com.chess.server.common.response.Response;
import com.chess.server.common.results.Result;

public class Encoder {

    public static void write(int i, StringBuilder builder) {
        builder.append(' ');
        builder.append(i);
    }

    public static void write(Color color, StringBuilder builder) {
        if (color == Color.WHITE)
            builder.append(" white");
        else
            builder.append(" black");
    }

    public static void write(PieceType type, StringBuilder builder) {
        switch (type) {
            case ROOK:
                builder.append(" rook");
                break;
            case BISHOP:
                builder.append(" bishop");
                break;
            case KNIGHT:
                builder.append(" knight");
                break;
            case KING:
                builder.append(" king");
                break;
            case PAWN:
                builder.append(" pawn");
                break;
            case QUEEN:
                builder.append(" queen");
                break;
        }
    }

    public static void write(String string, StringBuilder builder) {
        builder.append(string);
    }

    public static void write(Position position, StringBuilder builder) {
        builder.append(' ');
        builder.append(position.getID());
    }

    public static String encode(Move move) {
        StringBuilder builder = new StringBuilder();
        if (move != null)
            move.write(builder);
        return builder.toString();
    }

    public static String encode(Result result) {
        StringBuilder builder = new StringBuilder();
        if (result != null)
            result.write(builder);
        return builder.toString();
    }

    public static String encode(Response response) {
        StringBuilder builder = new StringBuilder();
        if (response != null)
            response.write(builder);
        return builder.toString();
    }

    public static String encode(Request request) {
        StringBuilder builder = new StringBuilder();
        if (request != null)
            request.write(builder);
        return builder.toString();
    }
}
