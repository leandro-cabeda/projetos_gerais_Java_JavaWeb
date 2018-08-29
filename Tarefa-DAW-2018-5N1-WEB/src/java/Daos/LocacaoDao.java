package Daos;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import DAO.DaoGenerico;
import Tarefa_JPA_Modelo.Locacao;
import java.io.Serializable;


/**
 *
 * @author Leandro
 */
public class LocacaoDao<T> extends DaoGenerico<Locacao>  implements Serializable {

    public LocacaoDao()
    {
        super();
        // define a classe persistente
        ClassePersistente=Locacao.class;
        
        // define o atributo padrão ao inicializar da ordem
        ordem="dataLocacao";
    }
    
   
}
