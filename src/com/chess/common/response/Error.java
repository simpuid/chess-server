package com.chess.common.response;

import com.chess.parser.Encoder;

import java.util.Scanner;

public class Error extends Response {

    public Error() {
    }

    public Error(Scanner scanner) {
    }

    public void write(StringBuilder builder) {
        Encoder.write("response error", builder);
    }
}
