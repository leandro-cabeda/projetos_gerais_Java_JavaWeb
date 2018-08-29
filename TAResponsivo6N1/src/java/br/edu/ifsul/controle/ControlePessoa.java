/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.modelo.Pessoa;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Leandro
 */
@ManagedBean(name="controlePessoa")
@SessionScoped
public class ControlePessoa implements Serializable{
    private List<Pessoa> lista=new ArrayList<>();
    private Pessoa objeto;
    private Boolean editando;
    
    public ControlePessoa()
    {
        lista.add(new Pessoa(1,"Leandro","leandro.cabeda@hotmail.com"));
        lista.add(new Pessoa(2,"Leo","leo.cabe@hotmail.com"));
        lista.add(new Pessoa(3,"Le","le.ca@hotmail.com"));
    }
    
    public String listar()
    {
        editando=false;
        return "/pessoa/listar?faces-redirect=true";
    }
    
    public void novo()
    {
        objeto=new Pessoa();
        editando=true;
    }
    
    public void alterar(Pessoa obj)
    {
        objeto=obj;
        editando=true;
    }
    
    public void excluir(Pessoa obj)
    {
        lista.remove(obj);
        FacesMessage msg=new FacesMessage(FacesMessage.SEVERITY_INFO,Util.getMensagemInternacionalizada("crud.remover.sucesso"),"");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public void salvar()
    {
        if(objeto.getId()==null)
        {
            objeto.setId(lista.size()+1);
            lista.add(objeto);
        }
        editando=false;
        FacesMessage msg=new FacesMessage(FacesMessage.SEVERITY_INFO, Util.getMensagemInternacionalizada("crud.salvar.sucesso"),"");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    

    public List<Pessoa> getLista() {
        return lista;
    }

    public void setLista(List<Pessoa> lista) {
        this.lista = lista;
    }

    public Pessoa getObjeto() {
        return objeto;
    }

    public void setObjeto(Pessoa objeto) {
        this.objeto = objeto;
    }

    public Boolean getEditando() {
        return editando;
    }

    public void setEditando(Boolean editando) {
        this.editando = editando;
    }
    
    
}
