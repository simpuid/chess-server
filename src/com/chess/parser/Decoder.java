package com.chess.parser;

import com.chess.chessboard.pieces.Color;
import com.chess.chessboard.pieces.PieceType;
import com.chess.common.Position;
import com.chess.common.moves.*;
import com.chess.common.request.Host;
import com.chess.common.request.Join;
import com.chess.common.request.Request;
import com.chess.common.response.Error;
import com.chess.common.response.Response;
import com.chess.common.response.Success;
import com.chess.common.results.*;

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
        switch (s) {
            case "pawn":
                return PieceType.PAWN;
            case "rook":
                return PieceType.ROOK;
            case "bishop":
                return PieceType.BISHOP;
            case "knight":
                return PieceType.KNIGHT;
            case "king":
                return PieceType.KING;
            case "queen":
                return PieceType.QUEEN;
            default:
                throw new Exception("piece type unknown");
        }
    }

    private static String readString(Scanner scanner) throws Exception {
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
            if (header.equals("turn"))
                return new SetTurn(scanner);
            else
                return null;
        } catch (Exception e) {
            return null;
        }
    }

    public static Response decodeResponse(String input) {
        try {
            Scanner scanner = new Scanner(input);
            if (!readString(scanner).equals("response"))
                return null;
            String header = readString(scanner);
            if (header.equals("error"))
                return new Error(scanner);
            if (header.equals("success"))
                return new Success(scanner);
            else
                return null;
        } catch (Exception e) {
            return null;
        }
    }

    public static Request decodeRequest(String input) {
        try {
            Scanner scanner = new Scanner(input);
            if (!readString(scanner).equals("request"))
                return null;
            String header = readString(scanner);
            if (header.equals("host"))
                return new Host(scanner);
            if (header.equals("join"))
                return new Join(scanner);
            else
                return null;
        } catch (Exception e) {
            return null;
        }
    }
}
