/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfacesED.atividadePilha;

import java.util.Iterator;

/**
 *
 * @author Matheus Nunes
 */
public class Estacionamento {

    private Pilha<Carro> estacionamentoDeCarros;

    public Estacionamento() {
        this.estacionamentoDeCarros = new Vetor<>(10);
    }

    public String estacionarCarro(Carro carro) {
        //Verificando se o carro ja esta no estacionamento
        if (this.retornaPosicaoDoCarro(carro) != -1) {
            return "O estacionamento ja possui um carro com a placa especificada";
        }
        this.estacionamentoDeCarros.push(carro);
        return "Carro estacionado com sucesso.";
    }

    public String sairComOCarro(Carro carro) {
        int posicaoDoCarroNoEstacionamento = this.retornaPosicaoDoCarro(carro);
        if (posicaoDoCarroNoEstacionamento == -1) {
            return "Nao existe carro com a placa especificada";
        }

        //Removendo os carros que estao na frente do carro a ser removido
        Pilha<Carro> pilhaAuxiliar = new Vetor<>();
        int posicaoAtual = this.estacionamentoDeCarros.size() - 1;
        while (posicaoDoCarroNoEstacionamento != posicaoAtual) {
            pilhaAuxiliar.push(this.estacionamentoDeCarros.pop());
            posicaoAtual--;
        }
        Carro carroASerRemovido = this.estacionamentoDeCarros.pop();
        //Adicionando novamente os carros que foram removidos, de volta no estacionamento e acrescentando em um na quantidade de manobras
        int quantidadeDeCarrosMovidos = pilhaAuxiliar.size();
        for (int i = 0; i < quantidadeDeCarrosMovidos; i++) {
            pilhaAuxiliar.top().adicionaNumeroDeManobras();
            this.estacionamentoDeCarros.push(pilhaAuxiliar.pop());
        }

        return "O carro com placa " + carroASerRemovido.getPlaca() + " foi removido do estacionamento. O mesmo realizou um total de "
                + carroASerRemovido.getNumeroDeManobras() + " manobras para que outros carros podessem sair do estacionamento";
    }

    public String listarCarrosDoEstacionamento() {
        if (this.estacionamentoDeCarros.isEmpty()) {
            return "Nao ha carros no estacionamento";
        }
        StringBuilder construtor = new StringBuilder();
        int contador = 1;
        construtor.append("Lista de carros do estacionamento").append("\n");
        for (Carro carroAtual : this.estacionamentoDeCarros) {
            construtor.append(contador).append(" - Placa: ").append(carroAtual).append("\n");
            contador++;
        }

        return construtor.toString();
    }

    public int retornaQuantidadeDeCarrosNoEstacionamento() {
        return this.estacionamentoDeCarros.size();
    }

    private int retornaPosicaoDoCarro(Carro carro) {
        int contador = 0;
        for (Carro carroAtual : this.estacionamentoDeCarros) {
            if (carro.equals(carroAtual)) {
                return contador;
            }
            contador++;
        }
        return -1;
    }

}
