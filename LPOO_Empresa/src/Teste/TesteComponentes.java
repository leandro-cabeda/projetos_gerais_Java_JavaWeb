/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Teste;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import modelo.Funcionario;

/**
 *
 * @author vanes
 */
public class TesteComponentes {
    public static void main(String[] args) {
        JLabel lblNome = new JLabel("Nome:");
        JLabel lblIdade = new JLabel("Data Nascimento:");
        JLabel lblPeso = new JLabel("Peso:");
        JLabel lblAltura = new JLabel("Altura:");
        
        JTextField txtNome = new JTextField();
        JTextField txtIdade = new JTextField();
        JTextField txtPeso = new JTextField();
        JTextField txtAltura = new JTextField();
        
//        txtNome.setText("Teste");
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        
        txtIdade.setToolTipText("Informe data (dd/mm/yyyy)");
        
        Object[] componentes = {lblNome, txtNome, lblIdade, txtIdade, lblPeso, txtPeso, lblAltura, txtAltura};
        JOptionPane.showMessageDialog(null, componentes);
        
        Funcionario f = new Funcionario();
        f.setNome(txtNome.getText());
        /*try {
            f.setDataNascimento(sdf.parse(txtIdade.getText()));
        } catch (ParseException ex) {
            System.out.println("Data Inválida");
        }*/
        f.setPeso(Double.parseDouble(txtPeso.getText()));
        f.setAltura(Double.parseDouble(txtAltura.getText()));
        
       // JOptionPane.showMessageDialog(null, f.getInfo());
    }
}
