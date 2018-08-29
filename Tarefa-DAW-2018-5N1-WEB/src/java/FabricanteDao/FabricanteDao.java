/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FabricanteDao;

import EntityManagerUtil.EntityManagerUtil;
import Tarefa_JPA_Modelo.Fabricante;
import Util.Util;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Leandro
 */
public class FabricanteDao {
    
    private String mensagem;
    private EntityManager em;

    public FabricanteDao() 
    {
        em = EntityManagerUtil.getEntityManager();
    }

    public List<Fabricante> getLista() 
    {
        return em.createQuery("from Fabricante order by nome").getResultList();
    }
    
    public boolean salvar(Fabricante obj) // Adicionar / Atualizar
    {
        
        try{
            em.getTransaction().begin();
            if(obj.getId()==null)
            {
                em.persist(obj);
                mensagem="Fabricante persistido com sucesso";
            }
            else
            {
                em.merge(obj);
                mensagem="Fabricante atualizado com sucesso";
            }
            em.getTransaction().commit();
            return true;
        
        }catch(Exception e)
        {
            if(em.getTransaction().isActive()==false)
            {
                em.getTransaction().commit();
            }
            em.getTransaction().rollback();
            mensagem="Erro ao persistir Fabricante:  "+Util.getMensagemErro(e);
            return false;
        }
    }
    
    public boolean remover(Fabricante obj) // Remover/ Apagar
    {
        
        try{
            em.getTransaction().begin();
            em.remove(obj);
            em.getTransaction().commit();
            mensagem="Fabricante removido com sucesso";
            return true;
        
        }catch(Exception e)
        {
            if(em.getTransaction().isActive()==false)
            {
                em.getTransaction().commit();
            }
            em.getTransaction().rollback();
            mensagem="Erro ao remover Fabricante: "+Util.getMensagemErro(e);
            return false;
        }
    }
    
    
    public Fabricante localizar(Integer id)  // Recuperar
    {
        return em.find(Fabricante.class, id);
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }
    
}
