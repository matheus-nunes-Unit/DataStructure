/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfacesED.atividadePilha;

import java.util.Objects;

/**
 *
 * @author Matheus Nunes
 */
public class Carro {
    
    private String placa;
    private int numeroDeManobras;

    public Carro(String placa) {
        this.placa = placa;
        this.numeroDeManobras = 0;
    }
    
    public void adicionaNumeroDeManobras(){
        this.numeroDeManobras++;
    }

    public String getPlaca() {
        return placa;
    }

    public int getNumeroDeManobras() {
        return numeroDeManobras;
    }    

    @Override
    public String toString() {
        return this.placa;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Carro other = (Carro) obj;
        return Objects.equals(this.placa, other.placa);
    }
}
