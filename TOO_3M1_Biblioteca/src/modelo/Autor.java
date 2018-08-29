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
public class Autor extends Pessoa{
    
//    @Override
//    public String toString() {
//        return nome;
//    }
    
    public boolean equals(Object obj) {
        Autor aut = (Autor) obj;
        if(this.nome.equals(aut.getNome())){
            return true;
        } else {
            return false;
        }
    }
}
