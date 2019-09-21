package com.chess.server.parser;

import com.chess.server.common.moves.*;

public class Decoder {
    public Move decode(String input) {
        try {
            String[] tokens = input.split(" ", 11);
            if (tokens.length < 5)
                return null;
            Move result;
            if (tokens[1].equals("normal"))
                result = new MoveNormal();
            else if (tokens[1].equals("terminate"))
                result = new MoveTerminate();
            else if (tokens[1].equals("castle"))
                result = new MoveCastle();
            else if (tokens[1].equals("upgrade"))
                result = new MoveUpgrade();
            else
                return null;
            result.decode(tokens);
            return result;
        } catch (Exception e) {
            return null;
        }
    }
}
