/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controles;

import CidadeDao.CidadeDao;
import ClienteDao.ClienteDao;
import Tarefa_JPA_Modelo.Cliente;
import Util.Util;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Leandro
 */
@ManagedBean(name="controlecliente")
@SessionScoped
public class ControleCliente implements Serializable {
    
    private Cliente cliente;
    private ClienteDao<Cliente> dao;
    private CidadeDao daoCidade;
    
    public ControleCliente()
    {
        dao= new ClienteDao<>();
        daoCidade=new CidadeDao();
    }
    
    
    public String listar()
    {
        return "/privado/cliente/listar?faces-redirect=true";
    }
    
    public String novo()
    {
        cliente=new Cliente();
        
        return "formulario?faces-redirect=true";
    }
    
    public String salvar()
    {
        boolean persistiu=false;
        
        if(cliente.getId()==null)
        {
            persistiu=dao.persist(cliente);
        }
        else
        {
            persistiu=dao.merge(cliente);
        }
        if(persistiu)
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
        cliente=dao.localizar(id);
        return "formulario?faces-redirect=true";
    }
    
    public void remover(Integer id)
    {
        cliente=dao.localizar(id);
        if(dao.remove(cliente))
        {
            Util.mensageminformacao(dao.getMensagem());
        }
        else
        {
            Util.mensagemErro(dao.getMensagem());
        }
        
    }
    

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public ClienteDao<Cliente> getDao() {
        return dao;
    }

    public void setDao(ClienteDao<Cliente> dao) {
        this.dao = dao;
    }

    public CidadeDao getDaoCidade() {
        return daoCidade;
    }

    public void setDaoCidade(CidadeDao daoCidade) {
        this.daoCidade = daoCidade;
    }
    
    
}
