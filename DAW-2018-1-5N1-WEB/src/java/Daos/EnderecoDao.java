package Daos;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import DAO.DaoGenerico;
import br.edu.ifsul.modelo.Endereco;
import java.io.Serializable;


/**
 *
 * @author Leandro
 */
public class EnderecoDao<T> extends DaoGenerico<Endereco>  implements Serializable {

    public EnderecoDao()
    {
        super();
        // define a classe persistente
        ClassePersistente=Endereco.class;
        
        // define o atributo padrão ao inicializar da ordem
        ordem="nickname";
    }

}
