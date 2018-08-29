/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import JPA_Entity_Manager.EntitymanagerUtil;
import Tarefa_JPA_Modelo.Carro;
import Tarefa_JPA_Modelo.Modelo;
import javax.persistence.EntityManager;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Leandro
 */
public class TestePersistirCarro {
    EntityManager em;
    
    public TestePersistirCarro() {
    }
    
    @Before
    public void setUp() {
        em=EntitymanagerUtil.getEntityManager();
    }
    
    @After
    public void tearDown() {
        em.close();
    }
    
    @Test
    public void teste()
    {
        boolean exception=false;
        
        try{
            Carro c=new Carro();
            c.setPlaca("NHB 7463");
            c.setAnofabricacao(1952);
            c.setAnomodelo(2003);
            c.setVersao("REVOLUTION");
            c.setModelo(em.find(Modelo.class, 2));
            em.getTransaction().begin();
            em.persist(c);
            em.getTransaction().commit();
        }catch(Exception e)
        {
            exception = true;
            e.printStackTrace();
        }
        
        // o método abaixo verifica se o valor  esperado(false)é igual ao valor doa tributo exception 
        // que vai indifcar se ocorreu ou não erro, se não ocorrer erro o teste passa
        
        Assert.assertEquals(false, exception);
    }
}
