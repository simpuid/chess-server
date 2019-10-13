package com.chess.server;

import com.chess.chessboard.ChessBoard;
import com.chess.common.moves.Move;
import com.chess.common.request.Host;
import com.chess.common.request.Join;
import com.chess.common.request.Request;
import com.chess.common.results.Result;

import java.net.ServerSocket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Server {
    private IdGenerator idGenerator;
    public ChessBoard board;
    public Map<Integer, Game> map = Collections.synchronizedMap(new HashMap<Integer, Game>(16));
    private ServerSocket serverSocket;

    public Server(int port) throws Exception {
        idGenerator = new IdGenerator();
        board = new ChessBoard();
        serverSocket = new ServerSocket(port);
    }

    public void run() {
        while (true) {
            try {
                Player player = new Player(serverSocket.accept(), this);
                System.out.println("player connected");
                player.start();
            } catch (Exception e) {
                System.out.println("player creation failed");
            }
        }
    }

    Result getResult(Move move) {
        return board.evaluate(move);
    }

    Game getGame(Request request, Player player) {
        if (request instanceof Host) {
            Game g = new Game(player, idGenerator.get(), this);
            map.put(g.gameId, g);
            return g;
        } else if (request instanceof Join) {
            int id = ((Join) request).gameId;
            if (map.containsKey(id)) {
                Game g = map.get(id);
                if (g.isFull())
                    return null;
                g.setVisitor(player);
                return g;
            } else {
                return null;
            }
        }
        System.out.println("wtf");
        return null;
    }

    void endGame(int id) {
        if (map.containsKey(id)) {
            System.out.println("ending game with id " + id);
            map.get(id).close();
            map.remove(id, map.get(id));
        }
    }
}
