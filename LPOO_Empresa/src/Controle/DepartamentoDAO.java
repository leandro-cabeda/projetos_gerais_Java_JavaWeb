/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle;

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
 * @author Leandro
 */
public class DepartamentoDAO {
     Connection con = (new Conexao()).getConexao();
    PreparedStatement ps;
    ResultSet rs;
    
    public ArrayList<Departamento> buscar(){
        ArrayList<Departamento> lista = new ArrayList();
        try {
            String sql = "Select * from departamento, empresa where id_empresa = empresa_dep";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Departamento d = new Departamento();
                d.setNome(rs.getString("nome_depar"));
                d.setId(rs.getInt("id_depar"));
                
                Empresa e = new Empresa();
                e.setId(rs.getInt("id_empresa"));
                e.setRazaoSocial(rs.getString("razaosocial_empresa"));
                e.setNome(rs.getString("nome_empresa"));
                e.setCNPJ(rs.getString("cnpj_empresa"));
                
                d.setEmpresa(e);
                lista.add(d);
            }
            //System.out.println("Teste");
            
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
    
    public ArrayList<Departamento> buscardado(String nome){
        ArrayList<Departamento> lista = new ArrayList();
        try {
            String sql = "Select * from departamento,empresa where lower(nome_depar) like ? and empresa_dep=id_empresa";
            ps = con.prepareStatement(sql);
            ps.setString(1, "%"+nome.toLowerCase().trim()+"%");
            rs = ps.executeQuery();
            while(rs.next()){
                Departamento d = new Departamento();
                d.setNome(rs.getString("nome_depar"));
                d.setId(rs.getInt("id_depar"));
                
                Empresa e = new Empresa();
                e.setId(rs.getInt("id_empresa"));
                e.setRazaoSocial(rs.getString("razaosocial_empresa"));
                e.setNome(rs.getString("nome_empresa"));
                e.setCNPJ(rs.getString("cnpj_empresa"));
                
                d.setEmpresa(e);
                lista.add(d);
            }
            //System.out.println("Teste");
            
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
    
    public ArrayList<Departamento> buscarempresa(Empresa ob){
        ArrayList<Departamento> lista = new ArrayList();
        try {
            String sql = "Select * from departamento,empresa where nome_empresa like ? and empresa_dep=id_empresa";
            ps = con.prepareStatement(sql);
            ps.setString(1, "%"+ob.getNome()+"%");
            rs = ps.executeQuery();
            while(rs.next()){
                Departamento d = new Departamento();
                d.setNome(rs.getString("nome_depar"));
                d.setId(rs.getInt("id_depar"));
                
                Empresa e = new Empresa();
                e.setId(rs.getInt("id_empresa"));
                e.setRazaoSocial(rs.getString("razaosocial_empresa"));
                e.setNome(rs.getString("nome_empresa"));
                e.setCNPJ(rs.getString("cnpj_empresa"));
                
                d.setEmpresa(e);
                lista.add(d);
            }
            //System.out.println("Teste");
            
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
    
    public ArrayList<Departamento> buscadepemp(String nome,Empresa ob){
        ArrayList<Departamento> lista = new ArrayList();
        try {
            String sql = "Select * from departamento,empresa where lower(nome_depar) like ? and nome_empresa like ? and empresa_dep=id_empresa";
            ps = con.prepareStatement(sql);
            ps.setString(1, "%"+nome.toLowerCase().trim()+"%");
            ps.setString(2, "%"+ob.getNome()+"%");
            rs = ps.executeQuery();
            while(rs.next()){
                Departamento d = new Departamento();
                d.setNome(rs.getString("nome_depar"));
                d.setId(rs.getInt("id_depar"));
                
                Empresa e = new Empresa();
                e.setId(rs.getInt("id_empresa"));
                e.setRazaoSocial(rs.getString("razaosocial_empresa"));
                e.setNome(rs.getString("nome_empresa"));
                e.setCNPJ(rs.getString("cnpj_empresa"));
                
                d.setEmpresa(e);
                lista.add(d);
            }
            //System.out.println("Teste");
            
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
    
    public void adicionar(Departamento obj){
        String sql = "insert into departamento (nome_depar, empresa_dep)"
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
        String sql = "delete from departamento where id_depar = ?";
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
         String sql = "update departamento set nome_depar = ?, empresa_dep = ?  "
                 + "where id_depar = ?";
        try{
            ps = con.prepareStatement(sql);
            ps.setString(1, obj.getNome());
            ps.setInt(2, obj.getEmpresa().getId());
            ps.setInt(3, obj.getId());
            ps.executeUpdate();
        }catch(SQLException e){
            System.err.println("Erro ao editar dados do Departamento: "+e);
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
