/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controles;



import AcessorioDao.AcessorioDao;
import Daos.GrupoDao;
import Tarefa_JPA_Modelo.Acessorio;
import Tarefa_JPA_Modelo.Grupo;
import Util.Util;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Leandro
 */
@ManagedBean(name="controleacessorio")
@ViewScoped
public class ControleAcessorio implements Serializable {
    
    private Grupo grupo;
    private GrupoDao<Grupo> daoGrupo;
    private AcessorioDao<Acessorio> daoAcessorio;
    private Acessorio acessorio;
    
    
    public ControleAcessorio()
    {
        daoGrupo=new GrupoDao<>();
        daoAcessorio= new AcessorioDao<>();
    }
    
    public void adicionarGrupo()
    {
        if(grupo!=null)
        {
            if(!acessorio.getGrupos().contains(grupo))
            {
                acessorio.getGrupos().add(grupo);
                Util.mensageminformacao("Conjunto adicionado com sucesso!");
            }
            else
            {
                Util.mensagemErro("Este conjunto já existe na sua lista!");
            }
        }
        else
        {
            Util.mensagemErro("selecione um grupo!");
        }
    }
    
    public void removerGrupo(int index)
    {
        grupo=acessorio.getGrupos().get(index);
        acessorio.getGrupos().remove(grupo);
        Util.mensageminformacao("Conjunto removido com sucesso!");
    }
    
    
    public String listar()
    {
        return "/privado/acessorio/listar?faces-redirect=true";
    }
    
    public void novo()
    {
        acessorio=new Acessorio();
    }
   
    
    public void salvar()
    {
        boolean persistiu=false;
        
        if(acessorio.getId()==null)
        {
            persistiu=daoAcessorio.persist(acessorio);
        }
        else
        {
            persistiu=daoAcessorio.merge(acessorio);
        }
        if(persistiu)
        {
            Util.mensageminformacao(daoAcessorio.getMensagem());
        }
        else
        {
            Util.mensagemErro(daoAcessorio.getMensagem());
        }
    }
    
    
    public void editar(Integer id)
    {
        acessorio=daoAcessorio.localizar(id);
    }
    
    public void remover(Integer id)
    {
        acessorio=daoAcessorio.localizar(id);
        if(daoAcessorio.remove(acessorio))
        {
            Util.mensageminformacao(daoAcessorio.getMensagem());
        }
        else
        {
            Util.mensagemErro(daoAcessorio.getMensagem());
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


    public AcessorioDao getDaoAcessorio() {
        return daoAcessorio;
    }

    public void setDaoAcessorio(AcessorioDao daoAcessorio) {
        this.daoAcessorio = daoAcessorio;
    }

    public Acessorio getAcessorio() {
        return acessorio;
    }

    public void setAcessorio(Acessorio acessorio) {
        this.acessorio = acessorio;
    }

    

    
}
