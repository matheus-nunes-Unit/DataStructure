/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfacesED.HashMap;

import InterfacesED.IHash;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *
 * @author Matheus Nunes
 */
public class IHashMap<K, V> implements IHash<K, V> {

    private int quantidadeDeElementos;
    private ListaDuplamenteLigada<NoDupEncHash<K, V>>[] mapa;

    public IHashMap() {
        this.quantidadeDeElementos = 0;
        this.mapa = new ListaDuplamenteLigada[4];
        this.iniciaHash();
    }

    private void iniciaHash() {
        for (int i = 0; i < this.mapa.length; i++) {
            this.mapa[i] = new ListaDuplamenteLigada<NoDupEncHash<K, V>>();
        }
    }

    private int hash(int hashCode) {
            return Math.abs(hashCode % this.mapa.length);
    }

    private void garanteEspaco() {
        if ((this.quantidadeDeElementos / this.mapa.length) >= 0.75) {
            int tamanhoAntigo = this.mapa.length;
            ListaDuplamenteLigada<NoDupEncHash<K, V>>[] antigoMapa = this.mapa;
            this.mapa = new ListaDuplamenteLigada[tamanhoAntigo * 2];
            iniciaHash();
            for (int i = 0; i < tamanhoAntigo; i++) {
                ListaDuplamenteLigada<NoDupEncHash<K, V>> posicaoTabelaHash = antigoMapa[i];
                for (NoDupEncHash<K, V> noDupEncHash : posicaoTabelaHash) {
                    int hash = this.hash(noDupEncHash.getKey().hashCode());
                    this.mapa[hash].adicionar(noDupEncHash);
                }
            }
            antigoMapa = null;
        }
    }

    @Override
    public void inserir(K chave, V valor) {
        this.garanteEspaco();
        boolean inserido = false;
        NoDupEncHash<K, V> novoNo = new NoDupEncHash<>(chave, valor);
        int posicao = this.hash(chave.hashCode());
        ListaDuplamenteLigada<NoDupEncHash<K, V>> posicaoTabelaHash = this.mapa[posicao];
        for (NoDupEncHash<K, V> noDupEncHash : posicaoTabelaHash) {
            if (noDupEncHash.getKey().equals(novoNo.getKey())) {
                noDupEncHash.setElemento(novoNo.getElemento());
                inserido = true;
                break;
            }
        }

        if (inserido == false) {
            posicaoTabelaHash.adicionar(novoNo);
            this.quantidadeDeElementos++;
        }
    }

    @Override
    public V obter(K chave) {
        int hash = this.hash(chave.hashCode());
        ListaDuplamenteLigada<NoDupEncHash<K, V>> posicaoTabelaHash = this.mapa[hash];
        for (NoDupEncHash<K, V> noDupEncHash : posicaoTabelaHash) {
            if (noDupEncHash.getKey().equals(chave)) {
                return noDupEncHash.getElemento();
            }
        }
        return null;
    }

    @Override
    public V remover(K chave) {
        if (this.quantidadeDeElementos == 0) {
            throw new IllegalStateException("Nao existe elementos dentro da tabela hash");
        }
        int hash = this.hash(chave.hashCode());
        ListaDuplamenteLigada<NoDupEncHash<K, V>> posicaoTabelaHash = this.mapa[hash];
        for (NoDupEncHash<K, V> noDupEncHash : posicaoTabelaHash) {
            if (noDupEncHash.getKey().equals(chave)) {
                posicaoTabelaHash.remover(noDupEncHash);
                this.quantidadeDeElementos--;
                return noDupEncHash.getElemento();
            }
        }
        return null;
    }

    @Override
    public boolean existe(K chave) {
        int hash = this.hash(chave.hashCode());
        ListaDuplamenteLigada<NoDupEncHash<K, V>> posicaoTabelaHash = this.mapa[hash];
        for (NoDupEncHash<K, V> noDupEncHash : posicaoTabelaHash) {
            if (noDupEncHash.getKey().equals(chave)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean contem(V valor) {
        for (int i = 0; i < this.mapa.length; i++) {
            ListaDuplamenteLigada<NoDupEncHash<K, V>> posicaoTabelaHash = this.mapa[i];
            for (NoDupEncHash<K, V> noAtual : posicaoTabelaHash) {
                if (noAtual.getElemento().equals(valor)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public int tamanho() {
        return this.quantidadeDeElementos;
    }

    @Override
    public void limpar() {
        this.iniciaHash();
        this.quantidadeDeElementos = 0;
    }

    @Override
    public String toString() {
        StringBuilder construtor = new StringBuilder("{");
        Iterator iterator = this.iterator();
        int contador = 1;
        while (iterator.hasNext()) {
            if (contador == this.quantidadeDeElementos) {
                construtor.append(iterator.next());
                break;
            }
            construtor.append(iterator.next()).append(" , ");
            contador++;
        }
        return construtor.append("}").toString();
    }

    @Override
    public Iterator iterator() {
        return new HashMapIterator();
    }

    private class HashMapIterator implements Iterator {

        private int linhaAtual;
        private Iterator iteratorDaLinkedList;
        private int quantidadeTotal;
        private int elementosPercorridos;
        private NoDupEncHash<K, V> cursor;
        private NoDupEncHash<K, V> ultRetornado;

        public HashMapIterator() {
            this.elementosPercorridos = 0;
            this.quantidadeTotal = tamanho();
            //Verificando se a quantidade de elementos e igual a 0, pois caso seja, nao sera permitido que seja chamado o next no iterator
            if (quantidadeTotal != 0) {
                this.linhaAtual = 0;
                this.iteratorDaLinkedList = mapa[linhaAtual++].iterator();
                //Enquanto for retornado um iterator que nao possui proximo
                while (!this.iteratorDaLinkedList.hasNext()) {
                    //Caso o iterator seja chamado quando o hash nao possuir elementos comece sem nenhum elemento
                    if (linhaAtual == mapa.length) {
                        break;
                    }
                    this.iteratorDaLinkedList = mapa[linhaAtual++].iterator();
                }
                this.cursor = (NoDupEncHash<K, V>) this.iteratorDaLinkedList.next();
                this.ultRetornado = null;
            }
        }

        @Override
        public boolean hasNext() {
            return this.elementosPercorridos != this.quantidadeTotal;
        }

        @Override
        public NoDupEncHash<K, V> next() {
            if (this.elementosPercorridos == this.quantidadeTotal) {
                throw new NoSuchElementException();
            }
            //A variavel verifica iterator ira checar se existe ainda algum iterator que podera ser utilizado
            boolean verificaIterator = true;
            this.elementosPercorridos++;
            this.ultRetornado = cursor;
            //Enquanto for retornado um iterator que nao possui proximo
            while (!this.iteratorDaLinkedList.hasNext()) {
                if (linhaAtual == mapa.length) {
                    verificaIterator = false;
                    break;
                }
                this.iteratorDaLinkedList = mapa[linhaAtual++].iterator();
            }
            //Caso ainda tenha iterators a serem utilizados
            if (verificaIterator) {
                this.cursor = (NoDupEncHash<K, V>) this.iteratorDaLinkedList.next();
                //Caso o cursor tenha chegado no ultimo elemento da linked list atual
                if (this.cursor == null) {
                    while (this.cursor == null) {
                        if (linhaAtual == mapa.length) {
                            break;
                        }
                        this.iteratorDaLinkedList = mapa[linhaAtual++].iterator();
                        this.cursor = (NoDupEncHash<K, V>) this.iteratorDaLinkedList.next();
                    }
                }
            }
            return ultRetornado;
        }

    }
}
