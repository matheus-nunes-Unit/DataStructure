/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfacesED.atividadeListaDuplamenteEncadeada;

import InterfacesED.ListaDuplamenteLigada.ListaDuplamenteLigada;

/**
 *
 * @author Matheus Nunes
 */
public class Pessoa {
    
    private int codigo;
    private String nome;
    private ListaDuplamenteLigada<Bem> listaDeBens;

    public Pessoa(int codigo, String nome) {
        this.codigo = codigo;
        this.nome = nome;
        this.listaDeBens = new ListaDuplamenteLigada<>();
    }
    
    public String consultaInformacoesPessoa(){
        StringBuilder construtor = new StringBuilder();
        construtor.append("Cod Pessoa: ").append(this.codigo).append("    Nome da Pessoa: ").append(this.nome).append("\n");
        construtor.append("Relacao de bens\n");
        construtor.append("CodBem  ").append("Nome do bem\t\t").append("Valor\n");
        construtor.append("------   ---------------------------   -------------\n");
        for (Bem bem : this.listaDeBens) {
            construtor.append(bem.getCodigo()).append("  ");
            construtor.append(bem.getNome()).append("\t\t");
            construtor.append(bem.getValor()).append("\n");
        }
        return construtor.toString();
    }
    
    public boolean verificaBem(Bem bem){
        return this.listaDeBens.contem(bem);
    }
    
    public void adicionaBem(Bem novoBem){
        this.listaDeBens.adicionar(novoBem);
    }

    public void removeBem(Bem bem){
        this.listaDeBens.remover(bem);
    }
    
    public int getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }
    
    public double getValorTotalBens(){
        double valorTotalDeBens = 0.0;
        for (Bem bem : this.listaDeBens) {
            valorTotalDeBens += bem.getValor();
        }
        return valorTotalDeBens;
    }
    
    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        Pessoa pessoa = (Pessoa) obj;

        return (pessoa.codigo == this.codigo);
    }

    
}
