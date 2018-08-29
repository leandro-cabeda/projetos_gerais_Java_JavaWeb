/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import EntityManagerUtil.EntityManagerUtil;
import Util.Util;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Leandro
 */
public class DaoGenerico<T> implements Serializable {  // <T> significa quem for usar essa classe tem que informar o tipo
    // lista que vai trazer a consulta paginada e filtrada
    private List<T> listaObjetos;
    
    // lista que vai retornar todos os objetos
    private List<T> listaTodos;
    
    // atributo que armazena a classe que o Dao estará utilizando
    protected Class ClassePersistente;
    
    // atributo que vai armazenar mensagens de erro ou sucesso
    protected String mensagem="";
    
    protected EntityManager em;
    
    // atributo que armazena a ordem da consulta
    protected String ordem="id";
    
    // atributo que armazena o filtro utilizado na consulta
    protected String filtro="";
    
    
    protected Integer maximoObjetos=8;
    protected Integer posicaoAtual=0;
    protected Integer totalObjetos=0;

    public DaoGenerico() {
        em=EntityManagerUtil.getEntityManager();
    }
    
    
    
    

    public List<T> getListaObjetos() {
        String jpql="from "+ClassePersistente.getSimpleName();
        String where="";
        
        // limpar o filtro para evitar injeção de SQL
        filtro=filtro.replaceAll("[';-]","");
        
        if(filtro.length()>0)
        {
            if(ordem.equals("id"))
            {
                try {
                    Integer.parseInt(filtro);
                    where+=" where "+ordem+" = '"+filtro+"'";
                } catch (Exception e) {}
            }else
            {
                where+=" where upper("+ordem+") like '"+filtro.toUpperCase()+"%'";
            }
        }
        jpql+=where;
        jpql+=" order by "+ordem;
        totalObjetos=em.createQuery(jpql).getResultList().size();
        return em.createQuery(jpql).setFirstResult(posicaoAtual).setMaxResults(maximoObjetos).getResultList();
    }
    
     public List<T> getListaTodos() {
         String jpql="from "+ClassePersistente.getSimpleName()+" order by "+ordem;
        return em.createQuery(jpql).getResultList();
    }
     
     public void primeiro()
     {
         posicaoAtual=0;
         
     }
     
     public void anterior()
     {
         posicaoAtual-=maximoObjetos;
         if(posicaoAtual<0)
         {
             posicaoAtual=0;
         }
     }
     
     public void proximo()
     {
         if(posicaoAtual + maximoObjetos < totalObjetos)
         {
             posicaoAtual+=maximoObjetos;
         }
     }
     
     public void ultimo()
     {
         int resto=totalObjetos%maximoObjetos;
         if(resto>0)
         {
             posicaoAtual=totalObjetos-resto;
         }else
         {
             posicaoAtual=totalObjetos-maximoObjetos;
         }
     }
     
     public String getMensagemNavegacao()
     {
         int ate=posicaoAtual+maximoObjetos;
         if(ate >totalObjetos)
         {
             ate=totalObjetos;
         }
         return "Listando de "+(posicaoAtual+1)+ " ate "+ate+" de "+totalObjetos+" registros";
     }
     
     public void rollback()
     {
         if(em.getTransaction().isActive()==false)
         {
             em.getTransaction().begin();
         }
         em.getTransaction().rollback();
     }
     
    public boolean persist(T obj)
    {
        try {
            em.getTransaction().begin();
            em.persist(obj);
            em.getTransaction().commit();
            mensagem="Objeto persistido com sucesso!!";
            return true;
        } catch (Exception e) {
            rollback();
            mensagem="Erro ao persistir: "+Util.getMensagemErro(e);
            return false;
        }
    }
    
    public boolean merge(T obj)
    {
        try {
            em.getTransaction().begin();
            em.merge(obj);
            em.getTransaction().commit();
            mensagem="Objeto atualizado com sucesso!!";
            return true;
        } catch (Exception e) {
            rollback();
            mensagem="Erro ao atualizar: "+Util.getMensagemErro(e);
            return false;
        }
    }
    
    public boolean remove(T obj)
    {
        try {
            em.getTransaction().begin();
            em.remove(obj);
            em.getTransaction().commit();
            mensagem="Objeto removido com sucesso!!";
            return true;
        } catch (Exception e) {
            rollback();
            mensagem="Erro ao remover: "+Util.getMensagemErro(e);
            return false;
        }
    }
    
    public T localizar(Integer id)
    {
        rollback();
        T obj=(T)em.find(ClassePersistente, id);
        return obj;
    }

    public void setListaObjetos(List<T> listaObjetos) {
        this.listaObjetos = listaObjetos;
    }

    public void setListaTodos(List<T> listaTodos) {
        this.listaTodos = listaTodos;
    }

    public Class getClassePersistente() {
        return ClassePersistente;
    }

    public void setClassePersistente(Class ClassePersistente) {
        this.ClassePersistente = ClassePersistente;
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

    public String getOrdem() {
        return ordem;
    }

    public void setOrdem(String ordem) {
        this.ordem = ordem;
    }

    public String getFiltro() {
        return filtro;
    }

    public void setFiltro(String filtro) {
        this.filtro = filtro;
    }

    public Integer getMaximoObjetos() {
        return maximoObjetos;
    }

    public void setMaximoObjetos(Integer maximoObjetos) {
        this.maximoObjetos = maximoObjetos;
    }

    public Integer getPosicaoAtual() {
        return posicaoAtual;
    }

    public void setPosicaoAtual(Integer posicaoAtual) {
        this.posicaoAtual = posicaoAtual;
    }

    public Integer getTotalObjetos() {
        return totalObjetos;
    }

    public void setTotalObjetos(Integer totalObjetos) {
        this.totalObjetos = totalObjetos;
    }
    
    
}
