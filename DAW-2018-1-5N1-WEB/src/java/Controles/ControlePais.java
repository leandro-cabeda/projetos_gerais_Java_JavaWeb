/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controles;


import PaisDao.PaisDao;
import Util.Util;
import br.edu.ifsul.modelo.Pais;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Leandro
 */
@ManagedBean(name="controlepais")
@ViewScoped
public class ControlePais implements Serializable {
    
    private PaisDao<Pais> dao;
    private Pais pais;
    
    
    public ControlePais()
    {
        dao= new PaisDao<>();
    }
    
    public String listar()
    {
        return "/privado/pais/listar?faces-redirect=true";
    }
    
    public void novo()
    {
        pais=new Pais();
        
    }
    
    public void salvar()
    {
        boolean persistiu=false;
        
        if(pais.getId()==null)
        {
            persistiu=dao.persist(pais);
        }
        else
        {
            persistiu=dao.merge(pais);
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
        pais=dao.localizar(id);
    }
    
    public void remover(Integer id)
    {
        pais=dao.localizar(id);
        if(dao.remove(pais))
        {
            Util.mensageminformacao(dao.getMensagem());
        }
        else
        {
            Util.mensagemErro(dao.getMensagem());
        }
        
    }

    public PaisDao getDao() {
        return dao;
    }

    public void setDao(PaisDao dao) {
        this.dao = dao;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }
    
}
