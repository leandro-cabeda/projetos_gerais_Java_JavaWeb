package Daos;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import DAO.DaoGenerico;
import Tarefa_JPA_Modelo.Vendedor;
import java.io.Serializable;
import javax.persistence.Query;


/**
 *
 * @author Leandro
 */
public class VendedorDao<T> extends DaoGenerico<Vendedor>  implements Serializable {

    public VendedorDao()
    {
        super();
        // define a classe persistente
        ClassePersistente=Vendedor.class;
        
        // define o atributo padrão ao inicializar da ordem
        ordem="nome_usuario";
    }
    
    public boolean login(String usuario, String senha)
    {
        Query query=em.createQuery("from Vendedor where upper(nome_usuario)=:usuario and upper(senha)=:senha");
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
    
    public Vendedor lozalicaPorNomeUsuario(String usuario)
    {
        Query query=em.createQuery("from Vendedor where upper(nome_usuario)=:usuario");
        
        query.setParameter("usuario",usuario.toUpperCase());
        
        return (Vendedor)query.getSingleResult();
    }

    
   
}
