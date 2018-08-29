package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Carro;
import java.io.Serializable;

/**
 *
 * @author Prof. Me. Jorge Luis Boeira Bavaresco
 * @email jorge.bavaresco@passofundo.ifsul.edu.br
 * @organization IFSUL - Campus Passo Fundo
 */
public class CarroDAO<TIPO> extends DAOGenerico<Carro> implements Serializable {

    public CarroDAO(){
        super();
        // define a classe persistente
        classePersistente = Carro.class;
        // define o atributo padrão ao inicializar o DAO
        ordem = "placa";
    }
 
}
