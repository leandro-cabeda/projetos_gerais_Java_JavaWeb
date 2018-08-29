package Daos;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import DAO.DaoGenerico;
import Tarefa_JPA_Modelo.Modelo;
import java.io.Serializable;


/**
 *
 * @author Leandro
 */
public class ModeloDao<T> extends DaoGenerico<Modelo>  implements Serializable {

    public ModeloDao()
    {
        super();
        // define a classe persistente
        ClassePersistente=Modelo.class;
        
        // define o atributo padrão ao inicializar da ordem
        ordem="nome";
    }
    
   
}
