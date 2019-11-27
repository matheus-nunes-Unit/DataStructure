/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfacesED.ListaSimplesmenteLigada;

import InterfacesED.Lista;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *
 * @author 1181123221
 * @param <T>
 */
public class ListaSimplesmenteLigada<T> implements Lista<T> {

    private int tamanho;
    private No<T> primeiro;
    private No<T> ultimo;

    public ListaSimplesmenteLigada() {
        this.tamanho = 0;
        this.primeiro = this.ultimo = null;
    }

    @Override
    public void adicionar(T elemento) {
        this.adicionarNoFim(elemento);
    }

    @Override
    public void adicionarNoInicio(T elemento) {
        No novoNo = new No(elemento);
        if (this.primeiro == null) {
            this.primeiro = novoNo;
            this.ultimo = novoNo;
        } else {
            novoNo.setProximo(this.primeiro);
            this.primeiro = novoNo;
        }
        this.tamanho++;
    }

    @Override
    public void adicionarNoFim(T elemento) {
        No novoNo = new No(elemento);
        if (this.primeiro == null) {
            this.adicionarNoInicio(elemento);
        } else {
            this.ultimo.setProximo(novoNo);
            this.ultimo = novoNo;
            this.tamanho++;
        }
    }

    @Override
    public void adicionar(T elemento, int posicao) {
        if (posicao == 0) {
            this.adicionarNoInicio(elemento);
        } else if (this.tamanho == posicao) {
            this.adicionarNoFim(elemento);
        } else {
            No noAnterior = this.buscarNo(posicao - 1);
            No noPosterior = this.buscarNo(posicao);
            No novoNo = new No(elemento, noPosterior);
            noAnterior.setProximo(novoNo);
            this.tamanho++;
        }
    }

    @Override
    public T remover(int posicao) {
        if (posicao == 0){
            return this.removerNoInicio();
        }else if (posicao == this.tamanho()-1){
            return this.removerNoFim();
        }
        No noAnterior = this.buscarNo(posicao-1);
        No atual = noAnterior.getProximo();
        No noProximo = atual.getProximo();
        noAnterior.setProximo(noProximo);
        atual.setProximo(null);
        this.tamanho--;
        return (T) atual.getElemento();
    }

    @Override
    public T remover(T elemento) {
        No noRemovido = null;
        if (!this.contem(elemento)){
            throw new IllegalArgumentException("Elemento Inexistente");
        }
        int posicao = this.indexOf(elemento);
        return this.remover(posicao);
    }

    @Override
    public T removerNoInicio() {
        No noRemovido;
        switch (this.tamanho()) {
            case 0:
                throw new IllegalArgumentException("Lista Vazia");
            case 1:
                noRemovido = this.primeiro;
                this.primeiro = this.primeiro.getProximo();
                this.ultimo = this.primeiro;
                break;
            default:
                noRemovido = this.primeiro;
                this.primeiro = this.primeiro.getProximo();
                break;
        }
        this.tamanho--;
        return (T) noRemovido.getElemento();
    }

    @Override
    public T removerNoFim() {
        No noRemovido;
        switch (this.tamanho()) {
            case 0:
                throw new IllegalArgumentException("Lista Vazia");
            case 1:
                noRemovido = this.primeiro;
                this.removerNoInicio();
                break;
            default:
                noRemovido = this.ultimo;
                No penultimoNo = this.buscarNo(this.tamanho() - 2);
                penultimoNo.setProximo(null);
                this.ultimo = penultimoNo;
                break;
        }
        this.tamanho--;
        return (T) noRemovido.getElemento();
    }

    @Override
    public int tamanho() {
        return this.tamanho;
    }

    @Override
    public void limpar() {
        this.primeiro = null;
        this.ultimo = null;
        this.tamanho = 0;
    }

    private No buscarNo(int posicao) {
        No noBuscado = null;
        if (this.tamanho == 0) {
            throw new IllegalArgumentException("Lista Vazia");
        } else if (!posicaoOcupada(posicao)) {
            throw new IllegalArgumentException("Posicao Invalida");
        }
        noBuscado = this.primeiro;
        for (int i = 0; i < this.tamanho(); i++) {
            if (i == posicao) {
                break;
            }
            noBuscado = noBuscado.getProximo();
        }
        return noBuscado;
    }

    @Override
    public T buscar(int posicao) {
        return (T) this.buscarNo(posicao).getElemento();
    }

    @Override
    public int indexOf(T elemento) {
        No noAtual = this.primeiro;
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
        No noAtual = this.primeiro;
        for (int i = 0; i < this.tamanho(); i++) {
            if (noAtual.getElemento().equals(elemento)) {
                return true;
            }
            noAtual = noAtual.getProximo();
        }
        return false;
    }

    private Boolean posicaoOcupada(int posicao) {
        if (posicao >= 0 && posicao < this.tamanho()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new ListaSimplesmenteLigadaIterator();
    }

    
    @Override
    public String toString() {

        if (this.tamanho() == 0) {
            return "[]";
        }

        No atual = this.primeiro;

        StringBuilder builder = new StringBuilder("[");

        for (int i = 0; i < this.tamanho(); i++) {
            builder.append(atual.getElemento()).append(",");
            atual = atual.getProximo();
        }

        builder.append("]");
        return builder.toString();
    }

    private class ListaSimplesmenteLigadaIterator implements Iterator<T> {

        private No<T> atual;
        private No<T> ultimoRetornado;

        public ListaSimplesmenteLigadaIterator() {
            this.atual = primeiro;
            this.ultimoRetornado = null;
        }

        @Override
        public boolean hasNext() {
            return this.atual != null;
        }

        @Override
        public T next() {
            if (!this.hasNext()) {
                throw new NoSuchElementException();
            }
            No<T> ultimoRetornadoAtual = this.atual;
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

}
