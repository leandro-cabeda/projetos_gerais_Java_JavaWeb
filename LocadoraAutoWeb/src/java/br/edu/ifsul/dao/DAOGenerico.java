package br.edu.ifsul.dao;

import br.edu.ifsul.jpa.EntityManagerUtil;
import br.edu.ifsul.util.Util;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Prof. Me. Jorge Luis Boeira Bavaresco
 * @email jorge.bavaresco@passofundo.ifsul.edu.br
 * @organization IFSUL - Campus Passo Fundo
 */
public class DAOGenerico<TIPO> {

    // lista que vai trazer a consulta paginada e filtrada
    private List<TIPO> listaObjetos;
    // lista que vai retornar todos os objetos
    private List<TIPO> listaTodos;
    protected EntityManager em;
    // atributo que armazena a classe que o DAO estará utilizando
    protected Class classePersistente;
    // atributo que vai armazenar mensagens, de erro ou sucesso
    protected String mensagem;
    // atributo que armazena a ordem da consulta
    protected String ordem = "id";
    // atributo que armazena o filtro utilizado na consulta
    protected String filtro = "";
    // atributo que armazena a quantidade maxima de objetos na consulta
    protected Integer maximoObjetos = 2;
    // atributo que armazena a pagina em que a consulta está
    protected Integer posicaoAtual = 0;
    // atributo que armazena o total de objetos daquela classe no banco
    protected Integer totalObjetos = 0;

    public DAOGenerico() {
        em = EntityManagerUtil.getEntityManager();
    }

    public List<TIPO> getListaObjetos() {
        String jpql = "from " + classePersistente.getSimpleName();
        // limpar o filtro para evitar injeção de SQL
        filtro = filtro.replaceAll("[';-]", "");
        String where = "";
        if (filtro.length() > 0) {
            if (ordem.equals("id")) {
                try {
                    // testo se é um inteiro para não dar erro
                    Integer.parseInt(filtro);
                    where += " where " + ordem + " = '" + filtro + "' ";
                } catch (Exception e) {
                }
            } else {
                where += " where upper(" + ordem + ") like '"
                        + filtro.toUpperCase() + "%' ";
            }
        }
        jpql += where;
        jpql += " order by " + ordem;
        System.out.println("JPQL: " + jpql); // exibindo no console a consulta   
        // obtendo o total de registros daquela classe no banco
        totalObjetos = em.createQuery("from " + classePersistente.getSimpleName()
                + where + " order by " + ordem).
                getResultList().size();
        return em.createQuery(jpql).
                setFirstResult(posicaoAtual).
                setMaxResults(maximoObjetos).getResultList();
    }

    public List<TIPO> getListaTodos() {
        String jpql = "from " + classePersistente.getSimpleName() + " order by " + ordem;
        return em.createQuery(jpql).getResultList();
    }

    public void primeiro() {
        posicaoAtual = 0;
    }

    public void anterior() {
        posicaoAtual -= maximoObjetos;
        if (posicaoAtual < 0) {
            posicaoAtual = 0;
        }
    }

    public void proximo() {
        if (posicaoAtual + maximoObjetos < totalObjetos) {
            posicaoAtual += maximoObjetos;
        }
    }

    public void ultimo() {
        int resto = totalObjetos % maximoObjetos;
        if (resto > 0) {
            posicaoAtual = totalObjetos - resto;
        } else {
            posicaoAtual = totalObjetos - maximoObjetos;
        }
    }

    public String getMensagemNavegacao() {
        int ate = posicaoAtual + maximoObjetos;
        if (ate > totalObjetos) {
            ate = totalObjetos;
        }
        return "Listando de " + (posicaoAtual + 1)
                + " até " + ate + " de " + totalObjetos + " registros";
    }

    public void rollback() {
        if (em.getTransaction().isActive() == false) {
            em.getTransaction().begin();
        }
        em.getTransaction().rollback();
    }

    public boolean persist(TIPO obj) {
        try {
            em.getTransaction().begin();
            em.persist(obj);
            em.getTransaction().commit();
            mensagem = "Objeto persistido com sucesso!";
            return true;
        } catch (Exception e) {
            rollback();
            mensagem = "Erro ao persistir objeto: "
                    + Util.getMensagemErro(e);
            return false;
        }
    }

    public boolean merge(TIPO obj) {
        try {
            em.getTransaction().begin();
            em.merge(obj);
            em.getTransaction().commit();
            mensagem = "Objeto atualizado com sucesso!";
            return true;
        } catch (Exception e) {
            rollback();
            mensagem = "Erro ao atualizar objeto: "
                    + Util.getMensagemErro(e);
            return false;
        }
    }

    public boolean remover(TIPO obj) {
        try {
            em.getTransaction().begin();
            em.remove(obj);
            em.getTransaction().commit();
            mensagem = "Objeto removido com sucesso!";
            return true;
        } catch (Exception e) {
            rollback();
            mensagem = "Erro ao remover objeto: "
                    + Util.getMensagemErro(e);
            return false;
        }
    }

    public TIPO localizar(Object id) {
        rollback();
        TIPO obj = (TIPO) em.find(classePersistente, id);
        return obj;
    }

    public void setListaObjetos(List<TIPO> listaObjetos) {
        this.listaObjetos = listaObjetos;
    }

    public void setListaTodos(List<TIPO> listaTodos) {
        this.listaTodos = listaTodos;
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public Class getClassePersistente() {
        return classePersistente;
    }

    public void setClassePersistente(Class classePersistente) {
        this.classePersistente = classePersistente;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
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
