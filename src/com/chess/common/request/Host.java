package com.chess.common.request;

import com.chess.parser.Encoder;

import java.util.Scanner;

public class Host extends Request {
    public Host() {
    }

    public Host(Scanner scanner) throws Exception {
    }

    public void write(StringBuilder builder) {
        Encoder.write("request host", builder);
    }
}
