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
import modelo.Dependente;
import modelo.Funcionario;

/**
 *
 * @author Leandro
 */
public class DependenteDAO {
    
    Connection con = (new Conexao()).getConexao();
    PreparedStatement ps;
    ResultSet rs;
    
    public ArrayList<Dependente> buscar(){
        ArrayList<Dependente> lista = new ArrayList();
        try {
            String sql = "Select * from dependente";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                
                Dependente depe=new Dependente();
                depe.setId(rs.getInt("id_dep"));
                depe.setNome(rs.getString("nome_dep"));
                depe.setSexo(rs.getString("sexo_dep")); //charAt(0), ele só manda 1 caracter pro sexo, usando char
                depe.setAltura(rs.getDouble("altura_dep"));
                depe.setPeso(rs.getDouble("peso_dep"));
                depe.setDtnasc((java.util.Date)rs.getDate("dtnasc_dep"));
                lista.add(depe);
            }
            //System.out.println("Teste");
            
        } catch (SQLException ex) {
            System.err.println("Erro ao buscar dados de Dependente\n"+ex);
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

    
    public ArrayList<Dependente> buscar(Funcionario fu){
        ArrayList<Dependente> lista = new ArrayList();
        try {
            String sql = "Select * from dependente where "
                    + " funcionario_dep= ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, fu.getId());
            rs = ps.executeQuery();
            while(rs.next()){
                
                Dependente depe=new Dependente();
                depe.setId(rs.getInt("id_dep"));
                depe.setNome(rs.getString("nome_dep"));
                depe.setSexo(rs.getString("sexo_dep")); //charAt(0), ele só manda 1 caracter pro sexo, usando char
                depe.setAltura(rs.getDouble("altura_dep"));
                depe.setPeso(rs.getDouble("peso_dep"));
                depe.setDtnasc((java.util.Date)rs.getDate("dtnasc_dep"));
                lista.add(depe);
            }
            //System.out.println("Teste");
            
        } catch (SQLException ex) {
            System.err.println("Erro ao buscar dados de Dependente\n"+ex);
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
    
    public void adicionar(Dependente obj){
        String sql = "insert into dependente ( nome_dep, sexo_dep,"
                + " dtnasc_dep, altura_dep, peso_dep, funcionario_dep)"
                + " values (?, ?, ?, ?, ?, ?)";
        try{
            ps = con.prepareStatement(sql);
            ps.setString(1, obj.getNome());
            ps.setString(2, obj.getSexo());
            ps.setDate(3, new java.sql.Date(obj.getDtnasc().getTime()));
            ps.setDouble(4, obj.getAltura());
            ps.setDouble(5, obj.getPeso());
            ps.setInt(6,obj.getFunc().getId());
            ps.executeUpdate();
        }catch(SQLException e){
            System.err.println("Erro ao inserir dados de Dependentes: "+e);
        }finally{
            try {
                ps.close();
                con.close();
            } catch (SQLException ex) {
                System.out.println("Erro: "+ex);
            }
        }
        
    }
    
    public void excluir(Dependente obj){
        String sql = "delete from dependente where id_dep = ?";
        try{
            ps = con.prepareStatement(sql);
            ps.setInt(1, obj.getId());
            ps.executeUpdate();
        }catch(SQLException e){
            System.err.println("Erro ao remover dados de Dependente: "+e);
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
