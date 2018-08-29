/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PaisDao;

import DAO.DaoGenerico;
import br.edu.ifsul.modelo.Pais;
import java.io.Serializable;


/**
 *
 * @author Leandro
 */
public class PaisDao<T> extends DaoGenerico<Pais> implements Serializable {

    public PaisDao()
    {
        super();
        ClassePersistente=Pais.class;
        ordem="nome";
        
    }
}
