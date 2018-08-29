/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teste;

import java.util.ArrayList;
import modelo.*;

/**
 *
 * @author vanes
 */
public class Teste { 
   
    
    public static void main(String[] args) {
        ArrayList<Autor> autores =  new ArrayList<>();
        ArrayList<Livro> livros =  new ArrayList<>();
        
        
        Autor a = new Autor();
        a.setNome("Autor 012");
        
        autores.add(a);
        
        Livro l = new Livro();
        l.setTitulo("Livro 123");
        l.setEditora("Nova Era");
        l.setAutor(a);
        
        livros.add(l);
        
    }
}
