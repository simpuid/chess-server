package com.chess.server.common.results;

import com.chess.server.parser.Encoder;

import java.util.ArrayList;
import java.util.Scanner;

public class StateChange extends Result {
    public ArrayList<Delta> deltas;

    public StateChange() {
        deltas = new ArrayList<Delta>();
    }

    public StateChange(ArrayList<Delta> deltas) {
        this.deltas = deltas;
    }

    public StateChange(Scanner scanner) throws Exception {
        int size = scanner.nextInt();
        deltas = new ArrayList<Delta>(size);
        for (int i = 0; i < size; i++) {
            deltas.add(new Delta(scanner.nextInt(), scanner.nextInt()));
        }
    }

    public void write(StringBuilder builder) {
        Encoder.write("result change", builder);
        Encoder.write(deltas.size(), builder);
        for (int i = 0; i < deltas.size(); i++) {
            Encoder.write(deltas.get(i).pieceId, builder);
            Encoder.write(deltas.get(i).positionId, builder);
        }
    }

    @Override
    public String encode() {
        String ret = "result change " + deltas.size();
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
