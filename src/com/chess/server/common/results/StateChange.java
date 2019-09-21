package com.chess.server.common.results;

import java.util.ArrayList;

public class StateChange extends Result {
    public ArrayList<Delta> deltas;

    public StateChange() {
        deltas = new ArrayList<Delta>();
    }

    @Override
    public String encode() {
        String ret = "result change" + deltas.size();
        for (int i = 0; i < deltas.size(); i++) {
            Delta d = deltas.get(i);
            ret += " ";
            ret += d.pieceId;
            ret += " ";
            ret += d.positionId;
        }
        return ret;
    }
}
