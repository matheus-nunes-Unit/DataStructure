/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfacesED.atividadeListaDuplamenteEncadeada;

import InterfacesED.ListaDuplamenteLigada.ListaDuplamenteLigada;
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
        Scanner scan = new Scanner(System.in);
        int menu = 1;
        ListaDuplamenteLigada<Pessoa> listaDePessoas = new ListaDuplamenteLigada<>();

        while (menu == 1) {
            System.out.println(
                    "1 – Incluir Pessoa\n"
                    + "2 – Excluir Pessoa\n"
                    + "3 – Consultar Pessoa\n"
                    + "4 – Listar Pessoa\n"
                    + "5 – Incluir Bem\n"
                    + "6 – Excluir Bem\n");
            System.out.println("Selecione uma opção do programa");
            int resposta = scan.nextInt();

            switch (resposta) {
                case (1):
                    System.out.println("Digite o nome da pessoa a ser cadastrada: ");
                    String nomeDaPessoaNova = scan.next();
                    System.out.println("Digite o codigo da pessoa a ser cadastrada: ");
                    int codigoDaNovaPessoa = scan.nextInt();
                    Pessoa novaPessoa = new Pessoa(codigoDaNovaPessoa, nomeDaPessoaNova);
                    if (!listaDePessoas.contem(novaPessoa)) {
                        listaDePessoas.adicionar(novaPessoa);
                        System.out.println("Pessoa Cadastrada com sucesso");
                    } else {
                        System.out.println("Pessoa com codigo especificado ja existe no sistema");
                    }
                    break;
                case (2):
                    System.out.println("Qual o codigo da pessoa a ser excluida do sistema");
                    int codigoPessoaExistente = scan.nextInt();
                    if (listaDePessoas.contem(new Pessoa(codigoPessoaExistente, ""))) {
                        listaDePessoas.remover(listaDePessoas.indexOf(new Pessoa(codigoPessoaExistente, "")));
                        System.out.println("Pessoa removida com sucesso");
                    } else {
                        System.out.println("Pessoa com codigo especificado nao existe no sistema");
                    }
                    break;
                case (3):
                    System.out.println("Qual o codigo da pessoa a ser consultada as informacoes");
                    int codigoPessoaInf = scan.nextInt();
                    if (listaDePessoas.contem(new Pessoa(codigoPessoaInf, ""))) {
                        Pessoa pessoaInf = listaDePessoas.buscar(listaDePessoas.indexOf((new Pessoa(codigoPessoaInf, ""))));
                        System.out.println(pessoaInf.consultaInformacoesPessoa());
                    } else {
                        System.out.println("Pessoa com codigo especificado nao existe no sistema");
                    }
                    break;
                case (4):
                    StringBuilder construtor = new StringBuilder();
                    construtor.append("Cod Pessoa  ").append("Nome da pessoa\t\t\t").append("Valor Total De Bens\n");
                    construtor.append("--------------  ----------------------------------  ----------------\n");
                    for (Pessoa pessoa : listaDePessoas) {
                        construtor.append(pessoa.getCodigo()).append("  ");
                        construtor.append(pessoa.getNome()).append("  ");
                        construtor.append(pessoa.getValorTotalBens()).append("  \n");
                    }
                    System.out.println(construtor.toString());
                    break;
                case (5):
                    System.out.println("Qual o codigo da pessoa a qual sera adicionado o bem: ");
                    int codigoPessoaBem = scan.nextInt();
                    if (listaDePessoas.contem(new Pessoa(codigoPessoaBem, ""))) {
                        Pessoa pessoaNovoBem = listaDePessoas.buscar(listaDePessoas.indexOf(new Pessoa(codigoPessoaBem, "")));
                        System.out.println("Digite o nome do bem a ser adicionado:");
                        String nomeDoBemNovo = scan.next();
                        System.out.println("Digite o valor do bem a ser adicionado:");
                        double valorDoBem = scan.nextDouble();
                        System.out.println("Digite o codigo do bem a ser adicionado:");
                        int codigoDoBemNovo = scan.nextInt();
                        Bem novoBem = new Bem(codigoDoBemNovo, nomeDoBemNovo, valorDoBem);
                        if (!pessoaNovoBem.verificaBem(novoBem)) {
                            pessoaNovoBem.adicionaBem(novoBem);
                            System.out.println("Bem adicionado com sucesso");
                        } else {
                            System.out.println("Bem ja existente");
                        }
                    } else {
                        System.out.println("Pessoa com codigo especificado nao existe no sistema");
                    }
                    break;
                case (6):
                    System.out.println("Qual o codigo da pessoa a qual sera excluido o bem: ");
                    int codigoPessoaBemRem = scan.nextInt();
                    if (listaDePessoas.contem(new Pessoa(codigoPessoaBemRem, ""))) {
                        Pessoa pessoaNovoBem = listaDePessoas.buscar(listaDePessoas.indexOf(new Pessoa(codigoPessoaBemRem, "")));
                        System.out.println("Digite o codigo do bem a ser removido:");
                        int codigoDoBemNovo = scan.nextInt();
                        Bem novoBem = new Bem(codigoDoBemNovo, "", 0.0);
                        if (pessoaNovoBem.verificaBem(novoBem)) {
                            pessoaNovoBem.removeBem(novoBem);
                            System.out.println("Bem removido com sucesso");
                        } else {
                            System.out.println("Bem nao existente");
                        }
                    } else {
                        System.out.println("Pessoa com codigo especificado nao existe no sistema");
                    }
                    break;
                default:
                    menu = 0;
                    break;
            }
        }
    }

}
