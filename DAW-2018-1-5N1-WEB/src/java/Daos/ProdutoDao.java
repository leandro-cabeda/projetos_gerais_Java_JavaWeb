package Daos;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import DAO.DaoGenerico;
import br.edu.ifsul.modelo.Produto;
import java.io.Serializable;


/**
 *
 * @author Leandro
 */
public class ProdutoDao<T> extends DaoGenerico<Produto>  implements Serializable {

    public ProdutoDao()
    {
        super();
        // define a classe persistente
        ClassePersistente=Produto.class;
        
        // define o atributo padrão ao inicializar da ordem
        ordem="nome";
    }

}
