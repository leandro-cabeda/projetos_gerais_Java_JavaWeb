/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author vanes
 */
public class Conexao {
    public Conexao() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            System.err.println("Biblioteca não adicionada");
        }
    }

    public Connection getConexao() {
        Connection con = null;
        String url = "jdbc:postgresql://localhost:5432/LPOO_Empresa";
        String user = "postgres";
        String senha = "vanessa";
        try {
            con = DriverManager.getConnection(url, user, senha);
//            System.out.println("Conexão realizada com sucesso");
        } catch (SQLException ex) {
            System.err.println("Erro ao conectar ao Banco:\n" + ex);
        }
        return con;
    }
}
