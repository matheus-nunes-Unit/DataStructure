/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfacesED.atividadePilha;

/**
 *
 * @author Matheus Nunes
 */
public interface Pilha<T> extends Iterable<T>{
    
    public abstract void push(T elemento);
    
    public abstract T pop();

    public abstract int size();
    
    public abstract boolean isEmpty();
    
    public abstract T top();
    
}
