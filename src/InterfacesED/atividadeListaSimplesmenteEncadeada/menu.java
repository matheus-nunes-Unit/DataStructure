/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfacesED.atividadeListaSimplesmenteEncadeada;

import java.util.Scanner;
import InterfacesED.ListaSimplesmenteLigada.ListaSimplesmenteLigada;

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
        ListaSimplesmenteLigada<Produto> listaDeProdutosAtacadoo = new ListaSimplesmenteLigada<>();

        while (menu == 1) {
            System.out.println(
                    "1 – Cadastrar Produto\n"
                    + "2 – Registrar Compra\n"
                    + "3 – Registrar Venda\n"
                    + "4 – Exibir Informações de um produto\n"
                    + "5 – Imprimir Compras\n"
                    + "6 – Imprimir Vendas\n");
            System.out.println("Selecione uma opção do programa");
            int resposta = scan.nextInt();

            switch (resposta) {
                case (1):
                    System.out.println("Digite o nome do produto a ser cadastrado: ");
                    String nomeNovoProduto = scan.next();
                    System.out.println("Digite o codigo do produto a ser cadastrado: ");
                    int codigoDoNovoProduto = scan.nextInt();
                    Produto novoProduto = new Produto(codigoDoNovoProduto, nomeNovoProduto);
                    if (!listaDeProdutosAtacadoo.contem(novoProduto)) {
                        listaDeProdutosAtacadoo.adicionar(novoProduto);
                        System.out.println("Produto adicionado com suceso");
                    } else {
                        System.out.println("Produto ja existente");
                    }
                    break;
                case (2):
                    System.out.println("Qual o codigo do produto que sera realizado o cadastro da compra: ");
                    int codigoDoProdutoCompra = scan.nextInt();
                    if (listaDeProdutosAtacadoo.contem(new Produto(codigoDoProdutoCompra, ""))) {
                        Produto produtoAtualCompra = listaDeProdutosAtacadoo.buscar(listaDeProdutosAtacadoo.indexOf(new Produto(codigoDoProdutoCompra, "")));
                        System.out.println("Digite a data da compra(a mesma deve estar na formatação dd/mm/yyyy):");
                        String dataDaCompra = scan.next();
                        System.out.println("Digite a quantidade comprada do produto:");
                        int quantidadeComprada = scan.nextInt();
                        System.out.println("Digite o valor total da compra:");
                        Double valorTotalDaCompra = scan.nextDouble();
                        Compra novaCompra = new Compra(dataDaCompra, quantidadeComprada, valorTotalDaCompra);
                        produtoAtualCompra.registrarCompra(novaCompra);
                        System.out.println("Compra cadastrada com sucesso");
                    } else {
                        System.out.println("Produto Inexistente com o codigo citado.");
                    }
                    break;
                case (3):
                    System.out.println("Qual o codigo do produto que sera realizado o cadastro da venda: ");
                    int codigoDoProdutoVenda = scan.nextInt();
                    if (listaDeProdutosAtacadoo.contem(new Produto(codigoDoProdutoVenda, ""))) {
                        Produto produtoAtualVenda = listaDeProdutosAtacadoo.buscar(listaDeProdutosAtacadoo.indexOf(new Produto(codigoDoProdutoVenda, "")));
                        System.out.println("Digite a data da venda(a mesma deve estar na formatação dd/mm/yyyy):");
                        String dataDaVenda = scan.next();
                        System.out.println("Digite a quantidade vendida do produto:");
                        int quantidadeVendida = scan.nextInt();
                        System.out.println("Digite o valor total da venda:");
                        Double valorTotalDaVenda = scan.nextDouble();
                        Venda novaVenda = new Venda(dataDaVenda, quantidadeVendida, valorTotalDaVenda);
                        if (produtoAtualVenda.registrarVenda(novaVenda)) {
                            System.out.println("Venda cadastrada com sucesso");
                        } else {
                            System.out.println("Quantidade de produtos a serem vendidos, é menor que a quantidade salva no estoque");
                        }
                    } else {
                        System.out.println("Produto Inexistente com o codigo citado.");
                    }
                    break;
                case (4):
                    System.out.println("Qual o codigo do produto que sera mostrada as informacoes: ");
                    int codigoDoProdutoInformacoes = scan.nextInt();
                    if (listaDeProdutosAtacadoo.contem(new Produto(codigoDoProdutoInformacoes, ""))) {
                        Produto produtoAtualInformacoes = listaDeProdutosAtacadoo.buscar(listaDeProdutosAtacadoo.indexOf(new Produto(codigoDoProdutoInformacoes, "")));
                        System.out.println("Nome do Produto | Codigo do produto | Quantidade em estoque | Custo Unitario");
                        System.out.println(produtoAtualInformacoes);
                    } else {
                        System.out.println("Nao existe produto com o codigo informado");
                    }
                    break;
                case (5):
                    System.out.println("Qual o codigo do produto que sera mostrado o historico de compras: ");
                    int codigoDoProdutoHistoricoCompras = scan.nextInt();
                    if (listaDeProdutosAtacadoo.contem(new Produto(codigoDoProdutoHistoricoCompras, ""))) {
                        Produto produtoAtualInformacoes = listaDeProdutosAtacadoo.buscar(listaDeProdutosAtacadoo.indexOf(new Produto(codigoDoProdutoHistoricoCompras, "")));
                        System.out.println(produtoAtualInformacoes.retornaHistoricoCompra());
                    } else {
                        System.out.println("Nao existe produto com o codigo informado");
                    }
                    break;
                case (6):
                    System.out.println("Qual o codigo do produto que sera mostrado o historico de Vendas: ");
                    int codigoDoProdutoHistoricoVendas = scan.nextInt();
                    if (listaDeProdutosAtacadoo.contem(new Produto(codigoDoProdutoHistoricoVendas, ""))) {
                        Produto produtoAtualInformacoes = listaDeProdutosAtacadoo.buscar(listaDeProdutosAtacadoo.indexOf(new Produto(codigoDoProdutoHistoricoVendas, "")));
                        System.out.println(produtoAtualInformacoes.retornaHistoricoVenda());
                    } else {
                        System.out.println("Nao existe produto com o codigo informado");
                    }
                    break;
                default:
                    menu = 0;
                    break;
            }
        }
    }

}
