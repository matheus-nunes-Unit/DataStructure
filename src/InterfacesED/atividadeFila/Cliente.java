/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfacesED.atividadeFila;

import java.util.Objects;

/**
 *
 * @author Matheus Nunes
 */
public class Cliente {
    
    private String nome;
    private String cpf;
    private int idade;
    private Vetor<Pedido> listaDePedidosDespachados;
    
    public Cliente(String nome,String cpf,int idade){
        this.nome = nome;
        this.cpf = cpf;
        this.idade = idade;
        this.listaDePedidosDespachados = new Vetor<>();
    }
    
    public void adicionarNovoPedidoDespachado(Pedido pedido){
        listaDePedidosDespachados.adicionar(pedido);
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public int getIdade() {
        return idade;
    }

    public Vetor<Pedido> getListaDePedidosDespachados() {
        return listaDePedidosDespachados;
    }

    @Override
    public String toString() {
        return this.nome;
    }    
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Cliente other = (Cliente) obj;
        return Objects.equals(this.cpf, other.cpf);
    }
}
