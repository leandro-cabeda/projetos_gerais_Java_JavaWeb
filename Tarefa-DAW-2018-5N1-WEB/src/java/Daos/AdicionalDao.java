package Daos;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import DAO.DaoGenerico;
import Tarefa_JPA_Modelo.Adicional;
import java.io.Serializable;


/**
 *
 * @author Leandro
 */
public class AdicionalDao<T> extends DaoGenerico<Adicional>  implements Serializable {

    public AdicionalDao()
    {
        super();
        // define a classe persistente
        ClassePersistente=Adicional.class;
        
        // define o atributo padrão ao inicializar da ordem
        ordem="valor";
    }
    
   
}
