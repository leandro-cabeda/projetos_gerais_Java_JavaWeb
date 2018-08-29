/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AcessorioDao;

import DAO.DaoGenerico;
import Tarefa_JPA_Modelo.Acessorio;
import java.io.Serializable;


/**
 *
 * @author Leandro
 */
public class AcessorioDao<T> extends DaoGenerico<Acessorio>  implements Serializable {
    
     public AcessorioDao()
    {
        super();
        // define a classe persistente
        ClassePersistente=Acessorio.class;
        
        // define o atributo padrão ao inicializar da ordem
        ordem="nome";
    }
}
