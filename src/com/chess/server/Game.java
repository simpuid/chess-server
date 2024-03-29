package com.chess.server;

import com.chess.chessboard.ChessBoard;
import com.chess.chessboard.pieces.Color;
import com.chess.common.moves.Move;
import com.chess.common.results.GameFinished;
import com.chess.common.results.InvalidMove;
import com.chess.common.results.Result;
import com.chess.common.results.SetTurn;
import com.chess.parser.Encoder;

public class Game {
    private Player host;
    private Player visitor;
    private Server server;
    private ChessBoard chessBoard;
    int gameId;

    Game(Player host, int id, Server server) {
        this.host = host;
        this.visitor = null;
        this.server = server;
        this.chessBoard = new ChessBoard();
        gameId = id;
    }

    void setVisitor(Player player) {
        visitor = player;
    }

    public void setColor(Color color) {
        chessBoard.currentColor = color;
        if (host != null && visitor != null) {
            host.send(new SetTurn(color));
            visitor.send(new SetTurn(color));
            System.out.println("sending setcolor");
        }
    }

    void process(Move move) {
        System.out.println("mo");
        Result result = chessBoard.evaluate(move);
        host.send(Encoder.encode(result));
        visitor.send(Encoder.encode(result));
        if (!(result instanceof InvalidMove))
            setColor(chessBoard.currentColor);
    }

    Color getAssignedColor(Player player) {
        if (player == host)
            return Color.WHITE;
        return Color.BLACK;
    }

    void disconnectPlayer(Player player) {
        System.out.println("disconnecting");
        if (player == host) {
            if (visitor != null) {
                visitor.send(new GameFinished(Color.BLACK));
                visitor.close();
            }
            server.endGame(gameId);
        } else if (player == visitor) {
            if (host != null) {
                host.send(new GameFinished((Color.WHITE)));
                host.close();
            }
            server.endGame(gameId);
        }
    }

    boolean isFull() {
        return visitor != null;
    }

    void close() {
        if (host != null)
            host.close();
        if (visitor != null)
            visitor.close();
    }
}
