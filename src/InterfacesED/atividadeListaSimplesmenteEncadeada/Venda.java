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
public class Venda {
    
    private String dataDaVenda;
    private int QuantidadeVendida;
    private Double valorDaVenda;
    private Double custoUnitario;

    public Venda(String dataDaVenda, int QuantidadeVendida, Double valorDaVenda) {
        this.dataDaVenda = dataDaVenda;
        this.QuantidadeVendida = QuantidadeVendida;
        this.valorDaVenda = valorDaVenda;
        this.custoUnitario = this.calculaValorUnitario();
    }
    
    private Double calculaValorUnitario(){
        Double valorUnitario = this.valorDaVenda / this.QuantidadeVendida;
        return valorUnitario;
    }

    public String getDataDaVenda() {
        return dataDaVenda;
    }

    public int getQuantidadeVendida() {
        return QuantidadeVendida;
    }

    public Double getValorDaVenda() {
        return valorDaVenda;
    }

    public Double getCustoUnitario() {
        return custoUnitario;
    }

    @Override
    public String toString() {
        StringBuilder construtor = new StringBuilder();
        construtor.append(this.dataDaVenda).append(" | ").append(this.QuantidadeVendida).append(" | ").append(this.valorDaVenda).append(" | ")
                .append(this.custoUnitario);
        return construtor.toString();
    }    
}
