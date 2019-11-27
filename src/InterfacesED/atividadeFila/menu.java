/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfacesED.atividadeFila;

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
        BancoDeDados bancoDeCadastros = new BancoDeDados();

        while (menu == 1) {
            System.out.println(
                    "1 – Cadastrar produto\n"
                    + "2 – Cadastrar cliente\n"
                    + "3 – Efetuar pedido\n"
                    + "4 – Despachar pedido\n"
                    + "5 – Exibir fila de pedidos\n"
                    + "6 - Exibir produtos cadastrados\n");
            System.out.println("Selecione uma opção do programa(para finalizar digite qualquer numero que nao esteja incluido no menu): ");
            int resposta = scan.nextInt();
            scan.nextLine();
            switch (resposta) {
                case (1):
                    System.out.println("Digite o nome do produto a ser cadastrado: ");
                    String nomeProduto = scan.nextLine();
                    System.out.println("Digite o codigo do produto a ser cadastrado: ");
                    int idProduto = scan.nextInt();
                                scan.nextLine();
                    Produto novoProduto = new Produto(nomeProduto, idProduto);
                    System.out.println(bancoDeCadastros.cadastrarProduto(novoProduto));
                    break;
                case (2):
                    System.out.println("Digite o nome do cliente a ser cadastrado: ");
                    String nomeCliente = scan.nextLine();
                    System.out.println("Digite a idade do cliente a ser cadastrado: ");
                    int idadeCliente = scan.nextInt();
                                scan.nextLine();
                    System.out.println("Digite o cpf do cliente a ser cadastrado: ");
                    String cpfCliente = scan.nextLine();
                    Cliente novoCliente = new Cliente(nomeCliente, cpfCliente, idadeCliente);
                    System.out.println(bancoDeCadastros.cadastrarCliente(novoCliente));
                    break;
                case (3):
                    //Inicialmente sera verificado se existe algum pedido no sistema para que se possa efetuar um pedido
                    if (!"Nao há produtos no sistema".equals(bancoDeCadastros.exibirRelatorioDeProdutosDisponiveisParaPedido())) {
                        //Lista de produtos do pedido que sera realizado
                        Lista<Produto> produtosPedidos = new Vetor<>();
                        System.out.println("Digite o cpf do cliente que realizara o pedido: ");
                        String cpfClientePedido = scan.nextLine();
                        System.out.println("Numero de produtos que serao pedidos: ");
                        int numeroDeProdutos = scan.nextInt();
                        scan.nextLine();
                        if (bancoDeCadastros.numeroDeprodutosNoSistema() == 0) {
                            System.out.println("Nao ha produtos no sistema");
                        } else if (numeroDeProdutos > bancoDeCadastros.numeroDeprodutosNoSistema() || numeroDeProdutos <= 0) {
                            System.out.println("Valor informado invalido");
                        } else if (numeroDeProdutos == bancoDeCadastros.numeroDeprodutosNoSistema()) {
                            System.out.println("Todos os itens do sistema fora adicionados no pedido");
                            //Adicionando todos os produtos do sistema no pedido
                            for (int i = 0; i < bancoDeCadastros.numeroDeprodutosNoSistema(); i++) {
                                produtosPedidos.adicionar(bancoDeCadastros.retornaProduto(i));
                            }
                            System.out.println(bancoDeCadastros.efetuarPedido(new Cliente("", cpfClientePedido, 0), produtosPedidos));
                        } else {
                            System.out.println("Abaixo estao os itens do sistema: ");
                            System.out.println("Caso deseje finalizar o registro do pedido digite -1");
                            for (int i = 0; i < numeroDeProdutos; i++) {
                                System.out.println(bancoDeCadastros.exibirRelatorioDeProdutosDisponiveisParaPedido());
                                System.out.println("Digite o numero do respectivo produto a ser adicionado no pedido "
                                        + "(Restam " + (numeroDeProdutos - i) + " produtos a serem adicionados no pedido): ");
                                int posicaoDoPedido = scan.nextInt();
                                scan.nextLine();
                                //Verificando se foi informada uma posicao valida de produto
                                if ((posicaoDoPedido >= 1) && (posicaoDoPedido <= bancoDeCadastros.numeroDeprodutosNoSistema())) {
                                    Produto produtoAserAdicionado = bancoDeCadastros.retornaProduto(posicaoDoPedido - 1);
                                    //Verificando se o respectivo produto ja esta no pedido
                                    if (!produtosPedidos.contem(produtoAserAdicionado)) {
                                        produtosPedidos.adicionar(produtoAserAdicionado);
                                        if (i == numeroDeProdutos - 1) {
                                            System.out.println(bancoDeCadastros.efetuarPedido(new Cliente("", cpfClientePedido, 0), produtosPedidos));
                                        }
                                    } else {
                                        System.out.println("O respectivo produto ja esta cadastrado no pedido. Por favor insira outro numero");
                                        i--;
                                    }
                                } else {
                                    //Verificando se o usuario cancelou a solicitacao de pedido
                                    if (posicaoDoPedido == -1) {
                                        System.out.println("Solicitação de pedido cancelada. O pedido nao foi cadastrado");
                                        break;
                                    }
                                    //Caso seja informada uma posicao invalida o usuario tera que novamente informar um valor valido para cadastro
                                    System.out.println("Posicao invalida.");
                                    i--;
                                }
                            }
                        }
                    }else{
                        System.out.println("O estoque de produtos esta vazio");
                    }
                    break;
                case (4):
                    System.out.println(bancoDeCadastros.despacharPedido());
                    break;
                case (5):
                    System.out.println(bancoDeCadastros.exibirFilaDePedidos());
                    break;
                case (6):
                    System.out.println(bancoDeCadastros.exibirRelatorioDeProdutosDisponiveisParaPedido());
                    break;
                default:
                    menu = 0;
                    break;
            }
        }
    }

}
