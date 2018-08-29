package Daos;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import DAO.DaoGenerico;
import br.edu.ifsul.modelo.PessoaFisica;
import java.io.Serializable;
import javax.persistence.Query;


/**
 *
 * @author Leandro
 */
public class PessoaFisicaDao<T> extends DaoGenerico<PessoaFisica>  implements Serializable {

    public PessoaFisicaDao()
    {
        super();
        // define a classe persistente
        ClassePersistente=PessoaFisica.class;
        
        // define o atributo padrão ao inicializar da ordem
        ordem="nome";
    }
    
    public boolean login(String usuario, String senha)
    {
        Query query=em.createQuery("from PessoaFisica where upper(nomeUsuario)=:usuario and upper(senha)=:senha");
        query.setParameter("usuario",usuario.toUpperCase());
        query.setParameter("senha",senha.toUpperCase());
        
        if(!query.getResultList().isEmpty())
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    public PessoaFisica lozalicaPorNomeUsuario(String usuario)
    {
        Query query=em.createQuery("from PessoaFisica where upper(nomeUsuario)=:usuario");
        
        query.setParameter("usuario",usuario.toUpperCase());
        
        return (PessoaFisica)query.getSingleResult();
    }

}
