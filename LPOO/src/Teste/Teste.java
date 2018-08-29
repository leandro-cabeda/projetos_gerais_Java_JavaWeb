/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Teste;

import Empresa.Departamento;
import Empresa.Empresa;
import ProjetoEmpresa.Depedente;
import ProjetoEmpresa.Funcionario;

import java.text.SimpleDateFormat;

import javax.swing.JOptionPane;

/**
 *
 * @author Leandro
 */
public class Teste {
    public static void main(String[] args){
        SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
       try{
        Funcionario f=new Funcionario();
        f.setNome("joao");
        f.setSexo('m');
        f.setDtnasci(sdf.parse("08/08/1985"));
        JOptionPane.showMessageDialog(null,f.getinfo());
        //
        /*Departamento dep=new Departamento();
        dep.setNome("TI");
        
        
        Empresa e=new Empresa();
        e.setNome("joao sa");
        dep.setEmpresa(e);
        
        f.setDepartamento(dep);
        Depedente d1=new Depedente();
        d1.setNome("Florinda");
        
        //d1.setDtnasci(sdf.parse("10/05/1999"));
    
        Depedente d2=new Depedente();
        d2.setNome("Florisbela");
        
        //d2.setDtnasci(sdf.parse("10/10/2010"));
        
        f.adicionardepedente(d1);
        f.adicionardepedente(d2);
        
        //JOptionPane.showMessageDialog(null,f.getinfo());
        */
         }catch(Exception e){
        {
            System.out.println("Data inválida");
        }
                
        
        
    }
    
}
    }
