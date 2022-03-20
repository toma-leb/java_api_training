package fr.lernejo.navy_battle;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;


import java.io.IOException;
import java.util.logging.Handler;


public class StartHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if (exchange.getRequestMethod().equals("POST")) {
            String request = StreamReader.toString(exchange.getRequestBody());
            POST received = new ObjectMapper().readValue(request,POST.class);

            POST send = new POST();
            send.id = "1";
            send.url = received.url;
            send.message = "Here I am !";
            String body = new ObjectMapper().writeValueAsString(send);

            Sender.response(exchange,202, body);
        }
        else {
            Sender.response(exchange,404,"Not Found");
        }
    }
}

