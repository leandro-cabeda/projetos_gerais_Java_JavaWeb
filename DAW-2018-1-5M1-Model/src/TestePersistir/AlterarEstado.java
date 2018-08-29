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
public class AlterarEstado {

    /**
     * @param args the command line arguments
     */
    // o método find recebe dois parametros, a classe do objeto que será recuperado do banco e o id desse objeto
    public static void main(String[] args) {
       EntityManager em= EntitymanagerUtil.getEntityManager();
       Estado e =em.find(Estado.class, 2);
       
       e.setNome("Santa Catarina");
       e.setUF("SC");
       em.getTransaction().begin();
       em.merge(e); //similar a um update
       em.getTransaction().commit();
    }
    
}
