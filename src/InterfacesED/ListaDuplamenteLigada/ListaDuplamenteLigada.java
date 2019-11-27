/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfacesED.ListaDuplamenteLigada;

import InterfacesED.Lista;
import InterfacesED.Fila;
import InterfacesED.Pilha;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *
 * @author Matheus Nunes
 */
public class ListaDuplamenteLigada<T> implements Lista<T>, Fila<T>, Pilha<T> {

    private NoDupEnc<T> primeiro;
    private NoDupEnc<T> ultimo;
    private int tamanho;

    public ListaDuplamenteLigada() {
        //Criando os nos cabeça e calda
        this.primeiro = new NoDupEnc<>(null);
        this.ultimo = new NoDupEnc<>(null);
        this.primeiro.setProximo(this.ultimo);
        this.ultimo.setAnterior(this.primeiro);
        this.tamanho = 0;
    }

    @Override
    public void adicionar(T elemento) {
        NoDupEnc<T> novoNo = new NoDupEnc(elemento);
        novoNo.setProximo(this.ultimo);
        novoNo.setAnterior(this.ultimo.getAnterior());
        this.ultimo.getAnterior().setProximo(novoNo);
        this.ultimo.setAnterior(novoNo);
        this.tamanho++;
    }

    @Override
    public void adicionarNoInicio(T elemento) {
        NoDupEnc<T> novoNo = new NoDupEnc(elemento);
        novoNo.setProximo(this.primeiro.getProximo());
        novoNo.setAnterior(this.primeiro);
        this.primeiro.getProximo().setAnterior(novoNo);
        this.primeiro.setProximo(novoNo);
        this.tamanho++;
    }

    @Override
    public void adicionarNoFim(T elemento) {
        this.adicionar(elemento);
    }

    @Override
    public void adicionar(T elemento, int posicao) {
        if (posicao == 0) {
            this.adicionarNoInicio(elemento);
        } else if (posicao == this.tamanho()) {
            this.adicionarNoFim(elemento);
        } else {
            NoDupEnc<T> novoNo = new NoDupEnc(elemento);
            NoDupEnc<T> noPosicaoAtual = this.buscarNo(posicao);
            novoNo.setProximo(noPosicaoAtual);
            novoNo.setAnterior(noPosicaoAtual.getAnterior());
            noPosicaoAtual.getAnterior().setProximo(novoNo);
            noPosicaoAtual.setAnterior(novoNo);
            this.tamanho++;
        }
    }

    @Override
    public T remover(int posicao) {
        if (posicao == 0) {
            return this.removerNoInicio();
        } else if (posicao == this.tamanho()) {
            return this.removerNoFim();
        }
        NoDupEnc<T> noPosicaoAtual = this.buscarNo(posicao);
        noPosicaoAtual.getAnterior().setProximo(noPosicaoAtual.getProximo());
        noPosicaoAtual.getProximo().setAnterior(noPosicaoAtual.getAnterior());
        this.tamanho--;
        return noPosicaoAtual.getElemento();
    }

    @Override
    public T remover(T elemento) {
        if (this.contem(elemento)) {
            NoDupEnc noASerRemovido = this.buscarNo(elemento);
            noASerRemovido.getAnterior().setProximo(noASerRemovido.getProximo());
            noASerRemovido.getProximo().setAnterior(noASerRemovido.getAnterior());
            this.tamanho--;
            return (T) noASerRemovido.getElemento();
        }
        throw new IllegalArgumentException("Elemento nao existente");
    }

    @Override
    public T removerNoInicio() {
        if (this.tamanho() == 0) {
            throw new IllegalArgumentException("Vetor Vazio");
        }
        NoDupEnc noASerRemovido = this.primeiro.getProximo();
        noASerRemovido.getProximo().setAnterior(this.primeiro);
        this.primeiro.setProximo(noASerRemovido.getProximo());
        this.tamanho--;
        return (T) noASerRemovido.getElemento();
    }

    @Override
    public T removerNoFim() {
        if (this.tamanho() == 0) {
            throw new IllegalArgumentException("Vetor Vazio");
        }
        NoDupEnc noASerRemovido = this.ultimo.getAnterior();
        noASerRemovido.getAnterior().setProximo(this.ultimo);
        this.tamanho--;
        return (T) noASerRemovido.getElemento();
    }

    @Override
    public int tamanho() {
        return this.tamanho;
    }

    @Override
    public void limpar() {
        this.primeiro.setProximo(this.ultimo);
        this.ultimo.setAnterior(this.primeiro);
        this.tamanho = 0;
    }

    private NoDupEnc buscarNo(int posicao) {
        if (this.tamanho >= posicao && posicao >= 0) {
            NoDupEnc<T> noPosicaoAtual;
            int posicaoInicial;
            if (posicao > (this.tamanho() / 2)) {
                posicaoInicial = this.tamanho() - 1;
                noPosicaoAtual = this.ultimo.getAnterior();
                for (int i = posicaoInicial; i > posicao; i--) {
                    noPosicaoAtual = noPosicaoAtual.getAnterior();
                }
            } else {
                posicaoInicial = 0;
                noPosicaoAtual = this.primeiro.getProximo();
                for (int i = posicaoInicial; i < posicao; i++) {
                    noPosicaoAtual = noPosicaoAtual.getProximo();
                }
            }
            return noPosicaoAtual;
        } else {
            throw new IllegalArgumentException("Posição Invalida");
        }
    }

