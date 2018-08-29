/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;

import java.io.Serializable;
import java.util.Calendar;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;



/**
 *
 * @author Leandro
 */
@ManagedBean(name="controleIndex")
@RequestScoped // ciclo de vida mais curto
//@SessionScoped // ciclo de vida de sessão
//@ApplicationScoped // ciclo de vida mais longo existe enquanto a app estiver no rodando
public class Controleindex implements Serializable {
    
    private String ola;
    private Calendar dataHoraServidor;

    
    public Controleindex()
    {
        ola="Seja bem vindo ao Java Serve Faces";
        dataHoraServidor=Calendar.getInstance();
    }
    public String sobre()
    {
        mensagemJSF("Navegando para o arquivo sobre....");
        return "Sobre?faces-redirect=true";
    }
    
    public String index()
    {
        mensagemJSF("Navegando para o arquivo index....");
        return "index?faces-redirect=true";
    }
    
    public void mensagemJSF(String mensagem)
    {
        FacesContext contexto=FacesContext.getCurrentInstance();
        contexto.getExternalContext().getFlash().setKeepMessages(true);
        FacesMessage msg= new FacesMessage(FacesMessage.SEVERITY_INFO,mensagem,"");
        contexto.addMessage(null, msg);
    }
    
    public String getOla() {
        return ola;
    }

    /**
     * @param ola the ola to set
     */
    public void setOla(String ola) {
        this.ola = ola;
    }

    /**
     * @return the dataHoraServidor
     */
    public Calendar getDataHoraServidor() {
        return dataHoraServidor;
    }

    /**
     * @param dataHoraServidor the dataHoraServidor to set
     */
    public void setDataHoraServidor(Calendar dataHoraServidor) {
        this.dataHoraServidor = dataHoraServidor;
    }
    
}
