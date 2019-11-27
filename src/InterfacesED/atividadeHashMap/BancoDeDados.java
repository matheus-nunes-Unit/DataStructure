/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfacesED.atividadeHashMap;

/**
 *
 * @author Matheus Nunes
 */
public class BancoDeDados {

    private IHashMap<String, Turma> bancoDeTurmas;
    private IHashMap<String, Aluno> bancoDeAlunos;

    public BancoDeDados() {
        this.bancoDeTurmas = new IHashMap<>();
        this.bancoDeAlunos = new IHashMap<>();
    }

    public String cadastrarAluno(Aluno aluno) {
        //Verificando se ja existe aluno com rg informado
        if (this.bancoDeAlunos.obter(aluno.getRg()) != null) {
            return "Ja existe um aluno com rg informado";
        }
        this.bancoDeAlunos.inserir(aluno.getRg(), aluno);
        return "Aluno cadastrado com sucesso";
    }

    public String cadastrarTurma(Turma turma) {
        //Verificando se ja existe turma com codigo informado
        if (this.bancoDeTurmas.obter(turma.getCodigo()) != null) {
            return "Ja existe uma turma com o codigo informado";
        }
        this.bancoDeTurmas.inserir(turma.getCodigo(), turma);
        return "Turma cadastrada com sucesso";
    }

    public String efetuarMatricula(String codigoDeTurma, String rg) {
        Turma turmaAtual = this.bancoDeTurmas.obter(codigoDeTurma);
        if (turmaAtual != null) {
            Aluno alunoAtual = this.bancoDeAlunos.obter(rg);
            if (alunoAtual != null) {
                this.bancoDeAlunos.remover(rg);
                if (turmaAtual.verificaSeAindaCabe()) {
                    turmaAtual.alunoCadastrar(alunoAtual);
                    return "Aluno cadastrado com sucesso na turma";
                } else {
                    turmaAtual.adicionarNaFila(alunoAtual);
                    return "Aluno foi inserido na fila de espera";
                }
            }
            return "Nao existe aluno cadastrado com rg informado";
        }
        return "Nao existe turma cadastrada com o codigo informado";
    }

    public String cancelarMatricula(String codigoDeTurma, String rg) {
        Turma turmaAtual = this.bancoDeTurmas.obter(codigoDeTurma);
        if (turmaAtual != null) {
            if (turmaAtual.verificaSeAlunoEstaMatriculado(new Aluno("", rg))) {
                return turmaAtual.cancelaMatricula(new Aluno("", rg));
            }
            return "Nao existe aluno matriculado com rg informado";
        }
        return "Nao existe turma com o codigo informado";
    }

    public String cancelarTurma(String codigoDeTurma) {
        Turma turmaAtual = this.bancoDeTurmas.obter(codigoDeTurma);
        if (turmaAtual != null) {
            this.bancoDeTurmas.remover(codigoDeTurma);
        }
        return "Nao existe turma com o codigo informado";
    }

    public String ListarAlunosNaTurma(String codigoDeTurma) {
        Turma turmaAtual = this.bancoDeTurmas.obter(codigoDeTurma);
        if (turmaAtual != null) {
            return turmaAtual.listarAlunos();
        }
        return "Nao existe turma com o codigo informado";
    }

    public String ListarTurmas() {
        StringBuilder cosntrutor = new StringBuilder();
        if (this.bancoDeTurmas.tamanho() == 0) {
            return "Nao ha turma cadastrada";
        }
        int contadorDeTurmas = 1;
        for (NoDupEncHash<String, Turma> object : this.bancoDeTurmas) {
            cosntrutor.append("----------------------------------------- Turma ").append(contadorDeTurmas++).append(" -----------------------------------------").append("\n");
            cosntrutor.append("Codigo da Turma: ").append(object.getElemento().getCodigo()).append("\n");
            cosntrutor.append("Numero de vagas: ").append(object.getElemento().retornaNumeroDeVagas()).append("\n");
            cosntrutor.append("Numero de alunos: ").append(object.getElemento().retornaNumeroDeAlunosCadastrados()).append("\n");
            cosntrutor.append("Tamanho da fila de espera: ").append(object.getElemento().retornaTamanhoDaFilaDeEspera()).append("\n");
        }
        return cosntrutor.toString();
    }

}
