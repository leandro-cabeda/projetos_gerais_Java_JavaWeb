/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.testes.junit;

import br.edu.ifsul.jpa.EntitymanagerUtil;
import br.edu.ifsul.modelo.PessoaFisica;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.persistence.EntityManager;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Leandro
 */
public class TestePersistirPessoa_Fisica {
    EntityManager em;
    
    public TestePersistirPessoa_Fisica() {
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
            PessoaFisica pf=new PessoaFisica();
            
            pf.setNome("Leandro");
            pf.setCpf("205.361.872-82");
            pf.setNascimento(new GregorianCalendar(1988,Calendar.APRIL,3));
            pf.setRg("1099849463");
            pf.setTelefone("54-999337135");
            pf.setEmail("leandro.cabeda@hotmail.com");
            em.getTransaction().begin();
            em.persist(pf);
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
