/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controles;


import CidadeDao.CidadeDao;
import Tarefa_JPA_Modelo.Cidade;
import Util.Util;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Leandro
 */
@ManagedBean(name="controlecidade")
@SessionScoped
public class ControleCidade implements Serializable {
    
    private CidadeDao dao;
    private Cidade cidade;
    
    
    public ControleCidade()
    {
        dao= new CidadeDao();
    }
    
    public String listar()
    {
        return "/privado/cidade/listar?faces-redirect=true";
    }
    
    public String novo()
    {
        cidade=new Cidade();
        
        return "formulario?faces-redirect=true";
    }
    
    public String salvar()
    {
        if(dao.salvar(cidade))
        {
            Util.mensageminformacao(dao.getMensagem());
            return "listar?faces-redirect=true";
        }
        else
        {
            Util.mensagemErro(dao.getMensagem());
            return "formulario?faces-redirect=true";
        }
    }
    
    public String cancelar()
    {
        return "listar?faces-redirect=true";
    }
    
    public String editar(Integer id)
    {
        cidade=dao.localizar(id);
        return "formulario?faces-redirect=true";
    }
    
    public void remover(Integer id)
    {
        cidade=dao.localizar(id);
        if(dao.remover(cidade))
        {
            Util.mensageminformacao(dao.getMensagem());
        }
        else
        {
            Util.mensagemErro(dao.getMensagem());
        }
        
    }

    public CidadeDao getDao() {
        return dao;
    }

    public void setDao(CidadeDao dao) {
        this.dao = dao;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }
    
}
