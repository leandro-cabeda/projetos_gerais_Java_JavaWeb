/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.testes.junit;

import br.edu.ifsul.jpa.EntitymanagerUtil;
import br.edu.ifsul.modelo.Produto;
import br.edu.ifsul.modelo.Venda;
import br.edu.ifsul.modelo.VendaItens;
import javax.persistence.EntityManager;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Leandro
 */
public class TestePersistirVenda_Item {
    EntityManager em;
    
    public TestePersistirVenda_Item() {
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
            Venda v=em.find(Venda.class, 4);
            Produto p=em.find(Produto.class, 1);
            VendaItens vi=new VendaItens();
            
            vi.setProduto(p);
            vi.setQuantidade(5.0);
            vi.setValorUnitario(p.getPreco());
            vi.setValorTotal(vi.getQuantidade()* vi.getValorUnitario());
            v.adicionarItem(vi);
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
