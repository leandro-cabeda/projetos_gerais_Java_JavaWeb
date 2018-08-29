/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Teste;

import controle.EmpresaDAO;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import modelo.Empresa;

/**
 *
 * @author vanes
 */
public class TesteCon2 {
    public static void main(String[] args) {
        EmpresaDAO dao = new EmpresaDAO();
//        ArrayList<Empresa> lista = dao.buscar("Maria");
//        JOptionPane.showMessageDialog(null, lista);
        Empresa e = new Empresa();
        e.setNome("DELL Ltda");
        e.setCNPJ("12312312312345");
        e.setRazaoSocial("DELL");
        
        dao.adicionar(e);
    }
}
