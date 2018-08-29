/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controles;



import Daos.GrupoDao;
import Daos.ModeloDao;
import FabricanteDao.FabricanteDao;
import Tarefa_JPA_Modelo.Fabricante;
import Tarefa_JPA_Modelo.Grupo;
import Tarefa_JPA_Modelo.Modelo;
import Util.Util;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Leandro
 */
@ManagedBean(name="controlemodelo")
@ViewScoped
public class ControleModelo implements Serializable {
    
    private GrupoDao<Grupo> daoGrupo;
    private Grupo grupo;
    private Modelo modelo;
    private ModeloDao<Modelo> daoModelo;
    private Fabricante fabricante;
    private FabricanteDao daoFabricante;
    
    
    public ControleModelo()
    {
        daoGrupo=new GrupoDao<>();
        daoFabricante=new FabricanteDao();
        daoModelo=new ModeloDao<>();
    }
    
    
    /*public String listar()
    {
        return "/privado/modelo/listar?faces-redirect=true";
    }*/
    
    public void novo()
    {
        modelo=new Modelo();
    }
   
    
    public void salvar()
    {
        boolean persistiu=false;
        
        if(modelo.getId()==null)
        {
            persistiu=daoModelo.persist(modelo);
        }
        else
        {
            persistiu=daoModelo.merge(modelo);
        }
        if(persistiu)
        {
            Util.mensageminformacao(daoModelo.getMensagem());
        }
        else
        {
            Util.mensagemErro(daoModelo.getMensagem());
        }
    }
    
    
    public void editar(Integer id)
    {
        modelo=daoModelo.localizar(id);
    }
    
    public void remover(Integer id)
    {
        modelo=daoModelo.localizar(id);
        if(daoModelo.remove(modelo))
        {
            Util.mensageminformacao(daoModelo.getMensagem());
        }
        else
        {
            Util.mensagemErro(daoModelo.getMensagem());
        }
        
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public GrupoDao<Grupo> getDaoGrupo() {
        return daoGrupo;
    }

    public void setDaoGrupo(GrupoDao<Grupo> daoGrupo) {
        this.daoGrupo = daoGrupo;
    }

    public Modelo getModelo() {
        return modelo;
    }

    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }

    public ModeloDao<Modelo> getDaoModelo() {
        return daoModelo;
    }

    public void setDaoModelo(ModeloDao<Modelo> daoModelo) {
        this.daoModelo = daoModelo;
    }

    public Fabricante getFabricante() {
        return fabricante;
    }

    public void setFabricante(Fabricante fabricante) {
        this.fabricante = fabricante;
    }

    public FabricanteDao getDaoFabricante() {
        return daoFabricante;
    }

    public void setDaoFabricante(FabricanteDao daoFabricante) {
        this.daoFabricante = daoFabricante;
    }


    

    
}
