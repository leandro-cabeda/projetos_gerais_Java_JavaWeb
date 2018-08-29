package EstadoDao;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import DAO.DaoGenerico;
import br.edu.ifsul.modelo.Estado;
import java.io.Serializable;


/**
 *
 * @author Leandro
 */
public class EstadoDao<T> extends DaoGenerico<Estado>  implements Serializable {

    public EstadoDao()
    {
        super();
        // define a classe persistente
        ClassePersistente=Estado.class;
        
        // define o atributo padrão ao inicializar da ordem
        ordem="nome";
    }

}
