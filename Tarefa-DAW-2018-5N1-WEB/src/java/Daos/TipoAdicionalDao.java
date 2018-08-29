package Daos;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import DAO.DaoGenerico;
import Tarefa_JPA_Modelo.TipoAdicional;
import java.io.Serializable;


/**
 *
 * @author Leandro
 */
public class TipoAdicionalDao<T> extends DaoGenerico<TipoAdicional>  implements Serializable {

    public TipoAdicionalDao()
    {
        super();
        // define a classe persistente
        ClassePersistente=TipoAdicional.class;
        
        // define o atributo padrão ao inicializar da ordem
        ordem="nome";
    }
    
   
}
