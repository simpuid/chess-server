package com.chess.server.common.results;

public class InvalidMove extends Result {
    @Override
    public String encode() {
        return "invalid move";
    }
}
