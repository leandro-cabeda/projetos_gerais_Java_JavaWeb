/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import JPA_Entity_Manager.EntitymanagerUtil;
import Tarefa_JPA_Modelo.Adicional;
import Tarefa_JPA_Modelo.Carro;
import Tarefa_JPA_Modelo.Cliente;
import Tarefa_JPA_Modelo.Locacao;
import Tarefa_JPA_Modelo.TipoAdicional;
import Tarefa_JPA_Modelo.Vendedor;
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
public class TestePersistirAdicional_Locacao {
    EntityManager em;
    
    public TestePersistirAdicional_Locacao() {
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
            
            Locacao l= new Locacao();
            l.setDataLocacao(new GregorianCalendar(2001,Calendar.MARCH,5));
            l.setDataDevolucao(new GregorianCalendar(2001,Calendar.MAY,15));
            l.setValorDiaria(120.00);
            l.setKmInicial(300);
            l.setKmFinal(1300);
            l.setValorTotal(8.400);
            l.setVendedor(em.find(Vendedor.class, 4));
            l.setCliente(em.find(Cliente.class, 2));
            l.setCarro(em.find(Carro.class, 1));
            
            Adicional a=new Adicional();
            a.setValor(293.30);
            a.setTipoadicional(em.find(TipoAdicional.class,1));
            a.setLocacao(em.find(Locacao.class,1));
            
            l.adicionarAdicional(a);
            
            
            em.getTransaction().begin();
            em.persist(l);
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
