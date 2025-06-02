package modelos;

public record Conversao(int id, String moeda_inicial, String moeda_final, double valor_original, double valor_convertido) {
}
