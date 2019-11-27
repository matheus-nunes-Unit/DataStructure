/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfacesED.Vetor;

import InterfacesED.Lista;
import InterfacesED.Fila;
import InterfacesED.Pilha;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *
 * @author 1181123221
 */
public class Vetor<T> implements Lista<T>, Fila<T>, Pilha<T> {

    //Tamanho atual do vetor, sendo iniciando com 10 casas caso nao especificado
    private int tamVetor = 10;
    //Quantidade de elementos no vetor
    private int quantidadeDeElementos = 0;
    private T[] vetor;

    public Vetor() {
        this.vetor = (T[]) new Object[this.tamVetor];
    }

    public Vetor(int tamanhoDoVetor) {
        this.vetor = (T[]) new Object[tamanhoDoVetor];
        this.tamVetor = tamanhoDoVetor;
    }

    public boolean validaPosicao(int posicao) {
        return posicao >= 0 && posicao <= this.tamanho();
    }

    public boolean verificaPosicaoPreenchida(int posicao) {
        return posicao >= 0 && posicao < this.tamanho();
    }

    //Transfere os valores do vetor antigo para o novo caso o mesmo ja esteja cheio
    private void garanteEspaco() {
        if (this.tamanho() == this.tamVetor) {
            int tamanhoDoNovoVetor;
            if (this.tamVetor == 0) {
                tamanhoDoNovoVetor = 2;
            } else {
                tamanhoDoNovoVetor = (this.tamVetor / 2) + (this.tamVetor);
            }
            T[] novoVetor = (T[]) new Object[tamanhoDoNovoVetor];
            this.tamVetor = novoVetor.length;
            for (int i = 0; i < this.vetor.length; i++) {
                novoVetor[i] = this.vetor[i];
            }
            this.vetor = novoVetor;
        }
    }

    @Override
    public void adicionar(T elemento) {
        this.garanteEspaco();
        this.vetor[this.tamanho()] = elemento;
        this.quantidadeDeElementos++;
    }

    @Override
    public void adicionarNoInicio(T elemento) {
        this.garanteEspaco();
        for (int i = this.tamanho() - 1; i >= 0; i--) {
            this.vetor[i + 1] = this.vetor[i];
        }
        this.vetor[0] = elemento;
        this.quantidadeDeElementos++;
    }

    @Override
    public void adicionarNoFim(T elemento) {
        this.adicionar(elemento);
    }

    @Override
    public void adicionar(T elemento, int posicao) {
        if (!this.validaPosicao(posicao)) {
            throw new IllegalArgumentException("Posicao Invalida!");
        }
        this.garanteEspaco();
        for (int i = this.tamanho() - 1; i >= posicao; i--) {
            this.vetor[i + 1] = this.vetor[i];
        }
        this.vetor[posicao] = elemento;
        this.quantidadeDeElementos++;
    }

    @Override
    public T remover(int posicao) {
        if (!this.verificaPosicaoPreenchida(posicao)) {
            throw new IllegalArgumentException("Posicao Invalida!");
        }
        T valorRemovido = this.vetor[posicao];
        for (int i = posicao; i < this.tamanho() - 1; i++) {
            this.vetor[i] = this.vetor[i + 1];
        }
        this.vetor[this.tamanho() - 1] = null;
        this.quantidadeDeElementos--;
        return valorRemovido;
    }

    @Override
    public T remover(T elemento) {
        if (!this.contem(elemento)) {
            return null;
        }
        int posicaDoElemento = this.indexOf(elemento);
        return this.remover(posicaDoElemento);
    }

    @Override
    public T removerNoInicio() {
        return remover(0);
    }

    @Override
    public T removerNoFim() {
        return remover(this.tamanho() - 1);
    }

    //Retorna a quantidade de elementos
    @Override
    public int tamanho() {
        return this.quantidadeDeElementos;
    }

    @Override
    public void limpar() {
        for (int i = 0; i < this.tamanho(); i++) {
            this.vetor[i] = null;
        }
        this.quantidadeDeElementos = 0;
    }

    @Override
    public T buscar(int posicao) {
        if (!this.verificaPosicaoPreenchida(posicao)) {
            throw new IllegalArgumentException("Posicao Invalida!");
        }
        return this.vetor[posicao];
    }

