/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import JPA_Entity_Manager.EntitymanagerUtil;
import Tarefa_JPA_Modelo.Cidade;
import javax.persistence.EntityManager;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Leandro
 */
public class TestePersistirCidade {
    EntityManager em;
    
    public TestePersistirCidade() {
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
            Cidade c=new Cidade();
            c.setNome("Curitiba");
            c.setEstado("PR");
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
