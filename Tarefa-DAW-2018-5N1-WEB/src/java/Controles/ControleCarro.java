/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controles;


import Daos.CarroDao;
import Daos.ModeloDao;
import Tarefa_JPA_Modelo.Carro;
import Tarefa_JPA_Modelo.Modelo;
import Util.Util;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Leandro
 */
@ManagedBean(name="controlecarro")
@ViewScoped
public class ControleCarro implements Serializable {
    
    private CarroDao<Carro> dao;
    private Carro carro;
    private ModeloDao<Modelo>daoModelo;
    
    
    public ControleCarro()
    {
        dao= new CarroDao<>();
        daoModelo= new ModeloDao<>();
    }
    
    public String listar()
    {
        return "/privado/carro/listar?faces-redirect=true";
    }
    
    public void novo()
    {
        carro=new Carro();
        
    }
    
    public void salvar()
    {
        boolean persistiu=false;
        
        if(carro.getId()==null)
        {
            persistiu=dao.persist(carro);
        }
        else
        {
            persistiu=dao.merge(carro);
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
        carro=dao.localizar(id);
    }
    
    public void remover(Integer id)
    {
        carro=dao.localizar(id);
        if(dao.remove(carro))
        {
            Util.mensageminformacao(dao.getMensagem());
        }
        else
        {
            Util.mensagemErro(dao.getMensagem());
        }
        
    }

    public CarroDao<Carro> getDao() {
        return dao;
    }

    public void setDao(CarroDao<Carro> dao) {
        this.dao = dao;
    }

    public Carro getCarro() {
        return carro;
    }

    public void setCarro(Carro carro) {
        this.carro = carro;
    }

    public ModeloDao<Modelo> getDaoModelo() {
        return daoModelo;
    }

    public void setDaoModelo(ModeloDao<Modelo> daoModelo) {
        this.daoModelo = daoModelo;
    }
   
    
}
