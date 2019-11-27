/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfacesED.atividadeListaSimplesmenteEncadeada;

import InterfacesED.ListaSimplesmenteLigada.ListaSimplesmenteLigada;

/**
 *
 * @author Matheus Nunes
 */
public class Produto {

    private int codigoDoProduto;
    private String nomeDoProduto;
    private int quantidadeEmEstoque;
    private Double custoUnitario;
    private ListaSimplesmenteLigada<Compra> listaDeCompras;
    private ListaSimplesmenteLigada<Venda> listaDeVendas;

    public Produto(int codigoDoProduto, String nomeDoProduto) {
        this.codigoDoProduto = codigoDoProduto;
        this.nomeDoProduto = nomeDoProduto;
        this.quantidadeEmEstoque = 0;
        this.custoUnitario = 0.0;
        this.listaDeCompras = new ListaSimplesmenteLigada<>();
        this.listaDeVendas = new ListaSimplesmenteLigada<>();
    }

    public boolean registrarVenda(Venda novaVenda) {
        if (novaVenda.getQuantidadeVendida() < this.quantidadeEmEstoque) {
            this.quantidadeEmEstoque -= novaVenda.getQuantidadeVendida();
            this.listaDeVendas.adicionar(novaVenda);
            return true;
        }
        return false;
    }

    public void registrarCompra(Compra novaCompra) {
        if (!novaCompra.getValorDaCompra().equals(this.custoUnitario)) {
            this.custoUnitario = novaCompra.calculaValorUnitario();
        }
        this.quantidadeEmEstoque += novaCompra.getQuantidadeComprada();
        this.listaDeCompras.adicionar(novaCompra);
    }

    public String retornaHistoricoVenda() {
        StringBuilder construtor = new StringBuilder();
        construtor.append("Data Da Venda | Quantidade Vendida | Valor Da Venda | Custo Unitario Na Venda\n");
        for (Venda vendaAtual : this.listaDeVendas) {
            construtor.append(vendaAtual.toString()).append("\n");
        }
        return construtor.toString();
    }

    public String retornaHistoricoCompra() {
        StringBuilder construtor = new StringBuilder();
        construtor.append("Data Da Compra | Quantidade comprada | Valor Da Compra\n");
        for (Compra compraAtual : this.listaDeCompras) {
            construtor.append(compraAtual.toString()).append("\n");
        }
        return construtor.toString();
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        Produto produto = (Produto) obj;

        return (this.codigoDoProduto == produto.codigoDoProduto);
    }

    @Override
    public String toString() {
        StringBuilder construtor = new StringBuilder();
        construtor.append(this.nomeDoProduto).append(" | ").append(this.codigoDoProduto).append(" | ").append(this.quantidadeEmEstoque)
                .append(" | ").append(this.custoUnitario);
        return construtor.toString();
    }

}
