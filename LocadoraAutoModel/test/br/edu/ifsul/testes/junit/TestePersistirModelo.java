package br.edu.ifsul.testes.junit;

import br.edu.ifsul.jpa.EntityManagerUtil;
import br.edu.ifsul.modelo.Fabricante;
import br.edu.ifsul.modelo.Grupo;
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
public class TestePersistirModelo {

    EntityManager em;

    public TestePersistirModelo() {
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
            Modelo obj1 = new Modelo();
            obj1.setNome("Gol");
            obj1.setGrupo(em.find(Grupo.class, 1));
            obj1.setFabricante(em.find(Fabricante.class, 1));
            Modelo obj2 = new Modelo();
            obj2.setNome("Jetta");
            obj2.setGrupo(em.find(Grupo.class, 2));
            obj2.setFabricante(em.find(Fabricante.class, 1));            
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
