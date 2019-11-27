/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfacesED.ListaDuplamenteLigada;

import java.util.HashMap;

/**
 *
 * @author Matheus Nunes
 */
public class teste {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ListaDuplamenteLigada<Integer> pilha = new ListaDuplamenteLigada<>();
     
        for (Integer elementoAtual : pilha) {
            System.out.println(elementoAtual);
        }
        
        HashMap a = new HashMap();
        a.toString();
       // System.out.println(pilha);
    }
    }

