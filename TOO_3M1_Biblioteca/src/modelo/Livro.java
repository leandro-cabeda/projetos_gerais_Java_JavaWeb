/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author vanes
 */
public class Livro {

    private String titulo;
    private String editora;
    private Autor autor;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public boolean equals(Object obj) {
        Livro outro = (Livro) obj;
        if (this.titulo.equals(outro.getTitulo())
                && this.editora.equals(outro.getEditora())
                && this.autor.equals(outro.getAutor())) {
            return true;
        }

        return false;
    }

    public String toString(){
        return titulo;
    }
}
