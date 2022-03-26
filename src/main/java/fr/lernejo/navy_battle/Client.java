package fr.lernejo.navy_battle;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.http.HttpResponse;

public class Client {

    public void start(String ClientAddress, String ServerAddress) throws IOException, InterruptedException {
        Server server = new Server();
        server.create(Integer.parseInt(ClientAddress));

        Sender sender = new Sender();

        POST send = new POST();
        send.id = "0";
        send.url = ClientAddress;
        send.message = "Are you there ?";
        String body = new ObjectMapper().writeValueAsString(send);

        HttpResponse<String> response = sender.postRequest(ServerAddress+"/api/game/start", body);
        POST mapper = new ObjectMapper().readValue(response.body(),POST.class);
        System.out.println(mapper.id);
        System.out.println(mapper.message);
        System.out.println(mapper.url);
    }
}
