/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfacesED.HashMap;


/**
 *
 * @author Matheus Nunes
 */
public class NoDupEncHash<K,V> {
    
    private K key;
    private V elemento;

    public NoDupEncHash(K key,V elemento) {
        this.key = key;
        this.elemento = elemento;
    }

    public V getElemento() {
        return elemento;
    }

    public void setElemento(V elemento) {
        this.elemento = elemento;
    }
    
    public K getKey(){
        return this.key;
    }
    
    public void setKey(K key){
        this.key = key;
    }

    @Override
    public String toString() {
        return this.key + " = " + this.elemento;
    }
}
