package com.chess.common.request;

import com.chess.parser.Decoder;
import com.chess.parser.Encoder;

import java.util.Scanner;

public class Join extends Request {
    public int gameId;

    public Join(int id) {
        gameId = id;
    }

    public Join(Scanner scanner) throws Exception {
        gameId = Decoder.readInt(scanner);
    }

    public void write(StringBuilder builder) {
        Encoder.write("request join", builder);
        Encoder.write(gameId, builder);
    }

}
