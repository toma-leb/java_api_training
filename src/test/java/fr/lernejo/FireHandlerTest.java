package fr.lernejo;

public class FireHandlerTest {
    /*@ParameterizedTest
    @CsvSource({
        "9880"
    })
    void getResponseTest(String port) throws IOException, InterruptedException {
        Server server = new Server();
        server.create(Integer.parseInt(port));
        Sender sender = new Sender();

        HttpResponse<String> response = sender.getRequest("http://localhost:"+port+"/api/game/fire","A1");
        GET mapper = new ObjectMapper().readValue(response.body(),GET.class);

        Assertions.assertThat(mapper.consequence).isEqualTo("miss");
        Assertions.assertThat(mapper.shipLeft).isEqualTo(true);

    }*/
}
