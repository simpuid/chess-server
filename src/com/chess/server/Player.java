package com.chess.server;

import com.chess.server.common.moves.Move;
import com.chess.server.common.request.Request;
import com.chess.server.common.response.Error;
import com.chess.server.common.response.Success;
import com.chess.server.common.results.InvalidMove;
import com.chess.server.common.results.Result;
import com.chess.server.parser.Decoder;
import com.chess.server.parser.Encoder;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Player extends Thread {
    private Socket socket;
    private Server server;
    private PrintWriter out;
    private BufferedReader in;
    private Game game;

    public Player(Socket socket, Server server) throws Exception {
        this.socket = socket;
        this.server = server;
        out = new PrintWriter(socket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    public void send(String s) {
        try {
            out.println(s);
        } catch (Exception e) {
            System.out.println("send error");
        }
    }

    public void send(Result result) {
        send(Encoder.encode(result));
    }

    public void close() {
        try {
            in.close();
            out.close();
            socket.close();
        } catch (Exception e) {
        }
    }

    private void disconnect() {
        game.disconnectPlayer(this);
        close();
    }

    private boolean establishConnection() {
        try {
            Request request = Decoder.decodeRequest(in.readLine());
            if (request == null)
                return false;
            game = server.getGame(request, this);
            if (game == null) {
                send(Encoder.encode(new Error()));
                disconnect();
                return false;
            } else {
                send(Encoder.encode(new Success(game.gameId, game.getAssignedColor(this))));
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    private void receiveLoop() {
        try {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                Move move = Decoder.decodeMove(inputLine);
                game.process(move, this);
            }
            disconnect();
        } catch (Exception e) {
            disconnect();
        }
    }

    public void run() {
        if (!establishConnection())
            return;

        out.println(Encoder.encode(new InvalidMove()));

        receiveLoop();
    }
}
