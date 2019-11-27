/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfacesED.atividadeVetor;

import InterfacesED.Vetor.Vetor;
import java.util.Scanner;

/**
 *
 * @author Matheus Nunes
 */
public class menu {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Vetor<Disciplina> vetorDeDisciplinas = new Vetor<>();
        Scanner scan = new Scanner(System.in);
        int menu = 1;
        Disciplina disciplinaAtualMenu;
        String nomeDisciplinaAtualMenu;
        String nomeAlunoAtualMenu;
        int matriculaAlunoAtualMenu;

        while (menu == 1) {
            System.out.println(
                    "1 – Cadastrar disciplinas\n"
                    + "2 – Cadastrar (matricular) alunos em uma disciplina\n"
                    + "3 – Listar alunos aprovados em uma disciplina\n"
                    + "4 – Listar alunos reprovados em uma disciplina\n"
                    + "5 – Listar alunos com a maior nota em uma disciplina\n"
                    + "6 – Trancar aluno em uma disciplina\n"
                    + "7 - Listar alunos de uma determinada disciplina\n"
                    + "8 - Finaliza o programa\n");
            System.out.println("Selecione uma opção do programa");
            int resposta = scan.nextInt();

            switch (resposta) {
                case (1):
                    System.out.println("Digite o nome disciplina: ");
                    nomeDisciplinaAtualMenu = scan.next();
                    disciplinaAtualMenu = new Disciplina(nomeDisciplinaAtualMenu);
                    if (!vetorDeDisciplinas.contem(disciplinaAtualMenu)) {
                        vetorDeDisciplinas.adicionar(disciplinaAtualMenu);
                    } else {
                        System.out.println("Disciplina ja cadastrada");
                    }
                    break;
                case (2):
                    System.out.println("Digite o nome da disciplina em que o aluno sera matriculado: ");
                    nomeDisciplinaAtualMenu = scan.next();
                    if (vetorDeDisciplinas.contem(new Disciplina(nomeDisciplinaAtualMenu))) {
                        disciplinaAtualMenu = vetorDeDisciplinas.buscar(vetorDeDisciplinas.indexOf(new Disciplina(nomeDisciplinaAtualMenu)));

                        System.out.println("Digite o nome do aluno a ser matriculado: ");
                        nomeAlunoAtualMenu = scan.next();
                        System.out.println("Digite a matricula do aluno a ser matriculado: ");
                        matriculaAlunoAtualMenu = scan.nextInt();
                        Aluno novoAluno = new Aluno(nomeAlunoAtualMenu, matriculaAlunoAtualMenu);
                        if (!disciplinaAtualMenu.getAlunosMatriculados().contem(novoAluno)) {
                            System.out.println("Digite a nota1 do aluno a ser matriculado: ");
                            double nota1NovoAluno = scan.nextDouble();
                            System.out.println("Digite a nota2 do aluno a ser matriculado: ");
                            double nota2NovoAluno = scan.nextDouble();
                            novoAluno.calculaMedia(nota1NovoAluno, nota2NovoAluno);
                            disciplinaAtualMenu.matricularAluno(novoAluno);
                            System.out.println("Aluno matriculado com sucesso!");
                        } else {
                            System.out.println("Aluno ja cadastrado nesta disciplina!");
                        }
                    } else {
                        System.out.println("Disiciplina Inexistente!");
                    }
                    break;
                case (3):
                    System.out.println("Digite o nome disciplina: ");
                    nomeDisciplinaAtualMenu = scan.next();
                    if (vetorDeDisciplinas.contem(new Disciplina(nomeDisciplinaAtualMenu))) {
                        disciplinaAtualMenu = vetorDeDisciplinas.buscar(vetorDeDisciplinas.indexOf(new Disciplina(nomeDisciplinaAtualMenu)));
                        System.out.println(disciplinaAtualMenu.listarAlunosAprovados());
                    } else {
                        System.out.println("Disiciplina Inexistente!");
                    }
                    break;
                case (4):
                    System.out.println("Digite o nome disciplina: ");
                    nomeDisciplinaAtualMenu = scan.next();
                    if (vetorDeDisciplinas.contem(new Disciplina(nomeDisciplinaAtualMenu))) {
                        disciplinaAtualMenu = vetorDeDisciplinas.buscar(vetorDeDisciplinas.indexOf(new Disciplina(nomeDisciplinaAtualMenu)));
                        System.out.println(disciplinaAtualMenu.listarAlunosReprovados());
                    } else {
                        System.out.println("Disiciplina Inexistente!");
                    }
                    break;
                case (5):
                    System.out.println("Digite o nome disciplina: ");
                    nomeDisciplinaAtualMenu = scan.next();
                    if (vetorDeDisciplinas.contem(new Disciplina(nomeDisciplinaAtualMenu))) {
                        disciplinaAtualMenu = vetorDeDisciplinas.buscar(vetorDeDisciplinas.indexOf(new Disciplina(nomeDisciplinaAtualMenu)));
                        System.out.println(disciplinaAtualMenu.listarAlunosComMaiorNota());
                    } else {
                        System.out.println("Disiciplina Inexistente!");
                    }
                    break;
                case (6):
                    System.out.println("Digite o nome disciplina: ");
                    nomeDisciplinaAtualMenu = scan.next();
                    if (vetorDeDisciplinas.contem(new Disciplina(nomeDisciplinaAtualMenu))) {
                        disciplinaAtualMenu = vetorDeDisciplinas.buscar(vetorDeDisciplinas.indexOf(new Disciplina(nomeDisciplinaAtualMenu)));
                        System.out.println("Digite o nome do aluno a ser trancado: ");
                        nomeAlunoAtualMenu = scan.next();
                        System.out.println("Digite a matricula do aluno a ser trancadp: ");
                        matriculaAlunoAtualMenu = scan.nextInt();
                        if (disciplinaAtualMenu.getAlunosMatriculados().contem(new Aluno(nomeAlunoAtualMenu, matriculaAlunoAtualMenu))) {
                            disciplinaAtualMenu.trancarAluno(new Aluno(nomeAlunoAtualMenu, matriculaAlunoAtualMenu));
                            System.out.println("Aluno trancando na disciplina");
                        }else{
                            System.out.println("Aluno nao cadastrado na disciplina");
                        }
                    } else {
                        System.out.println("Disiciplina Inexistente!");
                    }
                    break;
                case (7):
                    System.out.println("Digite o nome disciplina: ");
                    nomeDisciplinaAtualMenu = scan.next();
                    Disciplina disciplinaExistente = new Disciplina(nomeDisciplinaAtualMenu);
                    if (vetorDeDisciplinas.contem(disciplinaExistente)) {
                        Disciplina disciplinaSelecionada = vetorDeDisciplinas.buscar(vetorDeDisciplinas.indexOf(new Disciplina(nomeDisciplinaAtualMenu)));
                        System.out.println(disciplinaSelecionada.imprimeAlunos());
                    }
                    break;
                case (8):
                    menu = 0;
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }

        }

    }

}
