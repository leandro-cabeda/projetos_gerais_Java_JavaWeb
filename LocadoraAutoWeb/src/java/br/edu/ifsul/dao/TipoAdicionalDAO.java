package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.TipoAdicional;
import java.io.Serializable;

/**
 *
 * @author Prof. Me. Jorge Luis Boeira Bavaresco
 * @email jorge.bavaresco@passofundo.ifsul.edu.br
 * @organization IFSUL - Campus Passo Fundo
 */
public class TipoAdicionalDAO<TIPO> extends DAOGenerico<TipoAdicional> implements Serializable {

    public TipoAdicionalDAO(){
        super();
        // define a classe persistente
        classePersistente = TipoAdicional.class;
        // define o atributo padrão ao inicializar o DAO
        ordem = "nome";
    }
 
}
