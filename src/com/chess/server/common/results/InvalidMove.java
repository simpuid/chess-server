package com.chess.server.common.results;

import com.chess.server.parser.Encoder;

import java.util.Scanner;

public class InvalidMove extends Result {
    @Override
    public String encode() {
        return "invalid move";
    }

    public InvalidMove() {
    }

    public InvalidMove(Scanner scanner) throws Exception {
    }

    public void write(StringBuilder builder) {
        Encoder.write("result invalid", builder);
    }
}
