/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CidadeDao;

import EntityManagerUtil.EntityManagerUtil;
import Tarefa_JPA_Modelo.Cidade;
import Util.Util;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Leandro
 */
public class CidadeDao implements Serializable {

    private String mensagem;
    private EntityManager em;

    public CidadeDao() 
    {
        em = EntityManagerUtil.getEntityManager();
    }

    public List<Cidade> getLista() 
    {
        return em.createQuery("from Cidade order by nome").getResultList();
    }
    
    public boolean salvar(Cidade obj) // Adicionar / Atualizar
    {
        
        try{
            em.getTransaction().begin();
            if(obj.getId()==null)
            {
                em.persist(obj);
                mensagem="Cidade persistido com sucesso";
            }
            else
            {
                em.merge(obj);
                mensagem="Cidade atualizado com sucesso";
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
            mensagem="Erro ao persistir Cidade:  "+Util.getMensagemErro(e);
            return false;
        }
    }
    
    public boolean remover(Cidade obj) // Remover/ Apagar
    {
        
        try{
            em.getTransaction().begin();
            em.remove(obj);
            em.getTransaction().commit();
            mensagem="Cidade removido com sucesso";
            return true;
        
        }catch(Exception e)
        {
            if(em.getTransaction().isActive()==false)
            {
                em.getTransaction().commit();
            }
            em.getTransaction().rollback();
            mensagem="Erro ao remover Cidade: "+Util.getMensagemErro(e);
            return false;
        }
    }
    
    
    public Cidade localizar(Integer id)  // Recuperar
    {
        return em.find(Cidade.class, id);
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
