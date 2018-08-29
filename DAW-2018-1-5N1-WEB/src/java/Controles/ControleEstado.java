/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controles;


import EstadoDao.EstadoDao;
import PaisDao.PaisDao;
import Util.Util;
import br.edu.ifsul.modelo.Estado;
import br.edu.ifsul.modelo.Pais;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Leandro
 */
@ManagedBean(name="controleestado")
@ViewScoped
public class ControleEstado implements Serializable {
    
    private Estado estado;
    private EstadoDao<Estado> dao;
    private PaisDao<Pais> daoPais;
    
    
    public ControleEstado()
    {
        dao= new EstadoDao<>();
        daoPais=new PaisDao<>();
    }
    
    public String listar()
    {
        return "/privado/estado/listar?faces-redirect=true";
    }
    
    public void novo()
    {
        estado=new Estado();
        
    }
    
    public void salvar()
    {
        boolean persistiu=false;
        
        if(estado.getId()==null)
        {
            persistiu=dao.persist(estado);
        }
        else
        {
            persistiu=dao.merge(estado);
        }
        if(persistiu)
        {
            Util.mensageminformacao(dao.getMensagem());
        }
        else
        {
            Util.mensagemErro(dao.getMensagem());
        }
    }
    
    
    public void editar(Integer id)
    {
        estado=dao.localizar(id);
    }
    
    public void remover(Integer id)
    {
        estado=dao.localizar(id);
        if(dao.remove(estado))
        {
            Util.mensageminformacao(dao.getMensagem());
        }
        else
        {
            Util.mensagemErro(dao.getMensagem());
        }
        
    }


    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public EstadoDao getDao() {
        return dao;
    }

    public void setDao(EstadoDao dao) {
        this.dao = dao;
    }

    public PaisDao<Pais> getDaoPais() {
        return daoPais;
    }

    public void setDaoPais(PaisDao<Pais> daoPais) {
        this.daoPais = daoPais;
    }

    

    
}
