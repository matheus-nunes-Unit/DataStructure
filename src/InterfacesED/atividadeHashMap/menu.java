/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfacesED.atividadeHashMap;

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
        Scanner scan = new Scanner(System.in);
        BancoDeDados bancoDeDados = new BancoDeDados();
        int menu = 1;
        while (menu == 1) {
            System.out.println(
                    "1 – Cadastrar Turma\n"
                    + "2 – Cadastrar Aluno\n"
                    + "3 – Efetuar matricula\n"
                    + "4 – Cancelar matricula\n"
                    + "5 – Listar Alunos Na Turma\n"
                    + "6 - Listar Turmas\n");
            System.out.println("Selecione uma opção do programa(para finalizar digite qualquer numero que nao esteja incluido no menu): ");
            int resposta = scan.nextInt();
            scan.nextLine();
            switch (resposta) {
                case (1):
                    System.out.println("Digite o numero maximo de vagas que teraa turma: ");
                    int novaTurmaNumeroMaximo = scan.nextInt();
                    scan.nextLine();
                    System.out.println("Digite o codigo da turma que sera cadastrada: ");
                    String codigoNovaTurma = scan.nextLine();
                    System.out.println(bancoDeDados.cadastrarTurma(new Turma(codigoNovaTurma, novaTurmaNumeroMaximo)));
                    break;
                case (2):
                    System.out.println("Digite o nome do aluno que sera cadastrado: ");
                    String nomeNovoAluno = scan.nextLine();
                    System.out.println("Digite o rg do novo aluno que sera cadastrado: ");
                    String rgNovoAluno = scan.nextLine();
                    System.out.println(bancoDeDados.cadastrarAluno(new Aluno(nomeNovoAluno, rgNovoAluno)));
                    break;
                case (3):
                    System.out.println("Digite o rg do aluno que sera realizada a matricula: ");
                    String rgAlunoMatricula = scan.nextLine();
                    System.out.println("Digite o codigo da turma em que sera matriculado o aluno: ");
                    String codigoTurmaMatricula = scan.nextLine();
                    System.out.println(bancoDeDados.efetuarMatricula(codigoTurmaMatricula, rgAlunoMatricula));
                    break;
                case (4):
                    System.out.println("Digite o rg do aluno que sera cancelada a matricula: ");
                    String rgAlunoCancelarMatricula = scan.nextLine();
                    System.out.println("Digite o codigo da turma em que sera cancelada a matricula do aluno: ");
                    String codigoTurmaCancelarMatricula = scan.nextLine();
                    System.out.println(bancoDeDados.cancelarMatricula(codigoTurmaCancelarMatricula, rgAlunoCancelarMatricula));
                    break;
                case (5):
                    System.out.println("Digite o codigo da turma em que sera mostrada a lista de alunos: ");
                    String codigoTurmaConsultaAlunos = scan.nextLine();
                    System.out.println(bancoDeDados.ListarAlunosNaTurma(codigoTurmaConsultaAlunos));
                    break;
                case (6):
                    System.out.println(bancoDeDados.ListarTurmas());
                    break;
                default:
                    menu = 0;
                    break;
            }
        }
    }

}
