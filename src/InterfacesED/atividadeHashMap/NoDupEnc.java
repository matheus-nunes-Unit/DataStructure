/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfacesED.atividadeHashMap;

/**
 *
 * @author Matheus Nunes
 */
public class NoDupEnc<T> {

    private T elemento;
    private NoDupEnc<T> proximo;
    private NoDupEnc<T> anterior;

    public NoDupEnc(T elemento) {
        this.elemento = elemento;
        this.anterior = null;
        this.proximo = null;
    }

    public T getElemento() {
        return elemento;
    }

    public void setElemento(T elemento) {
        this.elemento = elemento;
    }

    public NoDupEnc<T> getProximo() {
        return proximo;
    }

    public void setProximo(NoDupEnc<T> proximo) {
        this.proximo = proximo;
    }

    public NoDupEnc<T> getAnterior() {
        return anterior;
    }

    public void setAnterior(NoDupEnc<T> anterior) {
        this.anterior = anterior;
    }

}
