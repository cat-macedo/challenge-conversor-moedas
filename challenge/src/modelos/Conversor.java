package modelos;

public class Conversor {
    private String moedaInicial;
    private String moedaFinal;
    private double cotacaoMoedaFinal;

    public Conversor(ConversorAPI conversorAPI, String moedaInicial, String moedaFinal) {
        this.moedaInicial = moedaInicial;
        this.moedaFinal = moedaFinal;
        this.cotacaoMoedaFinal = conversorAPI.conversion_rates().get(moedaFinal);
    }

    public String getMoedaInicial() {
        return moedaInicial;
    }

    public String getMoedaFinal() {
        return moedaFinal;
    }

    public double getCotacaoMoedaFinal() {
        return cotacaoMoedaFinal;
    }

    // Função de conversão do valor pedido pelo usuário para moeda correspondente à opção do menu
    public void converte(double valorUsuario) {
        double resultado = valorUsuario * this.cotacaoMoedaFinal;
        System.out.println(String.format("Valor %.2f [%s] corresponde ao valor final de =>>> %f [%s]\n", valorUsuario, this.moedaInicial, resultado, this.moedaFinal));
    }
}
