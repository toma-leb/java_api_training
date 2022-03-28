package fr.lernejo.navy_battle;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.util.List;

public class FireHandler implements HttpHandler {

    private final Game game;

    public FireHandler(Game gameStatus) {
        this.game = gameStatus;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {

        Sender sender = new Sender();

        if (exchange.getRequestMethod().equals("GET")) {
            List<String> cellHeader = exchange.getRequestHeaders().get("cell");
            String cell = cellHeader.get(0);
            String consequence = this.game.shooAt(cell);
            Boolean shipLeft = this.game.isOver();
            String body = "{\"consequence\": \"" + consequence + "\", \"shipLeft\": " + shipLeft + "}";
            exchange.getResponseHeaders().set("Content-Type", "application/json");
            sender.response(exchange,202, body);
        }
        else {
            sender.response(exchange,404,"Not Found");
        }
    }
}
