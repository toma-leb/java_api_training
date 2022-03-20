package fr.lernejo.navy_battle;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.http.HttpResponse;

public class Launcher {
    public static void main(String [] args) {
        if (args.length != 1) {
            throw new IllegalArgumentException("Should have at least 1 argument : port number");
        }
        HttpServer server = Server.create(Integer.parseInt(args[0]));
        System.out.println(args[0]);
    }
}
