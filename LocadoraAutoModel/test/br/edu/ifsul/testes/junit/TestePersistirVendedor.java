package br.edu.ifsul.testes.junit;

import br.edu.ifsul.jpa.EntityManagerUtil;
import br.edu.ifsul.modelo.Cidade;
import br.edu.ifsul.modelo.Vendedor;
import javax.persistence.EntityManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author jorge
 */
public class TestePersistirVendedor {

    EntityManager em;

    public TestePersistirVendedor() {
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
            Vendedor obj1 = new Vendedor();
            obj1.setNome("João");
            obj1.setCpf("962.411.990-21");
            obj1.setRg("8753829384");
            obj1.setAtivo(true);
            obj1.setNomeUsuario("joao");
            obj1.setSenha("123");
            Vendedor obj2 = new Vendedor();
            obj2.setNome("Jorge");
            obj2.setCpf("301.549.770-71");
            obj2.setRg("8753829384");
            obj2.setAtivo(true);
            obj2.setNomeUsuario("jorge");
            obj2.setSenha("123");
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
