package com.chess.server;

import com.chess.server.chessboard.pieces.Color;
import com.chess.server.common.moves.Move;
import com.chess.server.common.results.GameFinished;
import com.chess.server.common.results.InvalidMove;
import com.chess.server.parser.Encoder;

public class Game {
    Player host;
    Player visitor;
    Server server;
    int gameId;

    Game(Player host, int id, Server server) {
        this.host = host;
        this.visitor = null;
        this.server = server;
        gameId = id;
    }

    public void setVisitor(Player player) {
        visitor = player;
    }

    public void process(Move move, Player player) {
        player.send(Encoder.encode(new InvalidMove()));
    }

    public Color getAssignedColor(Player player) {
        if (player == host)
            return Color.WHITE;
        return Color.BLACK;
    }

    public void disconnectPlayer(Player player) {
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

    public boolean isFull() {
        return visitor != null;
    }

    public void close() {
        if (host != null)
            host.close();
        if (visitor != null)
            visitor.close();
    }
}
