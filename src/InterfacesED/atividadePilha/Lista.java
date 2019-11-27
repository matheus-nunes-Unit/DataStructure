/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfacesED.atividadePilha;

/**
 *
 * @author 11645
 */
public interface Lista<T> extends Iterable<T> {

    public abstract void adicionar(T elemento);

    public abstract void adicionarNoInicio(T elemento);

    public abstract void adicionarNoFim(T elemento);

    public abstract void adicionar(T elemento, int posicao);

    public abstract T remover(int posicao);

    public abstract T remover(T elemento);

    public abstract T removerNoInicio();

    public abstract T removerNoFim();

    public abstract int tamanho();

    public abstract void limpar();

    public abstract T buscar(int posicao);

    public abstract int indexOf(T elemento);

    public abstract boolean contem(T elemento);

//  get(int index) : LinkedList O(n) e ArrayList O(1)
//  add(E element) : LinkedList O(1) e ArrayList O(n) no pior caso (que é quando o vetor tem que ser redimensionado e copiado para um novo array)
//  add(int index, E element) : LinkedList O(n) e ArrayList O(n) no pior caso
//  remove(int index) : LinkedList O(n) e ArrayList O (n-index), se remover o último elemento então fica O(1)
}
