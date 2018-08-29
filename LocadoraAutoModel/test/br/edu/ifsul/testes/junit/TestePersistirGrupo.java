package br.edu.ifsul.testes.junit;

import br.edu.ifsul.jpa.EntityManagerUtil;
import br.edu.ifsul.modelo.Acessorios;
import br.edu.ifsul.modelo.Grupo;
import javax.persistence.EntityManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author jorge
 */
public class TestePersistirGrupo {

    EntityManager em;

    public TestePersistirGrupo() {
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
            Grupo obj1 = new Grupo();
            obj1.setNome("Carro Popular");
            obj1.setValor(130.00);
            Grupo obj2 = new Grupo();
            obj2.setNome("Carro Premium");
            obj2.setValor(250.00);
            obj2.getAcessorios().add(em.find(Acessorios.class, 1));
            obj2.getAcessorios().add(em.find(Acessorios.class, 2));
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
