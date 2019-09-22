package com.chess.server.common.results;

public abstract class Result {
    public abstract String encode();

    public abstract void write(StringBuilder builder);
}
