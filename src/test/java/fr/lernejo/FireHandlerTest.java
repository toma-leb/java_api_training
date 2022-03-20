package fr.lernejo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpServer;

import fr.lernejo.navy_battle.GET;
import fr.lernejo.navy_battle.Sender;
import fr.lernejo.navy_battle.Server;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.IOException;
import java.net.http.HttpResponse;

public class FireHandlerTest {
    @ParameterizedTest
    @CsvSource({
        "9880"
    })
    void getResponseTest(String port) throws IOException, InterruptedException {
        HttpServer server = Server.create(Integer.parseInt(port));

        HttpResponse<String> response = Sender.getRequest("http://localhost:"+port+"/api/game/fire","A1");
        GET mapper = new ObjectMapper().readValue(response.body(),GET.class);

        Assertions.assertThat(mapper.consequence).isEqualTo("miss");
        Assertions.assertThat(mapper.shipLeft).isEqualTo(true);

    }
}
