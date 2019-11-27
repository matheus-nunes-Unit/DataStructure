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
public class Produto {
    
    private String nome;
    private int idDoProduto;

    public Produto(String nome, int idDoProduto) {
        this.nome = nome;
        this.idDoProduto = idDoProduto;
    }

    public String getNome() {
        return nome;
    }

    public int getIdDoProduto() {
        return idDoProduto;
    }

    @Override
    public String toString() {
        return "Nome: " + this.nome + " | ID: " + this.idDoProduto;
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
        final Produto other = (Produto) obj;
        if (this.idDoProduto != other.idDoProduto) {
            return false;
        }
        return true;
    }
    
}