    private NoDupEnc buscarNo(T elemento) {
        NoDupEnc noAtual = this.primeiro.getProximo();
        for (int i = 0; i < this.tamanho(); i++) {
            if (noAtual.getElemento().equals(elemento)) {
                break;
            }
            noAtual = noAtual.getProximo();
        }
        return noAtual;
    }

    @Override
    public T buscar(int posicao) {
        return (T) this.buscarNo(posicao).getElemento();
    }

    @Override
    public int indexOf(T elemento) {
        NoDupEnc noAtual = this.primeiro.getProximo();
        for (int i = 0; i < this.tamanho(); i++) {
            if (noAtual.getElemento().equals(elemento)) {
                return i;
            }
            noAtual = noAtual.getProximo();
        }
        return -1;
    }

    @Override
    public boolean contem(T elemento) {
        NoDupEnc noAtual = this.primeiro.getProximo();
        for (int i = 0; i < this.tamanho(); i++) {
            if (noAtual.getElemento().equals(elemento)) {
                return true;
            }
            noAtual = noAtual.getProximo();
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return new ListaDuplamenteLigadaIterator();
    }

    public Iterator<T> descendingIterator() {
        return new ListaDuplamenteLigadaReverseIterator();
    }

    @Override
    public String toString() {

        if (this.tamanho() == 0) {
            return "[]";
        }

        //Pegando o elemento apos a cauda
        NoDupEnc atual = this.primeiro.getProximo();

        StringBuilder builder = new StringBuilder("[");

        for (int i = 0; i < this.tamanho(); i++) {
            if (i == this.tamanho() - 1){
                builder.append(atual.getElemento());
                break;
            }
            builder.append(atual.getElemento()).append(",");
            atual = atual.getProximo();
        }

        builder.append("]");
        return builder.toString();
    }

    //Metodos de Fila----------------------------------------------------------------------------------------------------------------------
    @Override
    public boolean enfileirar(T elemento) {
        this.adicionarNoFim(elemento);
        return true;
    }

    @Override
    public T desenfileirar() {
        return this.removerNoInicio();
    }

    @Override
    public boolean vazia() {
        return this.tamanho() == 0;
    }

    @Override
    public T primeiro() {
        return this.primeiro.getElemento();
    }

    @Override
    public T ultimo() {
        return this.ultimo.getElemento();
    }

    //--------------------------------------------------------------------------------------------------------------------------------------
    private class ListaDuplamenteLigadaIterator implements Iterator<T> {

        private NoDupEnc<T> atual;
        private NoDupEnc<T> ultimoRetornado;

        public ListaDuplamenteLigadaIterator() {
            this.atual = primeiro.getProximo();
            this.ultimoRetornado = null;
        }

        @Override
        public boolean hasNext() {
            return this.atual.getProximo() != null;
        }

        @Override
        public T next() {
            if (!this.hasNext()) {
                throw new NoSuchElementException();
            }
            NoDupEnc<T> ultimoRetornadoAtual = this.atual;
            this.atual = this.atual.getProximo();
            this.ultimoRetornado = ultimoRetornadoAtual;
            return ultimoRetornadoAtual.getElemento();
        }

        @Override
        public void remove() {
            //checando se o next nao foi chamado
            if (this.ultimoRetornado == null) {
                throw new IllegalStateException();
            }
            remover(this.ultimoRetornado.getElemento());
            //O metodo remove so pode ser chamado uma vez por metodo next chamado
            this.ultimoRetornado = null;
        }
    }

    private class ListaDuplamenteLigadaReverseIterator implements Iterator<T> {

        private NoDupEnc<T> atual;
        private NoDupEnc<T> ultimoRetornado;

        public ListaDuplamenteLigadaReverseIterator() {
            this.atual = ultimo.getAnterior();
            this.ultimoRetornado = null;
        }

        @Override
        public boolean hasNext() {
            return this.atual.getAnterior() != null;
        }

        @Override
        public T next() {
            if (!this.hasNext()) {
                throw new NoSuchElementException();
            }
            NoDupEnc<T> ultimoRetornadoAtual = this.atual;
            this.atual = this.atual.getAnterior();
            this.ultimoRetornado = ultimoRetornadoAtual;
            return ultimoRetornadoAtual.getElemento();
        }

        @Override
        public void remove() {
            //checando se o next nao foi chamado
            if (this.ultimoRetornado == null) {
                throw new IllegalStateException();
            }
            remover(this.ultimoRetornado.getElemento());
            //O metodo remove so pode ser chamado uma vez por metodo next chamado
            this.ultimoRetornado = null;
        }
    }

    //Metodos de Pilha----------------------------------------------------------------------------------------------------------------------
    @Override
    public void push(T elemento) {
        this.adicionarNoFim(elemento);
    }

    @Override
    public T pop() {
        return this.removerNoFim();
    }

    @Override
    public int size() {
        return this.tamanho();
    }

    @Override
    public boolean isEmpty() {
        return this.tamanho() == 0;
    }

    @Override
    public T top() {
        return this.buscar(this.tamanho-1); 
    }
    
    //-----------------------------------------------------------------------------------------------------------------------------------------------
}
