package com.chess.server;

import com.chess.server.common.moves.Move;
import com.chess.server.common.results.Result;
import com.chess.server.parser.Decoder;
import com.chess.server.parser.Encoder;

import java.util.Scanner;

public class Console {
    void run() {
        Server server = new Server();
        Scanner scanner = new Scanner(System.in);
        Decoder decoder = new Decoder();
        Encoder encoder = new Encoder();
        boolean quit = false;
        while (!quit) {
            String input = scanner.nextLine();
            if (input.equals("quit")) {
                quit = true;
                continue;
            }
            Move move = decoder.decode(input);
            if (move == null) {
                System.out.println("wrong command");
                continue;
            }
            Result result = server.getResult(move);
            System.out.println("=>" + encoder.encode(result));
        }
    }
}
