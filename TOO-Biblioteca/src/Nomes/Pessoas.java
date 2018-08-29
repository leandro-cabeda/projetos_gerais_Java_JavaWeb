/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Nomes;

import Autor.Autor;
import Emprestimo.Emprestimo;
import Livro.Livro;
import Pessoa.Pessoa;
import java.util.ArrayList;

/**
 *
 * @author Leandro
 */
public class Pessoas {
    public static void main(String[] args) {
        Pessoa p1=new Pessoa();
        Emprestimo e=new Emprestimo();
        Autor a=new Autor();
        Livro l=new Livro();
        ArrayList<Autor>autores=new ArrayList<>();
        ArrayList<Livro>livros=new ArrayList<>();
        
        p1.setNome("Leandro");
        e.setemp("05/06/2100");
        e.setdevo("09/03/2180");
        l.settitulo("A volta dos macumbeiros");
        a.setNome("Lapacino");
        l.setautor(a);
        autores.add(a);
        livros.add(l);
    }
    
}
