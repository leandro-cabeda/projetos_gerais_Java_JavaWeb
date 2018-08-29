/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EntityManagerUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Leandro
 */
public class EntityManagerUtil {
    private static EntityManagerFactory emf=null;
    private static EntityManager em=null;
    
    // Implementação do padrão de projeto singleton
    public static EntityManager getEntityManager()
    {
        if(emf==null)
        {
            emf=Persistence.createEntityManagerFactory("Tarefa_JPA_PU");
        }
        if(em==null)
        {
            em=emf.createEntityManager();
        }
        
        return em;
    }
    
}
