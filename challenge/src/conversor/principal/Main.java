package conversor.principal;

import menu.ControladorMenu;
import modelos.Conversor;
import modelos.ConversorAPI;
import modelos.Requisicao;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("Seja bem-vinda(o) ao Conversor de Moedas =]\n");
        Scanner sc = new Scanner(System.in);

        // Loop principal de exibição do menu
        while(true) {

            // Define as moedas inicial e final de acordo com a opção do menu
            ControladorMenu controlador = new ControladorMenu();
            controlador.exibeMenu();
            int opcao = controlador.getOpcaoUsuario();

            controlador.defineMoedas();
            String moedaInicial = controlador.getMoedaInicial();
            String moedaFinal = controlador.getMoedaFinal();

            if (opcao == 7) {
                System.out.println("Programa encerrado!");
                break;
            }

            // Se não escolheu uma opção válida, exibe o menu novamente para outra escolha
            else if(!controlador.defineMoedas()){
                System.out.println("Opção inválida! Escolha outra.");
                continue;
            }

            // Faz a requisição para a API
            Requisicao req = new Requisicao(moedaInicial);
            req.montaEndereco();
            ConversorAPI conversorAPI = req.enviaRequisicao();

            // Exibe a taxa atual das moedas pedidas
            double taxa = conversorAPI.conversion_rates().get(moedaFinal);
            System.out.println(String.format("Taxa atual: 1 %s = %f %s", moedaInicial, taxa, moedaFinal));

            // Pede o valor para conversão
            System.out.println("Digite o valor que deseja converter:");
            double valorUsuario = sc.nextDouble();

            // Converte o valor pedido pelo usuário
            Conversor conversor = new Conversor(conversorAPI, moedaInicial, moedaFinal);
            conversor.converte(valorUsuario);
        }
    }
}
