/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aula1;
import javax.swing.JOptionPane;

/**
 *
 * @author Leandro
 */
public class Teste_Pessoa {
    public static void main(String[] args) {
        //Modelo_Pessoa p1=new Modelo_Pessoa("Florinda", 13);
       // p1.nome="florinda";
        //p1.idade=13;
        //JOptionPane.showMessageDialog(null,"Quantidade pessoas cadastradas: "+Modelo_Pessoa.quantidade);
        //Modelo_Pessoa p2= new Modelo_Pessoa("João",14);
        //p2.setnome("João da Pinamaiangaba");
        //p2.fazeraniversario();
        Modelo_Pessoa p3= new Modelo_Pessoa("Leandro",29);
        Modelo_Pessoa p2= new Modelo_Pessoa("Leo",29);
        p3.setnome("Leandro Cabeda Rigo");
        p3.setpeso(68.5);
        p3.setaltura(1.72);
        Empresa e=new Empresa();
        e.setnome("Instituto Federal Sul-Rio-Grandense");
        e.setcnpj("111111111111");
        e.setrazaosocial("IFSUL");
        // Vinculando empresa a pessoa
        //p3.setlocaltrabalho(e);
        //p2.setlocaltrabalho(e);
        //JOptionPane.showMessageDialog(null,"Local de trabalho: "+p3.getlocaltrabalho().getnome());
        //JOptionPane.showMessageDialog(null,"Pessoa Cadastrada: "+p3+"  Empresa: "+e);
        //JOptionPane.showMessageDialog(null,p3.exibirdados());
        
        // vinculando empregados a empresa
        //Vinculando empregado(objetos do tipo pessoa)a empresa
        e.getempregados().add(p3);
        e.adicionarempregado(p2);
        JOptionPane.showMessageDialog(null,e.getempregados().get(0).getnome());
        JOptionPane.showMessageDialog(null,p2.getlocaltrabalho());
        JOptionPane.showMessageDialog(null,e.mostrarempregado());
        //e.getempregados().remove(p2);  exemplo dum jeito
        e.removerempregado(p2);  // exemplo mais simplificado
        
       // p2.nome="João";
        //p2.idade=10;
        //JOptionPane.showMessageDialog(null,p1.exibirdados());
        
        //JOptionPane.showMessageDialog(null,p2.exibirdados());
        
        //JOptionPane.showMessageDialog(null,p3.exibirdados());
        //JOptionPane.showMessageDialog(null,p3.exibirdadosmc());
        //JOptionPane.showMessageDialog(null,p3.graudeobesidade());
        Modelo_Pessoa p4= new Modelo_Pessoa("Alexandre",27);
        p4.setnome("Alexandre Cabeda Rigo");
        p4.setaltura(1.81);
        p4.setpeso(126);
        //JOptionPane.showMessageDialog(null,p4.exibirdados());
        //JOptionPane.showMessageDialog(null,p4.exibirdadosmc());
        //JOptionPane.showMessageDialog(null,p4.graudeobesidade());
        
        //JOptionPane.showMessageDialog(null,"Altura: "+p3.getaltura()+ "\nPeso:  "+p3.getpeso());
        
        //JOptionPane.showMessageDialog(null, p1.getidade());
        
        //JOptionPane.showMessageDialog(null,"Quantidade pessoas cadastradas: "+Modelo_Pessoa.quantidade);
    }
}
