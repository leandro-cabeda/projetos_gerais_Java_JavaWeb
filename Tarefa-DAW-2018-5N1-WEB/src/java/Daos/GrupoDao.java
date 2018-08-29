package Daos;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import DAO.DaoGenerico;
import Tarefa_JPA_Modelo.Grupo;
import java.io.Serializable;


/**
 *
 * @author Leandro
 */
public class GrupoDao<T> extends DaoGenerico<Grupo>  implements Serializable {

    public GrupoDao()
    {
        super();
        // define a classe persistente
        ClassePersistente=Grupo.class;
        
        // define o atributo padrão ao inicializar da ordem
        ordem="nome";
    }
    
   
}
