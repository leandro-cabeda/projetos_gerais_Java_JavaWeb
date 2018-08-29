/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TestePersistir;

import br.edu.ifsul.jpa.EntitymanagerUtil;
import br.edu.ifsul.modelo.Estado;
import javax.persistence.EntityManager;

/**
 *
 * @author Leandro
 */
public class TesteRemoverEstado {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        EntityManager em= EntitymanagerUtil.getEntityManager();
        Estado e=em.find(Estado.class, 2);
        
        em.getTransaction().begin();
        em.remove(e); // slimilar a um delete do banco
        em.getTransaction().commit();
        
    }
    
}
