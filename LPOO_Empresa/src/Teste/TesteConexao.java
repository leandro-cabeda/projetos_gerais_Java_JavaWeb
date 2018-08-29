/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Teste;

import Conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelo.Empresa;

/**
 *
 * @author vanes
 */
public class TesteConexao {
    public static void main(String[] args) {

        Connection con = (new Conexao()).getConexao();
        ArrayList<Empresa> lista = new ArrayList();
        try {
            String sql = "Select * from empresa";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Empresa e = new Empresa();
                e.setNome(rs.getString("nome_emp"));
                e.setCNPJ(rs.getString("cnpj_emp"));
                lista.add(e);
//                System.out.println("Nome: "+rs.getString("nome_emp"));
            }
            JOptionPane.showMessageDialog(null, lista);
        } catch (SQLException ex) {
            System.err.println("Erro ao buscar dados de Empresas\n"+ex);
        }
    }
}
