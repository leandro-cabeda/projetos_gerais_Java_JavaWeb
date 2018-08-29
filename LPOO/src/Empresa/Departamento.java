/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Empresa;

/**
 *
 * @author Leandro
 */
public class Departamento extends Empresa {
   private String nome;
   private Empresa empresa;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }
    
    public String toString()
    {
        String aux=nome;
        if(empresa!=null)
            aux+="\nEmpresa: "+empresa;
        return aux;
    }
    
}