    @Override
    public int indexOf(T elemento) {
        for (int i = 0; i < this.tamanho(); i++) {
            if (this.vetor[i].equals(elemento)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean contem(T elemento) {
        for (int i = 0; i < this.tamanho(); i++) {
            if (this.vetor[i].equals(elemento)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return new VetorIterator();
    }

    @Override
    public String toString() {

        StringBuilder construtorString = new StringBuilder("[");
        int posicao = this.inicio;
        for (int i = 0; i < this.tamanho(); i++) {
            if (i == this.tamanho() - 1) {
                construtorString.append(this.vetor[posicao]);
                break;
            }
            construtorString.append(this.vetor[posicao]).append(",");
            //if necessario para caso estejamos utilizando a implementação de fila com vetor
            if (posicao == this.vetor.length - 1) {
                posicao = 0;
            } else {
                posicao++;
            }
        }
        construtorString.append("]");
        return construtorString.toString();
    }

    private class VetorIterator implements Iterator<T> {

        private int quantidadeTotal;
        private int elementosPercorridos;
        private int cursor;
        private int ultRetornado;

        public VetorIterator() {
            this.elementosPercorridos = 0;
            this.cursor = inicio;
            this.ultRetornado = -1;
            this.quantidadeTotal = tamanho();
        }

        @Override
        public boolean hasNext() {
        return this.elementosPercorridos != this.quantidadeTotal;
        }

        @Override
        public T next() {
            if (this.elementosPercorridos == this.quantidadeTotal) {
                throw new NoSuchElementException();
            }
            this.ultRetornado = cursor;
            //if necessario para caso estejamos utilizando a implementação de fila com vetor
            if (this.cursor == tamVetor - 1) {
                this.cursor = 0;
            } else {
                this.cursor++;
            }
            this.elementosPercorridos++;
            return vetor[this.ultRetornado];
        }

        @Override
        public void remove() {
            //checando se o next nao foi chamado
            if (this.ultRetornado < 0) {
                throw new IllegalStateException();
            }
            try {
                remover(this.ultRetornado);
                this.cursor = this.ultRetornado;
                //O metodo remove so pode ser chamda uma vez por metodo next chamado
                this.ultRetornado = -1;
            } catch (IllegalArgumentException ex) {
                this.elementosPercorridos = this.quantidadeTotal;
            }
        }
    }

    //Metodos de Fila----------------------------------------------------------------------------------------------------------------------
    /*
    O atributo inicio sera usado com exclusividade para a implementação de Fila utilizando vetor sendo que inicialmente 
    o elemento de inicio estara na posicao 0 e o elemento final tambem estara na posicao 0
     */
    private int inicio = 0;
    private int fim = 0;

    private void garanteEspacoFila() {
        if (this.quantidadeDeElementos == this.tamVetor) {
            int tamanhoDoNovoVetor;
            if (this.tamVetor == 0) {
                tamanhoDoNovoVetor = 2;
            } else {
                tamanhoDoNovoVetor = (this.tamVetor / 2) + (this.tamVetor);
            }
            T[] novoVetor = (T[]) new Object[tamanhoDoNovoVetor];
            this.tamVetor = novoVetor.length;
            int posicao = this.inicio;
            for (int i = 0; i < this.vetor.length; i++) {
                novoVetor[i] = this.vetor[posicao];
                if (posicao == this.vetor.length - 1) {
                    posicao = 0;
                } else {
                    posicao++;
                }
            }
            this.vetor = novoVetor;
            this.inicio = 0;
            this.fim = this.quantidadeDeElementos;
        }
    }

    @Override
    public boolean enfileirar(T valor) {
        this.garanteEspacoFila();
        this.vetor[this.fim] = valor;
        if (this.fim == this.tamVetor) {
            this.fim = 0;
        } else {
            this.fim++;
        }
        this.quantidadeDeElementos++;
        return true;
    }

    @Override
    public T desenfileirar() {
        if (this.vazia()) {
            throw new NullPointerException("Sem elemento a ser removido do vetor");
        }
        T elementoRemovido = this.vetor[this.inicio];
        this.vetor[this.inicio] = null;
        if (this.inicio == this.tamVetor) {
            this.inicio = 0;
        } else {
            this.inicio++;
        }
        this.quantidadeDeElementos--;
        return elementoRemovido;
    }

    @Override
    public boolean vazia() {
        return this.tamanho() == 0;
    }

    @Override
    public T primeiro() {
        return this.vetor[this.inicio];
    }

    @Override
    public T ultimo() {
        return this.vetor[this.fim];
    }
    //---------------------------------------------------------------------------------------------------------------------------

    //Metodos de Pilha----------------------------------------------------------------------------------------------------------------------
    private int topo = -1;

    @Override
    public void push(T elemento) {
        this.garanteEspaco();
        this.vetor[++this.topo] = elemento;
        this.quantidadeDeElementos++;
    }

    @Override
    public T pop() {
        if (this.isEmpty()) {
            throw new IllegalArgumentException("Pilha vazia!");
        }
        T itemRemovido = this.vetor[this.topo];
        this.vetor[this.topo--] = null;
        this.quantidadeDeElementos--;
        return itemRemovido;
    }

    @Override
    public int size() {
        return this.topo + 1;
    }

    @Override
    public boolean isEmpty() {
        return this.topo == -1;
    }

    @Override
    public T top() {
        if (this.isEmpty()) {
            return null;
        }
        return this.buscar(this.topo);
    }

}
