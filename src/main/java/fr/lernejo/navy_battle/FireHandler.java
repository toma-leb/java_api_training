package fr.lernejo.navy_battle;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.util.List;

public class FireHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {

        Sender sender = new Sender();

        if (exchange.getRequestMethod().equals("GET")) {
            List<String> cell = exchange.getRequestHeaders().get("cell");
            String shootAt = cell.get(0);

            GET send = new GET();

            if (shootAt.equals("A1")) {
                send.consequence = "miss";
            }
            else if (shootAt.equals("B2")) {
                send.consequence = "hit";
            }
            else {
                send.consequence = "sunk";
            }

            send.shipLeft = true;
            String body = new ObjectMapper().writeValueAsString(send);

            sender.response(exchange,202, body);
        }
        else {
            sender.response(exchange,404,"Not Found");
        }
    }
}
