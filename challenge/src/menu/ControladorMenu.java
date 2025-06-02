package menu;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ControladorMenu {
    private int opcaoUsuario;
    private String moedaInicial;
    private String moedaFinal;

    public void exibeMenu() {
        System.out.println("\nOpções de conversão:");
        System.out.println("1) Dólar [USD] → Real brasileiro [BRL]");
        System.out.println("2) Real brasileiro [BRL] → Dólar australiano [AUD]");
        System.out.println("3) Euro [EUR] → Real brasileiro [BRL]");
        System.out.println("4) Iene Japonês [JPY] → Real brasileiro [BRL]");
        System.out.println("5) Real brasileiro [BRL] → Dólar de Fiji [FJD]");
        System.out.println("6) Dólar canadense [CAD] → Real brasileiro [BRL]");
        System.out.println("7) Sair");
        System.out.println("*******************************************************");
        System.out.println("Escolha uma opção válida:");
        System.out.print("> ");

        // Lê a opção do menu escolhida pelo usuário
        Scanner sc = new Scanner(System.in);
        this.opcaoUsuario = sc.nextInt();
    }

    // Define as moedas de acordo com a opção do menu
    public boolean defineMoedas() {

        if (this.opcaoUsuario == 1) {
            this.moedaInicial = "USD";
            this.moedaFinal = "BRL";
        }
        else if (this.opcaoUsuario == 2) {
            this.moedaInicial = "BRL";
            this.moedaFinal = "AUD";
        }
        else if (this.opcaoUsuario == 3) {
            this.moedaInicial = "EUR";
            this.moedaFinal = "BRL";
        }
        else if (this.opcaoUsuario == 4) {
            this.moedaInicial = "JPY";
            this.moedaFinal = "BRL";
        }
        else if (this.opcaoUsuario == 5) {
            this.moedaInicial = "BRL";
            this.moedaFinal = "FJD";
        }
        else if (this.opcaoUsuario == 6) {
            this.moedaInicial = "CAD";
            this.moedaFinal = "BRL";
        }
        else return false;
        return true;
    }

    public int getOpcaoUsuario() {
        return opcaoUsuario;
    }

    public String getMoedaInicial() {
        return moedaInicial;
    }

    public String getMoedaFinal() {
        return moedaFinal;
    }
}
