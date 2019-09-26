package com.chess.server;

import com.chess.server.chessboard.ChessBoard;
import com.chess.server.common.moves.Move;
import com.chess.server.common.request.Host;
import com.chess.server.common.request.Join;
import com.chess.server.common.request.Request;
import com.chess.server.common.results.Result;

import java.net.ServerSocket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

class Server {
    private IdGenerator idGenerator;
    public ChessBoard board;
    public Map<Integer, Game> map = Collections.synchronizedMap(new HashMap<Integer, Game>(16));
    private ServerSocket serverSocket;

    Server(int port) throws Exception {
        idGenerator = new IdGenerator();
        board = new ChessBoard();
        serverSocket = new ServerSocket(port);
    }

    void run() {
//        ChessBoard board = new ChessBoard();
//        System.out.println(board.lineTest(new Position(2, 1), new Position(2, 6)));
//        board.print();
//        Console console = new Console();
//        console.run(this);
        Test test = new Test();
        test.test(test.testParsers());
        while (true) {
            try {
                Player player = new Player(serverSocket.accept(), this);
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
                System.out.println("found game");
                Game g = map.get(id);
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
