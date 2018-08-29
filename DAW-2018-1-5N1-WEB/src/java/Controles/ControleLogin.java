/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controles;

import Daos.PessoaFisicaDao;
import Util.Util;
import br.edu.ifsul.modelo.PessoaFisica;
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
    
    private PessoaFisicaDao<PessoaFisica> dao;
    private PessoaFisica usuarioLogado;
    private String usuario;
    private String senha;

    public ControleLogin() {
        dao=new PessoaFisicaDao<>();
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

    public PessoaFisicaDao<PessoaFisica> getDao() {
        return dao;
    }

    public void setDao(PessoaFisicaDao<PessoaFisica> dao) {
        this.dao = dao;
    }

    public PessoaFisica getUsuarioLogado() {
        return usuarioLogado;
    }

    public void setUsuarioLogado(PessoaFisica usuarioLogado) {
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
