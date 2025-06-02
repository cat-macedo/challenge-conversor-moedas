package modelos;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import excecoes.RequisicaoInvalidaException;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Requisicao {
    private String moedaInicial;
    private String endereco;

    public Requisicao(String moedaInicial) {
        this.moedaInicial = moedaInicial;
    }

    public void montaEndereco() {
         this.endereco = "https://v6.exchangerate-api.com/v6/784c31b2384e5f7585139112/latest/" + this.moedaInicial;
    }

    public ConversorAPI enviaRequisicao() throws IOException, InterruptedException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(this.endereco)).build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString()); // Armazena a resposta

        // Verifica se o código de status HTTP não é 200 (OK)
        if (response.statusCode() != 200) {
            throw new RequisicaoInvalidaException("Erro na requisição HTTP: código " + response.statusCode());
        }

        String json = response.body();

        // Verificar se o JSON mostra erro
        if (json.contains("\"result\":\"error\"")) {
            throw new RequisicaoInvalidaException("Erro na API: verifique se o código da moeda está correto.");
        }

        // Converte JSON para classe tradutora
        ConversorAPI conversorAPI = gson.fromJson(json, ConversorAPI.class);

        return conversorAPI;
    }
}
