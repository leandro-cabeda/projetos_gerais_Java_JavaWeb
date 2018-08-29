package Daos;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import DAO.DaoGenerico;
import br.edu.ifsul.modelo.Cidade;
import java.io.Serializable;


/**
 *
 * @author Leandro
 */
public class CidadeDao<T> extends DaoGenerico<Cidade>  implements Serializable {

    public CidadeDao()
    {
        super();
        // define a classe persistente
        ClassePersistente=Cidade.class;
        
        // define o atributo padrão ao inicializar da ordem
        ordem="nome";
    }

}
