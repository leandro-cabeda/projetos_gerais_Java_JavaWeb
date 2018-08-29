/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Teste;

import Autor.Autor;
import Emprestimo.Emprestimo;
import Livro.Livro;
import Pessoa.Pessoa;
import javax.swing.JOptionPane;

/**
 *
 * @author Leandro
 */
public class TesteEmprestimo {
    public static void main(String[] args) {
        Autor a = new Autor();
        a.setNome("Joao da Silva");
        
        Livro l= new Livro();
        l.settitulo("Livro Teste");
        l.seteditora("Edt");
        l.setautor(a);
        
        Pessoa p1 = new Pessoa();
        p1.setNome("Juka");
        
        Emprestimo emp1 = new Emprestimo();
        emp1.setlivro(l);
        emp1.setpessoa(p1);
        JOptionPane.showMessageDialog(null, emp1.info());
        
        Emprestimo emp2 = new Emprestimo();
        emp2.setlivro(l);
        emp2.setpessoa(a);
        JOptionPane.showMessageDialog(null, emp2.info());
        
    }
    
}
