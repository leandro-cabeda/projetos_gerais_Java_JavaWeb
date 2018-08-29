package br.edu.ifsul.testes.junit;

import br.edu.ifsul.jpa.EntityManagerUtil;
import br.edu.ifsul.modelo.TipoAdicional;
import javax.persistence.EntityManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author jorge
 */
public class TestePersistirTipoAdicional {

    EntityManager em;

    public TestePersistirTipoAdicional() {
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
            TipoAdicional obj1 = new TipoAdicional();
            obj1.setNome("Seguro contra terceiros");
            obj1.setValor(150.00);
            TipoAdicional obj2 = new TipoAdicional();
            obj2.setNome("Cadeira de criança");
            obj2.setValor(50.00);
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
