package fr.lernejo.navy_battle;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

public class Server {

    private Game game;

    public void setGame(Game gameStatus) {
        this.game = gameStatus;
    }

    public void create(int port) {
        try {
            HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
            server.createContext("/ping", new PingHandler());
            server.createContext("/api/game/start", new StartHandler());
            server.createContext("/api/game/fire", new FireHandler(this.game));
            server.setExecutor(Executors.newSingleThreadExecutor());
            server.start();
        } catch (IOException e) {
            System.out.println("Exception has occured :" + e);
        }
    }
}
