/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Teste;

import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import modelo.Departamento;
import modelo.Dependente;
import modelo.Empresa;
import modelo.Funcionario;

/**
 *
 * @author vanes
 */
public class Teste {
    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try{
            
        Funcionario f = new Funcionario();
        f.setNome("Joao");
        f.setSexo('m');
        f.setDataNascimento(sdf.parse("09/06/1980"));
        JOptionPane.showMessageDialog(null, f.getInfo());
//        Departamento dep = new Departamento();
//        dep.setNome("TI");
//        
//        Empresa e = new Empresa();
//        e.setNome("JOAO SA");
//        dep.setEmpresa(e);
//        
//        f.setDepartamento(dep);
//        
//        
//        Dependente d1 = new Dependente();
//        d1.setNome("Florinda");
//        d1.setDataNascimento(sdf.parse("10/05/1999"));
//        Dependente d2 = new Dependente();
//        d2.setNome("Florisbela");
//        d2.setDataNascimento(sdf.parse("29/03/1991"));
////        System.out.println("Idade "+d2.getNome()+" eh:"+d2.getIdade());
//        
//        f.adicionarDependente(d1);
//        f.adicionarDependente(d2);
        
        
        }catch(Exception e){
            System.out.println("Data Inválida");
        }
    }
}
