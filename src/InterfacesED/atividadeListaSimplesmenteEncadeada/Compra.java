/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfacesED.atividadeListaSimplesmenteEncadeada;

/**
 *
 * @author Matheus Nunes
 */
public class Compra {
    
    private String dataDeCompra;
    private int quantidadeComprada;
    private Double valorDaCompra;

    public Compra(String dataDeCompra, int quantidadeComprada,Double valorDaCompra) {
        this.dataDeCompra = dataDeCompra;
        this.quantidadeComprada = quantidadeComprada;
        this.valorDaCompra = valorDaCompra;
    }
    
    public Double calculaValorUnitario(){
        Double valorUnitario = (this.valorDaCompra / this.quantidadeComprada);
        return valorUnitario;
    }

    public String getDataDeCompra() {
        return dataDeCompra;
    }

    public int getQuantidadeComprada() {
        return quantidadeComprada;
    }

    public Double getValorDaCompra() {
        return valorDaCompra;
    }
    
    @Override
    public String toString() {
        StringBuilder construtor = new StringBuilder();
        construtor.append(this.dataDeCompra).append(" | ").append(this.quantidadeComprada).append(" | ").append(this.valorDaCompra);
        return construtor.toString();
    }    
}
