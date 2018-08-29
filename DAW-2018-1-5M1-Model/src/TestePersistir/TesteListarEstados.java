/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TestePersistir;

import br.edu.ifsul.jpa.EntitymanagerUtil;
import br.edu.ifsul.modelo.Estado;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Leandro
 */
public class TesteListarEstados {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        EntityManager em= EntitymanagerUtil.getEntityManager();
        List<Estado>lista = 
                em.createQuery("from Estado where pais.nome='Uruguai' order by nome").getResultList();
        
        for(Estado e: lista)
        {
            System.out.println("ID: " +e.getId()+ "  Nome: "+e.getNome()+ "  UF: "+e.getUF()+"  Pais: "+ e.getPais().getNome());
        }
    }
    
}
