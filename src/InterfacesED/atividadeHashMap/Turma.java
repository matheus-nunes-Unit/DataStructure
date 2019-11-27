/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfacesED.atividadeHashMap;

import java.util.Objects;

/**
 *
 * @author Matheus Nunes
 */
public class Turma {

    private final int numeroMaximoAlunos;
    private String codigo;
    private ListaDuplamenteLigada<Aluno> alunosMatriculados;
    private Fila<Aluno> filaDeEsperar;

    public Turma(String codigo, int numeroMaximoDeAlunos) {
        this.codigo = codigo;
        this.numeroMaximoAlunos = numeroMaximoDeAlunos;
        this.alunosMatriculados = new ListaDuplamenteLigada<>();
        this.filaDeEsperar = new ListaDuplamenteLigada<>();
    }

    public String getCodigo() {
        return this.codigo;
    }

    //Metodo para matricular aluno na turma
    public void alunoCadastrar(Aluno aluno) {
        this.alunosMatriculados.adicionar(aluno);
    }

    //Metodo para inserir aluno na fila de espera caso a quantidade de matriculados seja a maxima
    public void adicionarNaFila(Aluno aluno) {
        this.filaDeEsperar.enfileirar(aluno);
    }

    //Metodo para verificar se ainda cabem alunos na turma
    public boolean verificaSeAindaCabe() {
        return this.numeroMaximoAlunos != this.alunosMatriculados.tamanho();
    }

    //Metodo para verificar se determinado aluno esta matriculado na turma
    public boolean verificaSeAlunoEstaMatriculado(Aluno aluno) {
        return this.alunosMatriculados.contem(aluno);
    }

    //Metodo para cancelar a matricula de um determinado aluno
    public String cancelaMatricula(Aluno aluno) {
        Aluno alunoRemovido = this.alunosMatriculados.remover(aluno);
        if (!filaDeEsperar.vazia()) {
            Aluno alunoNovo = this.filaDeEsperar.desenfileirar();
            this.alunoCadastrar(alunoNovo);
            return "A matricula do aluno " + alunoRemovido.getNome() + " foi cancelada e o aluno " + alunoNovo.getNome() + "saiu da fila de espera e foi matriculado";
        }
        return "A matricula do aluno " + alunoRemovido.getNome() + " foi cancelada";
    }

    //Metodo para listar os alunos da turma
    public String listarAlunos() {
        StringBuilder construtor = new StringBuilder("Alunos cadastrados na turma\n");
        if (this.alunosMatriculados.tamanho() == 0) {
            return "Nao ha alunos cadastrados na turma";
        }
        for (Aluno alunosMatriculado : this.alunosMatriculados) {
            construtor.append(alunosMatriculado).append(" | ");
        }
        return construtor.toString();
    }
    
    //Retorna numero de vagas
    public int retornaNumeroDeVagas(){
        return this.numeroMaximoAlunos;
    }
    
    //Retorna numero de alunos existentes
    public int retornaNumeroDeAlunosCadastrados(){
        return this.alunosMatriculados.tamanho();
    }
    
    //Retorna o tamanho da fila de espera
    public int retornaTamanhoDaFilaDeEspera(){
        return this.filaDeEsperar.tamanho();
    }
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.codigo);
        return hash;
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
        final Turma other = (Turma) obj;
        return Objects.equals(this.codigo, other.codigo);
    }

}
