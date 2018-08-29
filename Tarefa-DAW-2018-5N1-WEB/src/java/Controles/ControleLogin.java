/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controles;

import Daos.VendedorDao;
import Tarefa_JPA_Modelo.Vendedor;
import Util.Util;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Leandro
 */
@ManagedBean(name="controlelogin")
@SessionScoped
public class ControleLogin implements Serializable {
    
    private VendedorDao<Vendedor> dao;
    private Vendedor usuarioLogado;
    private String usuario;
    private String senha;

    public ControleLogin() {
        dao=new VendedorDao<>();
    }
    
    public String paginaLogin()
    {
        return "login?faces-redirect=true";
    }
    
    public String efetuarLogin()
    {
        if(dao.login(usuario, senha))
        {
            usuarioLogado=dao.lozalicaPorNomeUsuario(usuario);
           Util.mensageminformacao("Login realziado com sucesso!");
           usuario="";
           senha="";
           return "/index?faces-redirect=true";
        }
        else
        {
            Util.mensagemErro("Usuário ou senha inválidos!");
            return "login?faces-redirect=true";
        }
    }
    
    public String efetuarLogout(){
        usuarioLogado=null;
        Util.mensageminformacao("Logout realizado com sucesso!");
        return "/index?faces-redirect=true";
    }

    public VendedorDao<Vendedor> getDao() {
        return dao;
    }

    public void setDao(VendedorDao<Vendedor> dao) {
        this.dao = dao;
    }

    public Vendedor getUsuarioLogado() {
        return usuarioLogado;
    }

    public void setUsuarioLogado(Vendedor usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
}
