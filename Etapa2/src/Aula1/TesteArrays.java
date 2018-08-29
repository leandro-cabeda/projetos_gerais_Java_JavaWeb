/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aula1;

import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Leandro
 */
public class TesteArrays {public static void main(String[] args) {
        //ArrayList lista= new ArrayList(); utilizado para todos tipos
        ArrayList<String>lista= new ArrayList();
       // lista.add(88); // adiciona elemento dentro da lista
        lista.add("Joao da Fonseca");
        //lista.add(45.3);
        
       JOptionPane.showMessageDialog(null, lista);
       JOptionPane.showMessageDialog(null,"Tamanho da lista " +lista.size()); // mostra tamanho da lista
       lista.remove("Joao da Fonseca"); // remove elemento da lista
       JOptionPane.showMessageDialog(null, lista);
      JOptionPane.showMessageDialog(null,"Pegando um elemento "+ lista.get(0));
      //lista.set(3,"teste");// subtitui um elemento no tal indice
      //JOptionPane.showMessageDialog(null, lista);
    }
    
}
