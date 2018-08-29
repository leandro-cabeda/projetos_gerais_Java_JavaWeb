package br.edu.ifsul.testes.junit;

import br.edu.ifsul.jpa.EntityManagerUtil;
import br.edu.ifsul.modelo.Adicional;
import br.edu.ifsul.modelo.Carro;
import br.edu.ifsul.modelo.Cliente;
import br.edu.ifsul.modelo.Fabricante;
import br.edu.ifsul.modelo.Grupo;
import br.edu.ifsul.modelo.Locacao;
import br.edu.ifsul.modelo.Modelo;
import br.edu.ifsul.modelo.TipoAdicional;
import br.edu.ifsul.modelo.Vendedor;
import java.util.Calendar;
import javax.persistence.EntityManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author jorge
 */
public class TestePersistirLocacao {

    EntityManager em;

    public TestePersistirLocacao() {
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
            Locacao obj1 = new Locacao();
            obj1.setCliente(em.find(Cliente.class, 2));
            obj1.setVendedor(em.find(Vendedor.class, 4));
            obj1.setDataLocacao(Calendar.getInstance());
            obj1.setDataDevolucao(Calendar.getInstance());
            obj1.setValorDiaria(250.00);
            obj1.setValorTotal(250.00);
            obj1.setKmInicial(50000);
            obj1.setKmFinal(60000);
            obj1.setCarro(em.find(Carro.class, 2));
            Adicional adi = new Adicional();
            adi.setTipoadicional(em.find(TipoAdicional.class, 1));
            adi.setValor(150.00);
            obj1.adicionarAdicional(adi);
            em.getTransaction().begin();
            em.persist(obj1);
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
