/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controles;

import FabricanteDao.FabricanteDao;
import Tarefa_JPA_Modelo.Fabricante;
import Util.Util;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Leandro
 */
@ManagedBean(name="controlefabricante")
@SessionScoped
public class ControleFabricante {
    private FabricanteDao dao;
    private Fabricante fabricante;
    
    
    public ControleFabricante()
    {
        dao= new FabricanteDao();
    }
    
    public String listar()
    {
        return "/privado/fabricante/listar?faces-redirect=true";
    }
    
    public String novo()
    {
        fabricante=new Fabricante();
        
        return "formulario?faces-redirect=true";
    }
    
    public String salvar()
    {
        if(dao.salvar(fabricante))
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
        fabricante=dao.localizar(id);
        return "formulario?faces-redirect=true";
    }
    
    public void remover(Integer id)
    {
        fabricante=dao.localizar(id);
        if(dao.remover(fabricante))
        {
            Util.mensageminformacao(dao.getMensagem());
        }
        else
        {
            Util.mensagemErro(dao.getMensagem());
        }
        
    }

    public FabricanteDao getDao() {
        return dao;
    }

    public void setDao(FabricanteDao dao) {
        this.dao = dao;
    }

    public Fabricante getFabricante() {
        return fabricante;
    }

    public void setFabricante(Fabricante fabricante) {
        this.fabricante = fabricante;
    }

    
}
