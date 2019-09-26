package com.chess.server.common.results;

import com.chess.server.parser.Encoder;

import java.util.Scanner;

public class InvalidMove extends Result {

    public InvalidMove() {
    }

    public InvalidMove(Scanner scanner) throws Exception {
    }

    public void write(StringBuilder builder) {
        Encoder.write("result invalid", builder);
    }
}
