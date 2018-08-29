/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controles;


import Daos.CidadeDao;
import Util.Util;
import br.edu.ifsul.modelo.Cidade;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Leandro
 */
@ManagedBean(name="controlecidade")
@ViewScoped
public class ControleCidade implements Serializable {
    
    private CidadeDao<Cidade> dao;
    private Cidade cidade;
    
    
    public ControleCidade()
    {
        dao= new CidadeDao<>();
    }
    
    public String listar()
    {
        return "/privado/cidade/listar?faces-redirect=true";
    }
    
    public void novo()
    {
        cidade=new Cidade();
        
    }
    
    public void salvar()
    {
        boolean persistiu=false;
        
        if(cidade.getId()==null)
        {
            persistiu=dao.persist(cidade);
        }
        else
        {
            persistiu=dao.merge(cidade);
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
        cidade=dao.localizar(id);
    }
    
    public void remover(Integer id)
    {
        cidade=dao.localizar(id);
        if(dao.remove(cidade))
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
