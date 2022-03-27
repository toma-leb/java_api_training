package fr.lernejo.navy_battle;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.http.HttpResponse;

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

        POST send = new POST();
        send.id = "0";
        send.url = this.ClientAddress;
        send.message = "Are you there ?";
        String body = new ObjectMapper().writeValueAsString(send);

        HttpResponse<String> response = this.sender.postRequest(this.ServerAddress+"/api/game/start", body);
        POST mapper = new ObjectMapper().readValue(response.body(),POST.class);

        if (mapper.id.equals("1") && mapper.message.equals("Here I am !")) {
            System.out.println("Server available. Ready to start.");
        }
    }

    public void fire(String cell) throws IOException, InterruptedException {

        HttpResponse<String> response = this.sender.getRequest(this.ServerAddress+"/api/game/fire", cell);
        GET mapper = new ObjectMapper().readValue(response.body(),GET.class);
        mapper.print();
    }
}
