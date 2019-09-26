package com.chess.server.common.request;

import com.chess.server.parser.Encoder;

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
