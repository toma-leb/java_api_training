package fr.lernejo.navy_battle;

import java.util.InvalidPropertiesFormatException;

public class Launcher {

    public static void main(String [] args) throws InvalidPropertiesFormatException {
        int argsLen = args.length;

        if (argsLen == 1) {
            Server server = new Server();
            server.create(Integer.parseInt(args[0]));
            System.out.println("Server Address : http://localhost:"+args[0]);
            Board serverBoard = new Board();
            serverBoard.init();
            serverBoard.serverBoard();
            serverBoard.check();
        }
        else if (argsLen == 2) {
            Client client = new Client();
            try {
                client.create(args[0], args[1]);
                client.start();
                client.fire("B2");
            } catch (Exception e) {
                System.out.println("Unable to initialize game");
            }
        }
        else {
            throw new IllegalArgumentException("Mandatory : [0] HTTP port, Optional : [1] Server Address");
        }
    }
}
