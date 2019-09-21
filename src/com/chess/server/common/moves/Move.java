package com.chess.server.common.moves;

import com.chess.server.chessboard.pieces.Color;

public class Move {
    public int timeStamp;
    public Color color;
    public int gameId;

    public void decode(String[] tokens) throws Exception {
        gameId = Integer.parseInt(tokens[2]);

        if (tokens[3].equals("black"))
            color = Color.BLACK;
        else if (tokens[3].equals("white"))
            color = Color.WHITE;
        else
            throw new Exception("color decoding");

        timeStamp = Integer.parseInt(tokens[4]);
    }

    public String encode() {
        return "timeStamp: " + timeStamp + " color:" + color + " gameId:" + gameId;
    }
}