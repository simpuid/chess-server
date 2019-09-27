package com.chess.common.results;

import com.chess.parser.Encoder;

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
