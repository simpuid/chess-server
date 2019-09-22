package com.chess.server.parser;

import com.chess.server.chessboard.pieces.Color;
import com.chess.server.chessboard.pieces.PieceType;
import com.chess.server.common.Position;
import com.chess.server.common.moves.*;
import com.chess.server.common.results.GameFinished;
import com.chess.server.common.results.InvalidMove;
import com.chess.server.common.results.Result;
import com.chess.server.common.results.StateChange;

import java.util.Scanner;

public class Decoder {
    public static int readInt(Scanner scanner) throws Exception {
        if (!scanner.hasNextInt())
            throw new Exception("decode_error");
        return scanner.nextInt();
    }

    public static Color readColor(Scanner scanner) throws Exception {
        String s = readString(scanner);
        if (s.equals("white"))
            return Color.WHITE;
        else if (s.equals("black"))
            return Color.BLACK;
        else
            throw new Exception("decode_error");
    }

    public static PieceType readPieceType(Scanner scanner) throws Exception {
        if (!scanner.hasNext())
            throw new Exception("decode_error");
        String s = scanner.next();
        if (s.equals("pawn"))
            return PieceType.PAWN;
        else if (s.equals("rook"))
            return PieceType.ROOK;
        else if (s.equals("bishop"))
            return PieceType.BISHOP;
        else if (s.equals("knight"))
            return PieceType.KNIGHT;
        else if (s.equals("king"))
            return PieceType.KING;
        else if (s.equals("queen"))
            return PieceType.QUEEN;
        else
            throw new Exception("piece type unknown");
    }

    public static String readString(Scanner scanner) throws Exception {
        if (!scanner.hasNext())
            throw new Exception("decode_error");
        return scanner.next();
    }

    public static Position readPosition(Scanner scanner) throws Exception {
        return new Position(readInt(scanner));
    }

    public static Move decodeMove(String input) {
        try {
            Scanner scanner = new Scanner(input);
            if (!readString(scanner).equals("move"))
                return null;
            String header = readString(scanner);
            if (header.equals("normal"))
                return new MoveNormal(scanner);
            if (header.equals("terminate"))
                return new MoveTerminate(scanner);
            if (header.equals("castle"))
                return new MoveCastle(scanner);
            if (header.equals("upgrade"))
                return new MoveUpgrade(scanner);
            else
                return null;
        } catch (Exception e) {
            return null;
        }
    }

    public static Result decodeResult(String input) {
        try {
            Scanner scanner = new Scanner(input);
            if (!readString(scanner).equals("result"))
                return null;
            String header = readString(scanner);
            if (header.equals("invalid"))
                return new InvalidMove(scanner);
            if (header.equals("finish"))
                return new GameFinished(scanner);
            if (header.equals("change"))
                return new StateChange(scanner);
            else
                return null;
        } catch (Exception e) {
            return null;
        }
    }
}
