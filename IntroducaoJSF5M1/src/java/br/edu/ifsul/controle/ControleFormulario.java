/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Leandro
 */
@ManagedBean(name="controleformulario")
@SessionScoped
public class ControleFormulario implements Serializable {
    
    private String nome;
    private String cargo;
    private String saida;
    
    public ControleFormulario()
    {
        
    }
    
    public String irFormulario()
    {
        nome="";
        cargo="";
        return "formulario?faces-redirect=true";
    }
    
    public String processar()
    {
        saida="Nome : "+nome+ "Cargo: "+cargo;
        return "dados?faces-redirect=true";
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the cargo
     */
    public String getCargo() {
        return cargo;
    }

    /**
     * @param cargo the cargo to set
     */
    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    /**
     * @return the saida
     */
    public String getSaida() {
        return saida;
    }

    /**
     * @param saida the saida to set
     */
    public void setSaida(String saida) {
        this.saida = saida;
    }
    
    
    
}
