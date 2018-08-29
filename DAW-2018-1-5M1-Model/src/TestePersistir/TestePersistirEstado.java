/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TestePersistir;

import br.edu.ifsul.modelo.Estado;
import br.edu.ifsul.modelo.Pais;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Leandro
 */
public class TestePersistirEstado {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // o parametro é o nome da unidade de persistencia do arquivo persistente.xml
        EntityManagerFactory emf=Persistence.createEntityManagerFactory("DAW-2018-1-5M1-ModelPU");
        EntityManager em= emf.createEntityManager();
        Estado e= new Estado();
        e.setNome("Florida");
        e.setUF("F");
        Pais pais=em.find(Pais.class, 2);
        e.setPais(pais);
        em.getTransaction().begin();
        em.persist(e);
        em.getTransaction().commit();
    }
    
}
