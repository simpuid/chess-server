package com.chess.client;

import com.chess.chessboard.pieces.Color;
import com.chess.common.moves.Move;
import com.chess.common.request.Host;
import com.chess.common.request.Join;
import com.chess.common.response.Error;
import com.chess.common.response.Response;
import com.chess.common.response.Success;
import com.chess.common.results.*;
import com.chess.parser.Decoder;
import com.chess.parser.Encoder;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private int gameId;
    private Color color;
    ClientBoard clientBoard;

    public Client(String address, int port) throws Exception {
        System.out.println("creating socket");
        createSocket(address, port);
        System.out.println("sending request");
        out.println(Encoder.encode(new Host()));
        readResponse();
        clientBoard = new ClientBoard(color, gameId, this);
        clientBoard.displayBoard();
    }

    public Client(String address, int port, int gameID) throws Exception {
        System.out.println("supplied game id is " + gameID);
        System.out.println("creating socket");
        createSocket(address, port);
        System.out.println("sending request");
        out.println(Encoder.encode(new Join(gameID)));
        readResponse();
        clientBoard = new ClientBoard(color, gameId, this);
        clientBoard.displayBoard();
    }

    public void sendMove(Move move) {
        out.println(Encoder.encode(move));
    }

    public void createSocket(String address, int port) throws Exception {
        System.out.println("trying to connect");
        socket = new Socket(address, port);
        System.out.println("connected");
        out = new PrintWriter(socket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        System.out.println("socket created");
    }

    private void readResponse() throws Exception {
        System.out.println("starting to read response");
        String inputString = in.readLine();
        if (inputString == null)
            throw new Exception("client creation error");
        Response response = Decoder.decodeResponse(inputString);
        if (response instanceof Error) {
            throw new Exception("error from server");
        } else if (response instanceof Success) {
            gameId = ((Success) response).gameId;
            color = ((Success) response).color;
        } else
            throw new Exception("unknown response");
        System.out.println("got response");
        System.out.println(Encoder.encode(response));
    }

    public void disconnect() {
        try {
            in.close();
            out.close();
            socket.close();
            System.out.println("disconnected");
        } catch (Exception e) {

        }
        clientBoard.setVisible(false);
        clientBoard.dispose();
    }

    public void receiveCycle() {
        try {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                Result result = Decoder.decodeResult(inputLine);

                if (result instanceof GameFinished)
                    clientBoard.processResult(result);
                else if (result instanceof InvalidMove)
                    clientBoard.processResult(result);
                else if (result instanceof SetTurn)
                    clientBoard.processResult(result);
                else if (result instanceof StateChange)
                    clientBoard.processResult(result);
            }
            disconnect();
        } catch (Exception e) {
            disconnect();
        }
    }

    public void run() {
        receiveCycle();
    }
}
