package fr.lernejo.navy_battle;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

public class Server {
    public void create(int port) {
        try {
            HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
            server.createContext("/ping", new PingHandler());
            server.createContext("/api/game/start", new StartHandler());
            server.createContext("/api/game/fire", new FireHandler());
            server.setExecutor(Executors.newSingleThreadExecutor());
            server.start();
        } catch (IOException e) {
            System.out.println("Exception has occured :" + e);
        }
    }
}
