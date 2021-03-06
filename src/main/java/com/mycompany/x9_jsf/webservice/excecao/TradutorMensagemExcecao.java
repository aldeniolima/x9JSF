package com.mycompany.x9_jsf.webservice.excecao;

import javax.json.JsonObject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

public class TradutorMensagemExcecao {

    private String URL = "http://mymemory.translated.net";

    public String getTraducao(String textoOriginal) {
        Client client = ClientBuilder.newClient();
        WebTarget webTarget = client.target(URL);
        webTarget = webTarget.path("api");
        webTarget = webTarget.path("get");
        webTarget = webTarget.queryParam("q", textoOriginal);
        webTarget = webTarget.queryParam("langpair", "en|pt-BR");
        JsonObject jsonObject = webTarget.request().get(JsonObject.class);
        jsonObject = jsonObject.getJsonObject("responseData");
        return jsonObject.getString("translatedText");
    }
}
