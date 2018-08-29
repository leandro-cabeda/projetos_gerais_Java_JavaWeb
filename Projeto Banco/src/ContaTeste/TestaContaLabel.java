/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ContaTeste;

import Conta.Corrente.ContaCorrente;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import jdk.nashorn.internal.scripts.JO;

/**
 *
 * @author Leandro
 */
public class TestaContaLabel {
    public static void main(String[] args) {
    
    JLabel lblTitular=new JLabel("Titular: ");
    JLabel lblNro=new JLabel("Nº da Conta: ");
    JLabel lblTarifa=new JLabel("Valor da Tarifa: ");
    JLabel lblLimite=new JLabel("Valor do Limite: ");
    JTextField txtTitular=new JTextField();
    JTextField txtNro=new JTextField();
    JTextField txtTarifa=new JTextField();
    JTextField txtLimite=new JTextField();
    
    Object componentes[]={lblTitular,txtTitular,lblNro,txtNro,lblTarifa,txtTarifa,lblLimite,txtLimite};
    
      JOptionPane.showMessageDialog(null,componentes);
      
      ContaCorrente c1=new ContaCorrente(txtTitular.getText(),Integer.parseInt(txtNro.getText()));
    }
}
