/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Livro;

import Autor.Autor;

/**
 *
 * @author Leandro
 */
public class Livro {
    private String titulo;
    private String editora;
    private Autor a;
    
    public void settitulo(String titulo)
    {
        this.titulo=titulo;
    }
    public String gettitulo()
    {
        return titulo;
    }
    public void seteditora(String editora)
    {
        this.editora=editora;
    }
    public String geteditora()
    {
        return editora;
    }
    public void setautor(Autor autor)
    {
        a=autor;
    }
    public Autor getautor()
    {
        return a;
    }
    public boolean equals(Object obj)
    {
        Livro outro=(Livro)obj;
        if(this.titulo.equals(outro.gettitulo()))
        {
            if(this.editora.equals(outro.geteditora()))
            {
                 if(this.a.equals(outro.getautor()))
                 {
                     return true;
                 }
            }
        }
        return false;
    }
    
    public String toString()
    {
        return "Titulo do livro:  "+titulo+"   Editora:  "+editora+"   Autor:  "+a+"\n";
    }
}
