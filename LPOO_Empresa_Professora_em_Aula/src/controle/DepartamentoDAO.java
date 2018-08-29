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
import modelo.Departamento;
import modelo.Empresa;

/**
 *
 * @author vanes
 */
public class DepartamentoDAO {
    Connection con = (new Conexao()).getConexao();
    PreparedStatement ps;
    ResultSet rs;
    
    public ArrayList<Departamento> buscar(){
        ArrayList<Departamento> lista = new ArrayList();
        try {
            String sql = "Select * from departamento, empresa where id_emp = empresa_dep";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Departamento d = new Departamento();
                d.setNome(rs.getString("nome_dep"));
                d.setId(rs.getInt("id_dep"));
                
                Empresa e = new Empresa();
                e.setNome(rs.getString("nome_emp"));
                e.setCNPJ(rs.getString("cnpj_emp"));
                e.setRazaoSocial(rs.getString("razao_social_emp"));
                e.setId(rs.getInt("id_emp"));
                
                d.setEmpresa(e);
                lista.add(d);
            }
            
            
        } catch (SQLException ex) {
            System.err.println("Erro ao buscar dados de Departamentos\n"+ex);
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
    
    public ArrayList<Departamento> buscar(String n){
        ArrayList<Departamento> lista = new ArrayList();
        try {
            String sql = "Select * from departamento, empresa where id_emp = empresa_dep and "
                    + "lower(nome_dep) like ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, "%"+n+"%");
            rs = ps.executeQuery();
            while(rs.next()){
                Departamento d = new Departamento();
                d.setNome(rs.getString("nome_dep"));
                d.setId(rs.getInt("id_dep"));
                
                Empresa e = new Empresa();
                e.setNome(rs.getString("nome_emp"));
                e.setCNPJ(rs.getString("cnpj_emp"));
                e.setRazaoSocial(rs.getString("razao_social_emp"));
                e.setId(rs.getInt("id_emp"));
                
                d.setEmpresa(e);
                lista.add(d);
            }
            
            
        } catch (SQLException ex) {
            System.err.println("Erro ao buscar dados de Departamentos por nome\n"+ex);
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
    
    public ArrayList<Departamento> buscar(Empresa obj){
        ArrayList<Departamento> lista = new ArrayList();
        try {
            String sql = "Select * from departamento, empresa where id_emp = empresa_dep and "
                    + "empresa_dep = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, obj.getId());
            rs = ps.executeQuery();
            while(rs.next()){
                Departamento d = new Departamento();
                d.setNome(rs.getString("nome_dep"));
                d.setId(rs.getInt("id_dep"));
                
                Empresa e = new Empresa();
                e.setNome(rs.getString("nome_emp"));
                e.setCNPJ(rs.getString("cnpj_emp"));
                e.setRazaoSocial(rs.getString("razao_social_emp"));
                e.setId(rs.getInt("id_emp"));
                
                d.setEmpresa(e);
                lista.add(d);
            }
            
            
        } catch (SQLException ex) {
            System.err.println("Erro ao buscar dados de Departamentos por Empresa\n"+ex);
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
    
    public ArrayList<Departamento> buscar(Empresa obj, String n){
        ArrayList<Departamento> lista = new ArrayList();
        try {
            String sql = "Select * from departamento, empresa where id_emp = empresa_dep and "
                    + "lower(nome_dep) like ? and "
                    + "empresa_dep = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, "%"+n+"%");
            ps.setInt(2, obj.getId());
            rs = ps.executeQuery();
            while(rs.next()){
                Departamento d = new Departamento();
                d.setNome(rs.getString("nome_dep"));
                d.setId(rs.getInt("id_dep"));
                
                Empresa e = new Empresa();
                e.setNome(rs.getString("nome_emp"));
                e.setCNPJ(rs.getString("cnpj_emp"));
                e.setRazaoSocial(rs.getString("razao_social_emp"));
                e.setId(rs.getInt("id_emp"));
                
                d.setEmpresa(e);
                lista.add(d);
            }
            
            
        } catch (SQLException ex) {
            System.err.println("Erro ao buscar dados de Departamentos por nome\n"+ex);
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
    
    public void adicionar(Departamento obj){
        String sql = "insert into departamento (nome_dep, empresa_dep)"
                + " values (?, ?)";
        try{
            ps = con.prepareStatement(sql);
            ps.setString(1, obj.getNome());
            ps.setInt(2, obj.getEmpresa().getId());
            ps.executeUpdate();
        }catch(SQLException e){
            System.err.println("Erro ao inserir dados de Departamento: "+e);
        }finally{
            try {
                ps.close();
                con.close();
            } catch (SQLException ex) {
                System.out.println("Erro: "+ex);
            }
        }
        
    }
    
    public void excluir(Departamento obj){
        String sql = "delete from departamento where id_dep = ?";
        try{
            ps = con.prepareStatement(sql);
            ps.setInt(1, obj.getId());
            ps.executeUpdate();
        }catch(SQLException e){
            System.err.println("Erro ao remover dados de Departamento: "+e);
        }finally{
            try {
                ps.close();
                con.close();
            } catch (SQLException ex) {
                System.out.println("Erro: "+ex);
            }
        }
    }
    
    public void editar(Departamento obj){
        String sql = "update departamento set nome_dep = ?, empresa_dep = ? "
                + "where id_dep = ?";
        try{
            ps = con.prepareStatement(sql);
            ps.setString(1, obj.getNome());
            ps.setInt(2, obj.getEmpresa().getId());
            ps.setInt(3, obj.getId());
            ps.executeUpdate();
        }catch(SQLException e){
            System.err.println("Erro ao editar dados de Departamento: "+e);
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
