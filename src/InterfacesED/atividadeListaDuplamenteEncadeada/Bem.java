/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfacesED.atividadeListaDuplamenteEncadeada;

/**
 *
 * @author Matheus Nunes
 */
public class Bem {
    
    private int codigo;
    private String nome;
    private double valor;

    public Bem(int codigo, String nome, double valor) {
        this.codigo = codigo;
        this.nome = nome;
        this.valor = valor;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public double getValor() {
        return valor;
    }
       
    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        Bem bem = (Bem) obj;

        return (bem.codigo == this.codigo);
    }
    
}
