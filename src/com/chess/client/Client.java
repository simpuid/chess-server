package com.chess.client;

import com.chess.server.chessboard.pieces.Color;
import com.chess.server.common.request.Host;
import com.chess.server.common.request.Join;
import com.chess.server.common.response.Error;
import com.chess.server.common.response.Response;
import com.chess.server.common.response.Success;
import com.chess.server.common.results.Result;
import com.chess.server.parser.Decoder;
import com.chess.server.parser.Encoder;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private int gameId;
    private Color color;

    public Client(String address, int port) throws Exception {

        createSocket(address, port);
        out.println(Encoder.encode(new Host()));
        readResponse();
    }

    public Client(String address, int port, int gameID) throws Exception {
        System.out.println("game id" + gameID);
        createSocket(address, port);
        out.println(Encoder.encode(new Join(gameID)));
        readResponse();
    }

    public void createSocket(String address, int port) throws Exception {
        socket = new Socket(address, port);
        out = new PrintWriter(socket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        System.out.println("socket created");
    }

    private void readResponse() throws Exception {
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
        System.out.println(Encoder.encode(response));
    }

    public void disconnect() {
        try {
            in.close();
            out.close();
            socket.close();
        } catch (Exception e) {

        }
    }

    public void receiveCycle() {
        try {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                Result result = Decoder.decodeResult(inputLine);
                System.out.println("result" + Encoder.encode(result));
            }
            disconnect();
            System.out.println("player disconnected");
        } catch (Exception e) {
            System.out.println("player disconnected");
        } finally {
            disconnect();
        }
    }

    public void run() {
        System.out.println("hello");
        ClientBoard clientBoard = new ClientBoard(color, gameId);
        System.out.println("hello");
        clientBoard.displayBoard();
        System.out.println("hello");
        receiveCycle();
        Scanner scanner = new Scanner(System.in);
        scanner.nextInt();
    }
}
