package fr.lernejo.navy_battle;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;


import java.io.IOException;
import java.util.logging.Handler;


public class StartHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        Sender sender = new Sender();

        if (exchange.getRequestMethod().equals("POST")) {
            String request = StreamReader.toString(exchange.getRequestBody());
            POST received = new ObjectMapper().readValue(request,POST.class);

            POST send = new POST();
            send.id = String.valueOf(Integer.parseInt(received.id) + 1);
            send.url = exchange.getLocalAddress().getHostString()+":"+exchange.getLocalAddress().getPort();
            send.message = "Here I am !";
            String body = new ObjectMapper().writeValueAsString(send);

            sender.response(exchange,202, body);
        }
        else {
            sender.response(exchange,404,"Not Found");
        }
    }
}

