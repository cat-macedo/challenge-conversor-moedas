package modelos;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Requisicao {
    private String moeda;
    private String endereco;

    public Requisicao(String moeda) {
        this.moeda = moeda;
    }

    public void montaEndereco() {
         this.endereco = "https://v6.exchangerate-api.com/v6/784c31b2384e5f7585139112/latest/" + this.moeda;
    }

    public void enviaRequisicao() throws IOException, InterruptedException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(this.endereco)).build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString()); // Armazena a resposta

        String json = response.body();

        ConversorAPI conversorAPI = gson.fromJson(json, ConversorAPI.class);
        // System.out.println(conversorAPI);
        System.out.println("Base: " + conversorAPI.base_code());
        System.out.println("USD para ARS: " + conversorAPI.conversion_rates().get("ARS"));
        System.out.println("USD para BRL: " + conversorAPI.conversion_rates().get("BRL"));

        // retornar conversorAPI
        // na main, passar ele para o construtor de Conversor, acho que junto com moeda inicial e final
        // na classe Conversor, fazer a conversão em si
        // na main, preparar o menu e o loop

    }
}
