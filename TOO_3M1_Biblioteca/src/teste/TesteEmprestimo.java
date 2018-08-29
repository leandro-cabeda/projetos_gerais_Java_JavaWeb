/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teste;

import javax.swing.JOptionPane;
import modelo.Autor;
import modelo.Emprestimo;
import modelo.Livro;
import modelo.Pessoa;

/**
 *
 * @author vanes
 */
public class TesteEmprestimo {
    public static void main(String[] args) {
        Autor a = new Autor();
        a.setNome("Joao da Silva");
        
        Livro l= new Livro();
        l.setTitulo("Livro Teste");
        l.setEditora("Edt");
        l.setAutor(a);
        
        Pessoa p1 = new Pessoa();
        p1.setNome("Juka");
        
        Emprestimo emp1 = new Emprestimo();
        emp1.setLivro(l);
        emp1.setPessoa(p1);
        JOptionPane.showMessageDialog(null, emp1.info());
        
        Emprestimo emp2 = new Emprestimo();
        emp2.setLivro(l);
        emp2.setPessoa(a);
        JOptionPane.showMessageDialog(null, emp2.info());
    }
}
