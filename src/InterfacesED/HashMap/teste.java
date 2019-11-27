/*
* To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfacesED.HashMap;

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

        IHashMap<String, Integer> teste = new IHashMap<>();
        teste.inserir("zxcvbn", 2);
        //teste.limpar();
        System.out.println(teste);
        
        for (NoDupEncHash<String, Integer> noDupEncHash : teste) {
            
        }
    }

}
