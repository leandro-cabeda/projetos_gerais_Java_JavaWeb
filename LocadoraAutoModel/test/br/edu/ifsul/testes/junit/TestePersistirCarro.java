package br.edu.ifsul.testes.junit;

import br.edu.ifsul.jpa.EntityManagerUtil;
import br.edu.ifsul.modelo.Fabricante;
import br.edu.ifsul.modelo.Grupo;
import br.edu.ifsul.modelo.Carro;
import br.edu.ifsul.modelo.Modelo;
import javax.persistence.EntityManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author jorge
 */
public class TestePersistirCarro {

    EntityManager em;

    public TestePersistirCarro() {
    }

    @Before
    public void setUp() {
        em = EntityManagerUtil.getEntityManager();
    }

    @After
    public void tearDown() {
        em.close();
    }

    @Test
    public void teste() {
        boolean exception = false;
        try {
            Carro obj1 = new Carro();
            obj1.setAnoModelo(2018);
            obj1.setAnoFabricacao(2018);
            obj1.setPlaca("III8958");
            obj1.setVersao("Trendline");
            obj1.setModelo(em.find(Modelo.class, 1));            
            Carro obj2 = new Carro();
            obj2.setAnoModelo(2018);
            obj2.setAnoFabricacao(2018);
            obj2.setPlaca("III4543");
            obj2.setVersao("Confortline");
            obj2.setModelo(em.find(Modelo.class, 2));               
            em.getTransaction().begin();
            em.persist(obj1);
            em.persist(obj2);
            em.getTransaction().commit();
        } catch (Exception e) {
            exception = true;
            e.printStackTrace();
        }
        /*
        O método abaixo verifica se o valor esperado (false) é
        igual ao valor do atributo exception, que vai indicar se ocorreu ou não erro.
        Se não ocorrer erro o teste passa. 
         */
        Assert.assertEquals(false, exception);
    }
}
