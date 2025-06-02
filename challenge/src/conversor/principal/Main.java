package conversor.principal;

import excecoes.RequisicaoInvalidaException;
import menu.ControladorMenu;
import modelos.*;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("*******************************************************");
        System.out.println("$     Seja bem-vinda(o) ao Conversor de Moedas!    $");
        System.out.println("*******************************************************");

        Scanner sc = new Scanner(System.in);
        ListaConversoes historico = new ListaConversoes();


        // Loop principal de exibição do menu
        while(true) {
            // Define as moedas inicial e final de acordo com a opção do menu
            ControladorMenu controlador = new ControladorMenu();

            // Tratamento de exceção caso o usuário digite algo que não seja um inteiro
            try {
                controlador.exibeMenu();
            }
            catch(InputMismatchException e) {
                System.out.println("O valor digitado precisa ser um inteiro de 1 a 7!");
                continue; // volta ao menu
            }

            int opcao = controlador.getOpcaoUsuario();

            controlador.defineMoedas();
            String moedaInicial = controlador.getMoedaInicial();
            String moedaFinal = controlador.getMoedaFinal();

            if (opcao == 7) {
                System.out.println("Conversor encerrado. Até mais!");
                break;
            }

            // Se não escolheu uma opção válida, exibe o menu novamente para outra escolha
            else if(!controlador.defineMoedas()){
                System.out.println("Opção inválida! Escolha outra.");
                continue; // volta ao menu
            }

            ConversorAPI conversorAPI;
            // Tratamento de exceção de erro na requisição à API
            try {
                // Faz a requisição para a API
                Requisicao req = new Requisicao(moedaInicial);
                req.montaEndereco();
                conversorAPI = req.enviaRequisicao();
            }
            catch(RequisicaoInvalidaException e) {
                System.out.println(e.getMessage());
                continue;
            }

            // Exibe a taxa atual das moedas pedidas
            double taxa = conversorAPI.conversion_rates().get(moedaFinal);
            System.out.println(String.format("Taxa atual: 1 %s = %f %s", moedaInicial, taxa, moedaFinal));

            // Pede o valor para conversão
            System.out.print("Digite o valor que deseja converter:\n> ");
            double valorUsuario;

            while (true) {
                try { // Trata exceção para casos de valor não numérico
                    valorUsuario = sc.nextDouble();
                    break;
                } catch (InputMismatchException e) {
                    System.out.print("Insira um valor numérico!\n> ");
                    sc.nextLine(); // limpa o buffer
                }
            }

            // Converte o valor pedido
            Conversor conversor = new Conversor(conversorAPI, moedaInicial, moedaFinal);
            double res = conversor.converte(valorUsuario);

            // Adiciona conversão atual na lista de histórico
            Conversao conversao = new Conversao(historico.pegaTamanho() + 1, moedaInicial, moedaFinal, valorUsuario, res);
            historico.adicionaConversao(conversao);
        }

        // Cria arquivo com histórico de conversões do usuário
        historico.criaArq();
    }
}
