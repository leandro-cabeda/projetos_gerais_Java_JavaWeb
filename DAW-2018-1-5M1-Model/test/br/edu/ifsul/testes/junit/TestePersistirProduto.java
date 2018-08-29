/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.testes.junit;

import br.edu.ifsul.jpa.EntitymanagerUtil;
import br.edu.ifsul.modelo.Categoria;
import br.edu.ifsul.modelo.Marca;
import br.edu.ifsul.modelo.PessoaFisica;
import br.edu.ifsul.modelo.Produto;
import javax.persistence.EntityManager;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Leandro
 */
public class TestePersistirProduto {
    EntityManager em;
    
    public TestePersistirProduto() {
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
            Categoria c= new Categoria();
            c.setNome("Computadores");
            
            Categoria c2= new Categoria();
            c2.setNome("Perifericos");
            
            Categoria c3= new Categoria();
            c3.setNome("Monitores");
            
            Categoria c4= new Categoria();
            c4.setNome("Armazenamento");
            
            Marca m= new Marca();
            m.setNome("Samsung");
            
            Marca m2=new Marca();
            m2.setNome("Dell");
            
            Marca m3=new Marca();
            m3.setNome("Multilaser");
            
            Marca m4=new Marca();
            m4.setNome("HP");
            
            Produto p1= new Produto();
            p1.setNome("Mouse Laser");
            p1.setDescricao("Mouse show");
            p1.setPreco(130.00);
            p1.setQuantidadeEstoque(20.0);
            p1.setCategoria(c2);
            p1.setMarca(m3);

            Produto p2= new Produto();
            p2.setNome("Impressora HP");
            p2.setDescricao("Impressora Laser");
            p2.setPreco(180.00);
            p2.setQuantidadeEstoque(3.0);
            p2.setCategoria(c2);
            p2.setMarca(m4);

            Produto p3= new Produto();
            p3.setNome("HD 5TB");
            p3.setDescricao("HD Externo 5TB");
            p3.setPreco(450.00);
            p3.setQuantidadeEstoque(7.0);
            p3.setCategoria(c4);
            p3.setMarca(m);
            
            Produto p4= new Produto();
            p4.setNome("Notebook");
            p4.setDescricao("Notebook DELL");
            p4.setPreco(1250.00);
            p4.setQuantidadeEstoque(15.0);
            p4.setCategoria(c);
            p4.setMarca(m2);
            
            
            Produto p5= new Produto();
            p5.setNome("LED");
            p5.setDescricao("LED 70 polegadas");
            p5.setPreco(3500.00);
            p5.setQuantidadeEstoque(5.0);
            p5.setCategoria(c3);
            p5.setMarca(m);
            
            
            em.getTransaction().begin();
            em.persist(c);
            em.persist(c2);
            em.persist(c3);
            em.persist(c4);
            em.persist(m);
            em.persist(m2);
            em.persist(m3);
            em.persist(m4);
            em.persist(p1);
            em.persist(p2);
            em.persist(p3);
            em.persist(p4);
            em.persist(p5);
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
