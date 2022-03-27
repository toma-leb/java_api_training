package fr.lernejo.navy_battle;

import java.util.InvalidPropertiesFormatException;

public class Launcher {

    public static void main(String [] args) {
        int argsLen = args.length;

        if (argsLen == 1) {
            Game serverGame = new Game();
            serverGame.init();
            Server server = new Server();
            server.setGame(serverGame);
            server.create(Integer.parseInt(args[0]));
            System.out.println("Server Address : http://localhost:"+args[0]);
        }
        else if (argsLen == 2) {
            Client client = new Client();
            try {
                client.create(args[0], args[1]);
                client.start();
                Game clientGame = new Game();
                clientGame.init();
                client.fire("D7");
                client.fire("E7");
                client.fire("F7");
            } catch (Exception e) {
                System.out.println(e + " Unable to initialize game");
            }
        }
        else {
            throw new IllegalArgumentException("Mandatory : [0] HTTP port, Optional : [1] Server Address");
        }
    }
}
