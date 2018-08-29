package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Cidade;
import java.io.Serializable;

/**
 *
 * @author Prof. Me. Jorge Luis Boeira Bavaresco
 * @email jorge.bavaresco@passofundo.ifsul.edu.br
 * @organization IFSUL - Campus Passo Fundo
 */
public class CidadeDAO<TIPO> extends DAOGenerico<Cidade> implements Serializable {

    public CidadeDAO(){
        super();
        // define a classe persistente
        classePersistente = Cidade.class;
        // define o atributo padrão ao inicializar o DAO
        ordem = "nome";
    }
 
}
