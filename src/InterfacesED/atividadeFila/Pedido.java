/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfacesED.atividadeFila;

/**
 *
 * @author Matheus Nunes
 */
public class Pedido {
    
    private Cliente cliente;
    private Lista<Produto> listaDeProdutos;

    public Pedido(Cliente cliente, Lista<Produto> listaDeProdutos) {
        this.cliente = cliente;
        this.listaDeProdutos = listaDeProdutos;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Lista<Produto> getListaDeProdutos() {
        return listaDeProdutos;
    }    
    
}
