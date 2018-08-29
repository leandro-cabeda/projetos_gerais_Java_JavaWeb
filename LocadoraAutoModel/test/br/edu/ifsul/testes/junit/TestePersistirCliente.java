package br.edu.ifsul.testes.junit;

import br.edu.ifsul.jpa.EntityManagerUtil;
import br.edu.ifsul.modelo.Cidade;
import br.edu.ifsul.modelo.Cliente;
import javax.persistence.EntityManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author jorge
 */
public class TestePersistirCliente {

    EntityManager em;

    public TestePersistirCliente() {
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
            Cliente obj1 = new Cliente();
            obj1.setNome("João");
            obj1.setCpf("450.904.810-67");
            obj1.setRg("8753829384");
            obj1.setTelefone("(54)9987-0987");
            obj1.setEndereco("Rua da bica 345");
            obj1.setBairro("Centro");
            obj1.setCep("95300000");
            obj1.setCidade(em.find(Cidade.class, 1));
            Cliente obj2 = new Cliente();
            obj2.setNome("Jorge");
            obj2.setCpf("439.744.880-90");
            obj2.setRg("8753829384");
            obj2.setTelefone("(54)9987-0987");
            obj2.setEndereco("Rua da bica 345");
            obj2.setBairro("Centro");
            obj2.setCep("95300000");
            obj2.setCidade(em.find(Cidade.class, 2));
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
