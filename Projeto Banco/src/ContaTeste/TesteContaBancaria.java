/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ContaTeste;

import Conta.Corrente.ContaCorrente;
import Conta.Poupança.ContaPoupanca;
import java.text.DecimalFormat;
import javax.swing.JOptionPane;

/**
 *
 * @author Leandro
 */
public class TesteContaBancaria {
    public static void main(String[] args) {
        DecimalFormat df=new DecimalFormat("#,##0.00");
        ContaCorrente p1=new ContaCorrente("leandro cabeda",12345);
        ContaPoupanca p2=new ContaPoupanca("leandro rigo",3456);
   
        p1.depositar(200);
        p1.setTarifa(3);
        p1.processar();
        double valor=Double.parseDouble(JOptionPane.showInputDialog(null,"Informe um valor para sacar"));
        p1.sacar(valor);
        p1.transferir(p2, valor);
       
        //p2.depositar(70);
        //p2.setRendimento(5);
        //p2.processar();
    JOptionPane.showMessageDialog(null,p1.getExtrato());
    JOptionPane.showMessageDialog(null,p2.getExtrato());
        //JOptionPane.showMessageDialog(null,p1.extrato);
        //JOptionPane.showMessageDialog(null,p2.getinformacoes());
    }
    
}
