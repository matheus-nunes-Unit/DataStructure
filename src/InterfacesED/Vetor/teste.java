/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfacesED.Vetor;

import InterfacesED.Fila;
import InterfacesED.Lista;
import InterfacesED.Pilha;
import java.util.Iterator;


/**
 *
 * @author 1181123221
 */
public class teste {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Pilha<Integer> ass = new Vetor<Integer>(0);
//        ass.enfileirar(1);
//        ass.enfileirar(2);
//        ass.enfileirar(3);
//        ass.desenfileirar();
        ass.push(1);
        ass.push(2);
        ass.push(3);
        //System.out.println(ass.size());
        for (Iterator iterator = ass.iterator(); iterator.hasNext();) {
            System.out.println(iterator.next());
            iterator.remove();
        }
//        for (Integer as : ass) {
//            System.out.println(as);
//        }
        System.out.println(ass);

    }
}
