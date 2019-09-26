package com.chess.server;

import com.chess.client.Client;

public class Main {

    public static void argumentError() {
        System.out.println("error:wrong arguments");
    }

    public static void main(String[] args) {
        if (args.length < 1) {
            argumentError();
            return;
        }
        if (args[0].equals("server")) {
            if (args.length < 2) {
                argumentError();
                return;
            }
            try {
                Server server = new Server(Integer.parseInt(args[1]));
                server.run();
            } catch (Exception e) {
                argumentError();
                return;
            }
        } else if (args[0].equals("client")) {
            if (args.length < 3) {
                argumentError();
                return;
            }
            try {
                Client client;
                if (args.length == 4)
                    client = new Client(args[1], Integer.parseInt(args[2]), Integer.parseInt(args[3]));
                else
                    client = new Client(args[1], Integer.parseInt(args[2]));
                client.run();
            } catch (Exception e) {
                argumentError();
                return;
            }
        } else {
            argumentError();
            return;
        }
    }
}
