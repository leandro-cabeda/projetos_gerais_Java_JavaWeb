/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.testes.junit;

import br.edu.ifsul.jpa.EntitymanagerUtil;
import br.edu.ifsul.modelo.Usuario;
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
public class TestePersistirUsuario {
    EntityManager em;
    
    public TestePersistirUsuario() {
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
            Usuario u=new Usuario();
            u.setNome("Leandro");
            u.setCpf("587.477.355-03");
            u.setAtivo(true);
            u.setNomeUsuario("Leo.cr");
            u.setEmail("leao.@hotmail.com");
            u.setNascimento(new GregorianCalendar(2015,Calendar.MARCH,3));
            u.setRg("99983747");
            u.setTelefone("54-99839833");
            u.setSenha("12335");
            em.getTransaction().begin();
            em.persist(u);
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
