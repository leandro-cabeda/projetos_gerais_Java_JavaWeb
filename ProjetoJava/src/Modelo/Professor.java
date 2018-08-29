/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 *
 * @author Leandro
 */
public abstract class Professor {
    
    private String nome;
    private int matricula;
    private int idade;
    protected double salariotodo;
    private double salariototal=0;
    private ArrayList<Disciplina>dp=new ArrayList();
    private DecimalFormat df =  new DecimalFormat("#,##0.00");
    
    
    public Professor(String nome, int matricula,int idade)
    {
        this.nome=nome;
        this.matricula=matricula;
        this.idade=idade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }
    
    public double getsalariototal()
    {
        return salariototal;
    }

    public ArrayList<Disciplina> getDp() {
        return dp;
    }

    public void setDp(ArrayList<Disciplina> dp) {
        this.dp = dp;
    }
    
    public String getinfo()
    {
        String aux="";
        
        aux+="Nome:  "+nome+"\n\nMatricula:  "+matricula+"\n\nIdade:  "+idade;
        if(dp!=null)
        {
            aux+="\n\nDisciplina:  "+dp;
        }
        if(salariotodo!=0)
        {
            aux+="\n\nSalariotodo:  "+df.format(salariotodo);
        }
        if(salariototal!=0)
        {
            aux+="\n\nSalariototal:  "+df.format(salariototal);
        }
        
        return aux;
    }
    
     public void somarsalario(Professor conta)
    {   
        this.salariototal+=conta.getSalariotodo();
    }
    
    @Override
    public String toString(){
        
        String aux="";
        
        aux+="Nome: "+nome+"    Matricula: "+matricula+"   Idade: "+idade;
        
        if(dp!=null)
             aux+="\nDisciplina:  "+dp;
        
        if(salariotodo!=0)
        {
            aux+="\nSalariotodo:  "+df.format(salariotodo);
        }
        if(salariototal!=0)
        {
            aux+="    Salariototal:  "+df.format(salariototal);
        }
        
        return aux;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.matricula;
        return hash;
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
        final Professor other = (Professor) obj;
        if (this.matricula != other.matricula) {
            return false;
        }
        return true;
    }
    
    

    public double getSalariotodo() {
        return this.salariotodo;
    }
    
}
