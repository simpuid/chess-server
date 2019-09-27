package com.chess.server;

import com.chess.common.moves.Move;
import com.chess.common.results.Result;
import com.chess.parser.Decoder;
import com.chess.parser.Encoder;

import java.util.Scanner;

public class Console {
    void run(Server server) {
        Scanner scanner = new Scanner(System.in);
        boolean quit = false;
        while (!quit) {
            String input = scanner.nextLine();
            if (input.equals("quit")) {
                quit = true;
                continue;
            }
            Move move = Decoder.decodeMove(input);
            if (move == null) {
                System.out.println("wrong command");
                continue;
            }
            Result result = server.getResult(move);
            System.out.println("=>" + Encoder.encode(result));
            server.board.print();
        }
    }
}
