/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.testes.junit;

import br.edu.ifsul.jpa.EntitymanagerUtil;
import br.edu.ifsul.modelo.Cidade;
import br.edu.ifsul.modelo.Endereco;
import br.edu.ifsul.modelo.PessoaFisica;
import javax.persistence.EntityManager;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Leandro
 */
public class TestePersistirEnderecos {
    EntityManager em;
    
    public TestePersistirEnderecos() {
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
            PessoaFisica pf=em.find(PessoaFisica.class,2);
            Endereco e= new Endereco();
            e.setBairro("Centro");
            e.setCep("99010-290");
            e.setCidade(em.find(Cidade.class,1));
            e.setComplemento("AP 500");
            e.setNickname("ATS");
            e.setEndereco("Lava Pés");
            e.setNumero("2398");
            e.setReferencia("Perto do mercado");
            pf.adicionarEndereco(e);
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
