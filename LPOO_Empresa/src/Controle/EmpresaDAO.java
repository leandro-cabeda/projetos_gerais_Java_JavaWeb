/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

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
public class EmpresaDAO {
    Connection con = (new Conexao()).getConexao();
    PreparedStatement ps;
    ResultSet rs;
    
    public ArrayList<Empresa> buscar(){
        ArrayList<Empresa> lista = new ArrayList();
        try {
            String sql = "Select * from empresa";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Empresa e = new Empresa();
                e.setId(rs.getInt("id_empresa"));
                e.setRazaoSocial(rs.getString("razaosocial_empresa"));
                e.setNome(rs.getString("nome_empresa"));
                e.setCNPJ(rs.getString("cnpj_empresa"));
                lista.add(e);
//                System.out.println("Nome: "+rs.getString("nome_emp"));
            }
//            System.out.println("Teste");
//            JOptionPane.showMessageDialog(null, lista);
        } catch (SQLException ex) {
            System.err.println("Erro ao buscar dados de Empresas\n"+ex);
        }finally{
            try {
                ps.close();
                rs.close();
                con.close();
            } catch (SQLException ex) {
                System.out.println("Erro: "+ex);
            }
        }
        
        return lista;
    }
    
    public ArrayList<Empresa> buscar(String nome){
        ArrayList<Empresa> lista = new ArrayList();
        try {
//            String sql = "Select * from empresa where nome_emp like '%"+nome+"%'";
            String sql = "Select * from empresa where lower(nome_empresa) like ?";
            
            ps = con.prepareStatement(sql);
            ps.setString(1, "%"+nome.toLowerCase().trim()+"%");
            rs = ps.executeQuery();
            while(rs.next()){
                Empresa e = new Empresa();
                e.setId(rs.getInt("id_empresa"));
                e.setRazaoSocial(rs.getString("razaosocial_empresa"));
                e.setNome(rs.getString("nome_empresa"));
                e.setCNPJ(rs.getString("cnpj_empresa"));
                lista.add(e);
//                System.out.println("Nome: "+rs.getString("nome_emp"));
            }
//            JOptionPane.showMessageDialog(null, lista);
        } catch (SQLException ex) {
            System.err.println("Erro ao buscar dados de Empresas\n"+ex);
        }finally{
            try {
                ps.close();
                rs.close();
                con.close();
            } catch (SQLException ex) {
                System.out.println("Erro: "+ex);
            }
        }
        return lista;
    }
    
    public void adicionar(Empresa obj){
        String sql = "insert into empresa (razaosocial_empresa,nome_empresa, cnpj_empresa)"
                + " values (?, ?, ?)";
        try{
            ps = con.prepareStatement(sql);
            ps.setString(1, obj.getRazaoSocial());
            ps.setString(2, obj.getNome());
            ps.setString(3, obj.getCNPJ());
            ps.executeUpdate();
        }catch(SQLException e){
            System.err.println("Erro ao inserir dados de Empresa: "+e);
        }finally{
            try {
                ps.close();
                con.close();
            } catch (SQLException ex) {
                System.out.println("Erro: "+ex);
            }
        }
        
    }
    
    public void excluir(Empresa obj){
        String sql = "delete from empresa where id_empresa = ?";
        try{
            ps = con.prepareStatement(sql);
            ps.setInt(1, obj.getId());
            ps.executeUpdate();
        }catch(SQLException e){
            System.err.println("Erro ao remover dados de Empresa: "+e);
        }finally{
            try {
                ps.close();
                con.close();
            } catch (SQLException ex) {
                System.out.println("Erro: "+ex);
            }
        }
    }
    
    public void editar(Empresa obj){
         String sql = "update empresa set razaosocial_empresa = ?, nome_empresa = ?, cnpj_empresa = ?"
                 + "where id_empresa = ?";
        try{
            ps = con.prepareStatement(sql);
            ps.setString(1, obj.getRazaoSocial());
            ps.setString(2, obj.getNome());
            ps.setString(3, obj.getCNPJ());
            ps.setInt(4, obj.getId());
            ps.executeUpdate();
        }catch(SQLException e){
            System.err.println("Erro ao editar dados de Empresa: "+e);
        }finally{
            try {
                ps.close();
                con.close();
            } catch (SQLException ex) {
                System.out.println("Erro: "+ex);
            }
        }
    }
}
