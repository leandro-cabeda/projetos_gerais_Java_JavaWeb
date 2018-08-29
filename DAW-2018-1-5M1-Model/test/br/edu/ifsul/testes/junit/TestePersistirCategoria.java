/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.testes.junit;

import br.edu.ifsul.jpa.EntitymanagerUtil;
import br.edu.ifsul.modelo.Categoria;
import javax.persistence.EntityManager;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Leandro
 */
public class TestePersistirCategoria {
    EntityManager em;
    
    public TestePersistirCategoria() {
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
            Categoria c=new Categoria();
            c.setNome("Eltronica");
            Categoria c2=new Categoria();
            c2.setNome("Informática");
            Categoria c3=new Categoria();
            c3.setNome("Eletrodomestico");
            
            em.getTransaction().begin();
            em.persist(c);
            em.persist(c2);
            em.persist(c3);
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
