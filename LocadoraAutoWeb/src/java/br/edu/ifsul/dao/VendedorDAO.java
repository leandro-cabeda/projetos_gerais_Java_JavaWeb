package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Vendedor;
import java.io.Serializable;

/**
 *
 * @author Prof. Me. Jorge Luis Boeira Bavaresco
 * @email jorge.bavaresco@passofundo.ifsul.edu.br
 * @organization IFSUL - Campus Passo Fundo
 */
public class VendedorDAO<TIPO> extends DAOGenerico<Vendedor> implements Serializable {

    public VendedorDAO(){
        super();
        // define a classe persistente
        classePersistente = Vendedor.class;
        // define o atributo padrão ao inicializar o DAO
        ordem = "nome";
    }
 
}
