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
import modelo.Funcionario;

/**
 *
 * @author Leandro
 */
public class FuncionarioDAO {
     Connection con = (new Conexao()).getConexao();
    PreparedStatement ps;
    ResultSet rs;

    
    public ArrayList<Funcionario> buscar(){
        ArrayList<Funcionario> lista = new ArrayList();
        try {
            String sql = "Select * from departamento, empresa,funcionario where id_empresa = empresa_dep and dep_funcionario=id_depar";
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
                
                Funcionario f=new Funcionario();
                f.setId(rs.getInt("id_funcionario"));
                f.setHoras(rs.getDouble("horas_funcionario"));
                f.setSalarioHora(rs.getDouble("salariohora_funcionario"));
                f.setNome(rs.getString("nome_funcionario"));
                f.setSexo(rs.getString("sexo_funcionario")); //charAt(0), ele só manda 1 caracter pro sexo, usando char
                f.setAltura(rs.getDouble("altura_funcionario"));
                f.setPeso(rs.getDouble("peso_funcionario"));
                f.setNasc((java.util.Date)rs.getDate("dtnasc_funcionario"));
                
                d.setEmpresa(e);
                f.setDepartamento(d);
                lista.add(f);
            }
            //System.out.println("Teste");
            
        } catch (SQLException ex) {
            System.err.println("Erro ao buscar dados de Funcionario\n"+ex);
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
    
    public ArrayList<Funcionario> buscar(String nome){
        ArrayList<Funcionario> lista = new ArrayList();
        try {
            String sql = "Select * from funcionario,departamento,empresa where lower(nome_funcionario) like ? and empresa_dep=id_empresa "
                    + "and dep_funcionario=id_depar";
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
                
                Funcionario f=new Funcionario();
                f.setId(rs.getInt("id_funcionario"));
                f.setHoras(rs.getDouble("horas_funcionario"));
                f.setSalarioHora(rs.getDouble("salariohora_funcionario"));
                f.setNome(rs.getString("nome_funcionario"));
                f.setSexo(rs.getString("sexo_funcionario"));
                f.setAltura(rs.getDouble("altura_funcionario"));
                f.setPeso(rs.getDouble("peso_funcionario"));
                f.setNasc((java.util.Date)rs.getDate("dtnasc_funcionario"));
                
                d.setEmpresa(e);
                f.setDepartamento(d);
                lista.add(f);
            }
            //System.out.println("Teste");
            
        } catch (SQLException ex) {
            System.err.println("Erro ao buscar dados de Funcionario\n"+ex);
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
    
    public ArrayList<Funcionario> buscar(Departamento ob){
        ArrayList<Funcionario> lista = new ArrayList();
        try {
            String sql = "Select * from funcionario,departamento,empresa where nome_depar like ? and empresa_dep=id_empresa and dep_funcionario=id_depar";
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
                
                Funcionario f=new Funcionario();
                f.setId(rs.getInt("id_funcionario"));
                f.setHoras(rs.getDouble("horas_funcionario"));
                f.setSalarioHora(rs.getDouble("salariohora_funcionario"));
                f.setNome(rs.getString("nome_funcionario"));
                f.setSexo(rs.getString("sexo_funcionario"));
                f.setAltura(rs.getDouble("altura_funcionario"));
                f.setPeso(rs.getDouble("peso_funcionario"));
                f.setNasc((java.util.Date)rs.getDate("dtnasc_funcionario"));
                
                d.setEmpresa(e);
                f.setDepartamento(d);
                lista.add(f);
            }
            //System.out.println("Teste");
            
        } catch (SQLException ex) {
            System.err.println("Erro ao buscar dados de Funcionario\n"+ex);
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
    
    public ArrayList<Funcionario> buscar(Empresa ob){
        ArrayList<Funcionario> lista = new ArrayList();
        try {
            String sql = "Select * from funcionario,departamento,empresa where nome_empresa like ? and empresa_dep=id_empresa and dep_funcionario=id_depar";
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
                
                Funcionario f=new Funcionario();
                f.setId(rs.getInt("id_funcionario"));
                f.setHoras(rs.getDouble("horas_funcionario"));
                f.setSalarioHora(rs.getDouble("salariohora_funcionario"));
                f.setNome(rs.getString("nome_funcionario"));
                f.setSexo(rs.getString("sexo_funcionario"));
                f.setAltura(rs.getDouble("altura_funcionario"));
                f.setPeso(rs.getDouble("peso_funcionario"));
                f.setNasc((java.util.Date)rs.getDate("dtnasc_funcionario"));
                
                d.setEmpresa(e);
                f.setDepartamento(d);
                lista.add(f);
            }
            //System.out.println("Teste");
            
        } catch (SQLException ex) {
            System.err.println("Erro ao buscar dados de Funcionario\n"+ex);
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
    
    public ArrayList<Funcionario> buscar(String nome,Departamento ob){
        ArrayList<Funcionario> lista = new ArrayList();
        try {
            String sql = "Select * from funcionario,departamento,empresa where lower(nome_funcionario) like ? and nome_depar like ? and empresa_dep=id_empresa "
                    + "and dep_funcionario=id_depar";
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
                
                Funcionario f=new Funcionario();
                f.setId(rs.getInt("id_funcionario"));
                f.setHoras(rs.getDouble("horas_funcionario"));
                f.setSalarioHora(rs.getDouble("salariohora_funcionario"));
                f.setNome(rs.getString("nome_funcionario"));
                f.setSexo(rs.getString("sexo_funcionario"));
                f.setAltura(rs.getDouble("altura_funcionario"));
                f.setPeso(rs.getDouble("peso_funcionario"));
                f.setNasc((java.util.Date)rs.getDate("dtnasc_funcionario"));
                
                d.setEmpresa(e);
                f.setDepartamento(d);
                lista.add(f);
            }
            //System.out.println("Teste");
            
        } catch (SQLException ex) {
            System.err.println("Erro ao buscar dados de Funcionario\n"+ex);
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
    
    public ArrayList<Funcionario> buscar(String nome,Empresa ob){
        ArrayList<Funcionario> lista = new ArrayList();
        try {
            String sql = "Select * from funcionario,departamento,empresa where lower(nome_funcionario) like ? and nome_empresa like ? and empresa_dep=id_empresa "
                    + "and dep_funcionario=id_depar";
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
                
                Funcionario f=new Funcionario();
                f.setId(rs.getInt("id_funcionario"));
                f.setHoras(rs.getDouble("horas_funcionario"));
                f.setSalarioHora(rs.getDouble("salariohora_funcionario"));
                f.setNome(rs.getString("nome_funcionario"));
                f.setSexo(rs.getString("sexo_funcionario"));
                f.setAltura(rs.getDouble("altura_funcionario"));
                f.setPeso(rs.getDouble("peso_funcionario"));
                f.setNasc((java.util.Date)rs.getDate("dtnasc_funcionario"));
                
                d.setEmpresa(e);
                f.setDepartamento(d);
                lista.add(f);
            }
            //System.out.println("Teste");
            
        } catch (SQLException ex) {
            System.err.println("Erro ao buscar dados de Funcionario\n"+ex);
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
    
    public ArrayList<Funcionario> buscar(Departamento obj,Empresa ob){
        ArrayList<Funcionario> lista = new ArrayList();
        try {
            String sql = "Select * from funcionario,departamento,empresa where nome_depar like ? and nome_empresa like ? and empresa_dep=id_empresa "
                    + "and dep_funcionario=id_depar";
            ps = con.prepareStatement(sql);
            ps.setString(1, "%"+obj.getNome()+"%");
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
                
                Funcionario f=new Funcionario();
                f.setId(rs.getInt("id_funcionario"));
                f.setHoras(rs.getDouble("horas_funcionario"));
                f.setSalarioHora(rs.getDouble("salariohora_funcionario"));
                f.setNome(rs.getString("nome_funcionario"));
                f.setSexo(rs.getString("sexo_funcionario"));
                f.setAltura(rs.getDouble("altura_funcionario"));
                f.setPeso(rs.getDouble("peso_funcionario"));
                f.setNasc((java.util.Date)rs.getDate("dtnasc_funcionario"));
                
                d.setEmpresa(e);
                f.setDepartamento(d);
                lista.add(f);
            }
            //System.out.println("Teste");
            
        } catch (SQLException ex) {
            System.err.println("Erro ao buscar dados de Funcionario\n"+ex);
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
    
    public ArrayList<Funcionario> buscar(String nome,Departamento obj,Empresa ob){
        ArrayList<Funcionario> lista = new ArrayList();
        try {
            String sql = "Select * from funcionario,departamento,empresa where lower(nome_funcionario) like ? "
                    + "and nome_depar like ? "
                    + "and nome_empresa like ?"
                    + "and empresa_dep=id_empresa "
                    + "and dep_funcionario=id_depar";
            ps = con.prepareStatement(sql);
            ps.setString(1, "%"+nome.toLowerCase().trim()+"%");
            ps.setString(2, "%"+obj.getNome()+"%");
            ps.setString(3, "%"+ob.getNome()+"%");
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
                
                Funcionario f=new Funcionario();
                f.setId(rs.getInt("id_funcionario"));
                f.setHoras(rs.getDouble("horas_funcionario"));
                f.setSalarioHora(rs.getDouble("salariohora_funcionario"));
                f.setNome(rs.getString("nome_funcionario"));
                f.setSexo(rs.getString("sexo_funcionario"));
                f.setAltura(rs.getDouble("altura_funcionario"));
                f.setPeso(rs.getDouble("peso_funcionario"));
                f.setNasc((java.util.Date)rs.getDate("dtnasc_funcionario"));
                
                d.setEmpresa(e);
                f.setDepartamento(d);
                lista.add(f);
            }
            //System.out.println("Teste");
            
        } catch (SQLException ex) {
            System.err.println("Erro ao buscar dados de Funcionario\n"+ex);
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
    
    public void adicionar(Funcionario obj){
        String sql = "insert into funcionario (horas_funcionario, salariohora_funcionario, nome_funcionario, sexo_funcionario,"
                + " altura_funcionario, peso_funcionario, dep_funcionario, dtnasc_funcionario)"
                + " values (?, ?, ?, ?, ?, ?, ?, ?)";
        try{
            ps = con.prepareStatement(sql);
            ps.setDouble(1, obj.getHoras());
            ps.setDouble(2, obj.getSalarioHora());
            ps.setString(3, obj.getNome());
            ps.setString(4, obj.getSexo());
            ps.setDouble(5, obj.getAltura());
            ps.setDouble(6, obj.getPeso());
            ps.setInt(7,obj.getDepartamento().getId());
            ps.setDate(8, new java.sql.Date(obj.getNasc().getTime()));
            ps.executeUpdate();
        }catch(SQLException e){
            System.err.println("Erro ao inserir dados de Funcionario: "+e);
        }finally{
            try {
                ps.close();
                con.close();
            } catch (SQLException ex) {
                System.out.println("Erro: "+ex);
            }
        }
        
    }
    
    public void excluir(Funcionario obj){
        String sql = "delete from funcionario where id_funcionario = ?";
        try{
            ps = con.prepareStatement(sql);
            ps.setInt(1, obj.getId());
            ps.executeUpdate();
        }catch(SQLException e){
            System.err.println("Erro ao remover dados de Funcionario: "+e);
        }finally{
            try {
                ps.close();
                con.close();
            } catch (SQLException ex) {
                System.out.println("Erro: "+ex);
            }
        }
    }
    
    public void editar(Funcionario obj){
         String sql = "update funcionario set horas_funcionario = ?,"
                 + " salariohora_funcionario = ?, nome_funcionario = ?, sexo_funcionario = ?,"
                 + " altura_funcionario = ?, peso_funcionario = ?, dep_funcionario = ?, dtnasc_funcionario = ?"
                 + " where id_funcionario = ?";
        try{
            ps = con.prepareStatement(sql);
            ps.setDouble(1, obj.getHoras());
            ps.setDouble(2, obj.getSalarioHora());
            ps.setString(3, obj.getNome());
            ps.setString(4, obj.getSexo());
            ps.setDouble(5, obj.getAltura());
            ps.setDouble(6, obj.getPeso());
            ps.setInt(7, obj.getDepartamento().getId());
            ps.setDate(8, (java.sql.Date) obj.getNasc());
            ps.setInt(9, obj.getId());
            ps.executeUpdate();
        }catch(SQLException e){
            System.err.println("Erro ao editar dados do Funcionario: "+e);
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
