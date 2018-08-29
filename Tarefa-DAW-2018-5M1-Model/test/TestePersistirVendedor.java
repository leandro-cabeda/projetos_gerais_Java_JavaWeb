/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import JPA_Entity_Manager.EntitymanagerUtil;
import Tarefa_JPA_Modelo.Vendedor;
import javax.persistence.EntityManager;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Leandro
 */
public class TestePersistirVendedor {
    EntityManager em;
    
    public TestePersistirVendedor() {
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
            Vendedor v=new Vendedor();
            v.setNome_usuario("Alberto.Santos");
            v.setNome("Alberto Floriano");
            v.setCpf("502.874.412-29");
            v.setRg("63908731");
            v.setSenha("12345");
            v.setAtivo(true);
            em.getTransaction().begin();
            em.persist(v);
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
