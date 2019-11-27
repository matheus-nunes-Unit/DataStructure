/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfacesED.ListaSimplesmenteLigada;

import java.util.Iterator;

/**
 *
 * @author Matheus Nunes
 */
public class teste {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ListaSimplesmenteLigada<Integer> listaDeNomes = new ListaSimplesmenteLigada<>();
        listaDeNomes.adicionar(1);
        listaDeNomes.adicionar(2);
        listaDeNomes.adicionar(3);
        listaDeNomes.adicionar(4);
        listaDeNomes.adicionar(5);
        listaDeNomes.adicionar(6);
        listaDeNomes.adicionar(7);
       
        System.out.println(listaDeNomes);
        
    }
    
}
