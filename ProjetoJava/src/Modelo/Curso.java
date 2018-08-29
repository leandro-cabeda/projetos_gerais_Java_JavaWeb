/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author Leandro
 */
public class Curso {
    private String nome;
    private ArrayList<Disciplina>dp=new ArrayList();

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ArrayList<Disciplina> getDp() {
        return dp;
    }

    public void setDp(ArrayList<Disciplina> dp) {
        this.dp = dp;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + Objects.hashCode(this.nome);
        return hash;
    }

    @Override
    public String toString() {
        
        String aux="";
        aux+= "Curso{";
        if(nome!=null)
                aux+="    nome = " + nome + ", ";
         
       if(dp!=null)
                aux+= "    Disciplina = " + dp;
                        
         aux+= "  }";
       
       return aux;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Curso other = (Curso) obj;
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        return true;
    }
    
    
    
    
      
    
}
