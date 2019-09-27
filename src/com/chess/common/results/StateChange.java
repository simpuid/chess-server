package com.chess.common.results;

import com.chess.parser.Encoder;

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
}
