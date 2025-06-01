package conversor.principal;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import modelos.Requisicao;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("Seja bem-vinda(o) ao Conversor de Moedas =]");

        Requisicao req = new Requisicao("USD");
        req.montaEndereco();
        req.enviaRequisicao();



    }
}
