package fr.lernejo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpServer;
import fr.lernejo.navy_battle.POST;
import fr.lernejo.navy_battle.Sender;
import fr.lernejo.navy_battle.Server;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.IOException;
import java.net.http.HttpResponse;

public class StartHandlerTest {
    @ParameterizedTest
    @CsvSource({
        "9879"
    })
    void postResponseTest(String port) throws IOException, InterruptedException {
        HttpServer server = Server.create(Integer.parseInt(port));
        Sender sender = new Sender();

        POST send = new POST();
        send.id = "0";
        send.url = "test";
        send.message = "Are you there ?";
        String body = new ObjectMapper().writeValueAsString(send);

        HttpResponse<String> response = sender.postRequest("http://localhost:"+port+"/api/game/start", body);
        POST mapper = new ObjectMapper().readValue(response.body(),POST.class);

        Assertions.assertThat(mapper.id).isEqualTo("1");
        Assertions.assertThat(mapper.url).isEqualTo("test");
        Assertions.assertThat(mapper.message).isEqualTo("Here I am !");
    }
}
