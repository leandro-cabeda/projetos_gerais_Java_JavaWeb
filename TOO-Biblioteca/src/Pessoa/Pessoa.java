/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pessoa;


/**
 *
 * @author Leandro
 */
public class Pessoa {
    //private String nome;
    protected String nome;
    protected String CPF;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }
    @Override
    public String toString() {
        return nome +" - "+CPF;
     
    }
    
}
