package modelos;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ListaConversoes {
    private List<Conversao> listaConversoes;

    public ListaConversoes() {
        this.listaConversoes = new ArrayList<>();
    }

    public void adicionaConversao(Conversao conversao) {
        listaConversoes.add(conversao);
    }

    public int pegaTamanho() {
        return this.listaConversoes.size();
    }

    public void criaArq() throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        FileWriter esc = new FileWriter("conversoes.json");
        esc.write(gson.toJson(this.listaConversoes));
        esc.close();
        System.out.println("Arquivo de histórico criado com sucesso!");
    }

//    @Override
//    public String toString() {
//        StringBuilder sb = new StringBuilder("Histórico de conversões:\n");
//        for (Conversao c : this.listaConversoes) {
//            sb.append(c.toString()).append("\n");
//        }
//        return sb.toString();
//    }

}
