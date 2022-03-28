package fr.lernejo.navy_battle;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.Map;

public class Client {

    private String ClientAddress;
    private String ServerAddress;
    private final Server client = new Server();
    private final Sender sender = new Sender();

    public void create(String ClientAddress, String ServerAddress) {
        this.ClientAddress = ClientAddress;
        this.ServerAddress = ServerAddress;
        this.client.create(Integer.parseInt(this.ClientAddress));
    }

    public void start() throws IOException, InterruptedException {

        String body = "{\"id\":\"0\", \"url\":\"" + this.ClientAddress + "\", \"message\":\"Are you there ?\"}";

        HttpResponse<String> response = this.sender.postRequest(this.ServerAddress+"/api/game/start", body);
        Map<String, String> map = new ObjectMapper().readValue(response.body(), new TypeReference<>() {});

        if (map.get("id").equals("1") && map.get("message").equals("Here I am !")) {
            System.out.println("Ready to start.");
        }
    }

    public void fire(String cell) throws IOException, InterruptedException {
        HttpResponse<String> response = this.sender.getRequest(this.ServerAddress+"/api/game/fire", cell);
        Map<String, String> map = new ObjectMapper().readValue(response.body(), new TypeReference<>() {});
        System.out.println(map);
    }
}
