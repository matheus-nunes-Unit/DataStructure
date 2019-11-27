/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfacesED.atividadeVetor;

import InterfacesED.Vetor.Vetor;

/**
 *
 * @author Matheus Nunes
 */
public class Disciplina {

    private String nome;
    private Vetor<Aluno> alunosMatriculados;

    public Disciplina(String nome) {
        this.nome = nome;
        this.alunosMatriculados = new Vetor<>();
    }

    public void matricularAluno(Aluno novoALuno) {
        this.alunosMatriculados.adicionar(novoALuno);
    }

    public String listarAlunosAprovados() {
        StringBuilder construtorString = new StringBuilder("Alunos aprovados na disciplina Programação:");
        construtorString.append("\nMATRÍCULA NOME NOTA 1 NOTA 2 MÉDIA\n");
        for (int i = 0; i < this.alunosMatriculados.tamanho(); i++) {
            if (this.alunosMatriculados.buscar(i).getMediaNaDisciplina() >= 6.0) {
                String matriculaDoAprovado = Integer.toString(this.alunosMatriculados.buscar(i).getMatricula());
                String nomeDoAprovado = this.alunosMatriculados.buscar(i).getNome();
                String nota1DoAprovado = Double.toString(this.alunosMatriculados.buscar(i).getNota1());
                String nota2DoAprovado = Double.toString(this.alunosMatriculados.buscar(i).getNota2());
                String mediaDoAprovado = Double.toString(this.alunosMatriculados.buscar(i).getMediaNaDisciplina());
                construtorString.append(matriculaDoAprovado).append("         ");
                construtorString.append(nomeDoAprovado).append("  ");
                construtorString.append(nota1DoAprovado).append("  ");
                construtorString.append(nota2DoAprovado).append("  ");
                construtorString.append(mediaDoAprovado).append("\n");;

            }
        }
        return construtorString.toString();
    }

    public String listarAlunosReprovados() {
        StringBuilder construtorString = new StringBuilder("Alunos reprovados na disciplina Programação:");
        construtorString.append("\nMATRÍCULA NOME NOTA 1 NOTA 2 MÉDIA\n");
        for (int i = 0; i < this.alunosMatriculados.tamanho(); i++) {
            if (this.alunosMatriculados.buscar(i).getMediaNaDisciplina() < 6.0) {
                String matriculaDoReprovado = Integer.toString(this.alunosMatriculados.buscar(i).getMatricula());
                String nomeDoReprovado = this.alunosMatriculados.buscar(i).getNome();
                String nota1DoReprovado = Double.toString(this.alunosMatriculados.buscar(i).getNota1());
                String nota2DoReprovado = Double.toString(this.alunosMatriculados.buscar(i).getNota2());
                String mediaDoReprovado = Double.toString(this.alunosMatriculados.buscar(i).getMediaNaDisciplina());
                construtorString.append(matriculaDoReprovado).append("         ");
                construtorString.append(nomeDoReprovado).append("  ");
                construtorString.append(nota1DoReprovado).append("  ");
                construtorString.append(nota2DoReprovado).append("  ");
                construtorString.append(mediaDoReprovado).append("\n");;
            }
        }
        return construtorString.toString();
    }

    public String listarAlunosComMaiorNota() {
        //maiorNota = Armazena a maior nota atual
        //vetorDeMaiorNota = Armazena os alunos que possuem a maior nota
        //O vetor ira iniciar com o primeiro aluno sendo o que possui a maior nota, e posteriormente vai comparando com os outros alunos
        double maiorNotaAtual = this.alunosMatriculados.buscar(0).getMediaNaDisciplina();
        Vetor<Aluno> vetorDeMaiorNota = new Vetor<>();
        vetorDeMaiorNota.adicionar(this.alunosMatriculados.buscar(0));

        for (int i = 1; i < this.alunosMatriculados.tamanho(); i++) {
            Aluno alunoAtual = this.alunosMatriculados.buscar(i);
            /*
            Caso for encontrada uma nota que seja maior que a atual, o vetor sera limpo, 
            e serao somente adicionados os alunos que agora possuirem essa nova maior nota.
             */
            if (maiorNotaAtual < alunoAtual.getMediaNaDisciplina()) {
                vetorDeMaiorNota.limpar();
                vetorDeMaiorNota.adicionar(alunoAtual);
                maiorNotaAtual = alunoAtual.getMediaNaDisciplina();
                //Verificando se foi encontrado um outro aluno que tambem possui a maior nota atual
            } else if (maiorNotaAtual == alunoAtual.getMediaNaDisciplina()) {
                vetorDeMaiorNota.adicionar(alunoAtual);
            }
        }
        StringBuilder construtorString = new StringBuilder("Alunos destaque na disciplina Programação:");
        construtorString.append("\nMATRÍCULA NOME NOTA 1 NOTA 2 MÉDIA\n");
        //Adicionando as informacoes dos alunos com melhor nota no StringBuilder
        for (int i = 0; i < vetorDeMaiorNota.tamanho(); i++) {
            String matriculaDaMelhorMedia = Integer.toString(vetorDeMaiorNota.buscar(i).getMatricula());
            String nomeDaMelhorMedia = vetorDeMaiorNota.buscar(i).getNome();
            String nota1DaMelhorMedia = Double.toString(vetorDeMaiorNota.buscar(i).getNota1());
            String nota2DaMelhorMedia = Double.toString(vetorDeMaiorNota.buscar(i).getNota2());
            String melhorMedia = Double.toString(vetorDeMaiorNota.buscar(i).getMediaNaDisciplina());
            construtorString.append(matriculaDaMelhorMedia).append("         ");
            construtorString.append(nomeDaMelhorMedia).append("  ");
            construtorString.append(nota1DaMelhorMedia).append("  ");
            construtorString.append(nota2DaMelhorMedia).append("  ");
            construtorString.append(melhorMedia).append("\n");;
        }

        return construtorString.toString();
    }

    public void trancarAluno(Aluno alunoTrancado) {
        if (this.alunosMatriculados.contem(alunoTrancado)) {
            this.alunosMatriculados.remover(alunoTrancado);
        }
    }

    public String imprimeAlunos() {
        StringBuilder construtorDeString = new StringBuilder("NOME   |   MATRICULA\n");
        for (int i = 0; i < this.alunosMatriculados.tamanho(); i++) {
            construtorDeString.append(this.alunosMatriculados.buscar(i)).append("\n");
        }
        return construtorDeString.toString();
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        Disciplina disciplina = (Disciplina) obj;

        return (disciplina.nome.equals(this.nome));
    }

    public Vetor<Aluno> getAlunosMatriculados() {
        return alunosMatriculados;
    }

}
