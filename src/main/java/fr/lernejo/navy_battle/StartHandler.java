package fr.lernejo.navy_battle;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;


import java.io.IOException;
import java.util.Map;


public class StartHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        Sender sender = new Sender();

        if (exchange.getRequestMethod().equals("POST")) {
            String response = StreamReader.toString(exchange.getRequestBody());
            Map<String, String> map = new ObjectMapper().readValue(response, new TypeReference<>() {});

            String id = String.valueOf(Integer.parseInt(map.get("id")) + 1);
            String url = exchange.getLocalAddress().getHostString()+":"+exchange.getLocalAddress().getPort();
            String body = "{\"id\":\""+id+"\", \"url\":\"" + url + "\", \"message\":\"Here I am !\"}";
            sender.response(exchange,202, body);
        }
        else {
            sender.response(exchange,404,"Not Found");
        }
    }
}

