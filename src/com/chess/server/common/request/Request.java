package com.chess.server.common.request;

public abstract class Request {
    public abstract void write(StringBuilder builder);
}
