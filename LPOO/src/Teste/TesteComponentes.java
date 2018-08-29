/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Teste;

import ProjetoEmpresa.Funcionario;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Leandro
 */
public class TesteComponentes {
    public static void main(String[] args) {
       JLabel lblNome=new JLabel("Nome:");
       JLabel lblIdade=new JLabel("Data de nascimento:");
       JTextField txtNome=new JTextField();
       JTextField txtIdade=new JTextField ();
       JLabel lblAltura=new JLabel("Altura: ");
       JLabel lblPeso=new JLabel("Peso: ");
       JTextField txtAltura=new JTextField();
       JTextField txtPeso=new JTextField();
        SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
        
        txtIdade.setToolTipText("Informe data (dd/mm/yyyy)");
       
       Object[] componentes={lblNome,txtNome,lblIdade,txtIdade,lblAltura,txtAltura,lblPeso,txtPeso};
       
        JOptionPane.showMessageDialog(null, componentes);
        
        Funcionario f=new Funcionario();
        f.setNome(txtNome.getText());
        f.setAltura(Double.parseDouble(txtAltura.getText()));
        f.setPeso(Double.parseDouble(txtPeso.getText()));
        try{
            f.setDtnasci(sdf.parse(txtIdade.getText()));
        }catch(ParseException ex)
        {
            System.out.println("Data Inválida");
        }
        JOptionPane.showMessageDialog(null,f.getinfo());
    }
    
}
