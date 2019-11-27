/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfacesED.atividadePilha;

import java.util.Scanner;

/**
 *
 * @author Matheus Nunes
 */
public class menu {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner scaner = new Scanner(System.in);
        Estacionamento estacionamento = new Estacionamento();
        boolean menu = true;
        while (menu) {
            System.out.println("Digite uma das opcoes do menu abaixo(Caso deseje finalizar o programa digite qualquer valor que nao esteja no menu):");
            System.out.println("1 - Inserir carro nos estacionamento(Carro Chegando)\n"
                    + "2 - Retirar carro do estacionamento(Carro saindo)\n"
                    + "3 - Listar carros do estacionamento\n");
            int opcaoDoMenu = scaner.nextInt();
            scaner.nextLine();
            switch (opcaoDoMenu) {
                case 1:
                    //Verificando se o estacionamento ja esta cheio
                    if (estacionamento.retornaQuantidadeDeCarrosNoEstacionamento() == 10) {
                        System.out.println("Estacionamento Lotado!");
                        break;
                    }
                    System.out.println("Digite o cpf do carro a ser inserido: ");
                    String cpfNovoCarro = scaner.nextLine();
                    Carro novoCarro = new Carro(cpfNovoCarro);
                    System.out.println(estacionamento.estacionarCarro(novoCarro));
                    break;
                case 2:
                    if (estacionamento.retornaQuantidadeDeCarrosNoEstacionamento() == 0) {
                        System.out.println("Nao ha carros no estacionamento");
                        break;
                    }
                    System.out.println("Digite o cpf do carro a ser retirado do estacionamento: ");
                    String cpfRemoverCarro = scaner.nextLine();
                    System.out.println(estacionamento.sairComOCarro(new Carro(cpfRemoverCarro)));
                    break;
                case 3:
                    System.out.println(estacionamento.listarCarrosDoEstacionamento());
                    break;
                default:
                    menu = false;
                    break;
            }
        }
        System.out.println("Programa finalizado");
    }

}
