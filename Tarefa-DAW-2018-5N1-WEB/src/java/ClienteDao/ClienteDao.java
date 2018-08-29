/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClienteDao;

import DAO.DaoGenerico;
import Tarefa_JPA_Modelo.Cliente;
import java.io.Serializable;

/**
 *
 * @author Leandro
 */
public class ClienteDao<T> extends DaoGenerico<Cliente>  implements Serializable {
    
    public ClienteDao()
    {
        super();
        // define a classe persistente
        ClassePersistente=Cliente.class;
        
        // define o atributo padrão ao inicializar da ordem
        ordem="nome";
    }
    
    
}
