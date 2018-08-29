/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Autor;

import Pessoa.Pessoa;




/**
 *
 * @author Leandro
 */
public class Autor extends Pessoa {
    //private String nome;
    
    
   /* public void setnome(String nome)
    {
        this.nome=nome;
    }
    public String getnome()
    {
        return nome;
    }*/
    public boolean equals(Object obj)
    {
        Autor aut=(Autor)obj;
        if(this.nome.equals(aut.getNome()))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    /*@Override
    public String toString()
    {
        return nome;
    }*/
}
