/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfacesED.atividadeVetor;

/**
 *
 * @author Matheus Nunes
 */
public class Aluno {

    private String nome;
    private int matricula;
    private double nota1;
    private double nota2;
    private double mediaNaDisciplina;

    public Aluno(String nome, int matricula) {
        this.nome = nome;
        this.matricula = matricula;
    }

    public void calculaMedia(double nota1,double nota2) {
        this.setNota1(nota1);
        this.setNota2(nota2);
        double media = ((this.nota1 * 4) + (this.nota2 * 6)) / 10;
        this.mediaNaDisciplina = media;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        Aluno aluno = (Aluno) obj;

        return ((aluno.nome.equals(this.nome)) && (aluno.matricula == this.matricula));
    }

    @Override
    public String toString() {
        StringBuilder construtorDeString = new StringBuilder();
        construtorDeString.append(this.nome).append(" ").append(this.matricula).append("\n");
        return construtorDeString.toString();
    }
    
    

    public String getNome() {
        return nome;
    }

    public int getMatricula() {
        return matricula;
    }

    public double getNota1() {
        return nota1;
    }

    public double getNota2() {
        return nota2;
    }

    public double getMediaNaDisciplina() {
        return mediaNaDisciplina;
    }

    public void setNota1(double nota1) {
        this.nota1 = nota1;
    }

    public void setNota2(double nota2) {
        this.nota2 = nota2;
    }
    
    
}
