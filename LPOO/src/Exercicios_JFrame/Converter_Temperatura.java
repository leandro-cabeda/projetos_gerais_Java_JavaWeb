/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exercicios_JFrame;

import Teste.TesteJFrame;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Leandro
 */
public class Converter_Temperatura extends JFrame implements ActionListener {
    
    JLabel lblt=new JLabel("Conversão de Temperaturas: ºC <> ºF");
    JLabel lblc=new JLabel("ºC ");
    JTextField txtc=new JTextField(20);
    JLabel lblf=new JLabel("ºF ");
    JTextField txtf=new JTextField(20);
    JButton btnconverter= new JButton("Converter");
    JButton btnsair= new JButton("Sair");
    
    public Converter_Temperatura()
    {
        setTitle("Converte Temp");
        setSize(650,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        add(lblt);
        add(txtc);
        add(lblc);
        add(txtf);
        add(lblf);
        add(btnconverter);
        add(btnsair);
        
        btnconverter.addActionListener(this);
        btnsair.addActionListener(this);
        getRootPane().setDefaultButton(btnconverter); // seleciona o botão automático ou acão definida apenas
        //  apertando enter
    }
    
    public static void main(String[] args) {
         Converter_Temperatura tela= new Converter_Temperatura();
           tela.setVisible(true);
    }
    
    public double convert(double a)
    {
        double res;
        res=(a*1.8)+32;
        return res;
    }
    public double convert2(double b)
    {
        double res;
        res=(b-32)/1.8;
        return res;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource()==btnconverter)
        {
            if(!txtc.getText().isEmpty())
            {
                double valc=Double.parseDouble(txtc.getText().replace(",","."));
                double res=convert(valc);
                txtf.setText(""+res);
                txtc.setText("");
                
            }
            else if(txtf.getText()!=null)
            {
                double valf=Double.parseDouble(txtf.getText().replace(",","."));
                double res2=convert2(valf);
                txtc.setText(""+res2);
                txtf.setText("");
            }
            else
            {
                JOptionPane.showMessageDialog(rootPane,"Campo obrigatório para digitar");
            }
            
        }else if(e.getSource()==btnsair)
        {
            System.exit(0);
        }
        
    }
    
    
    
    
    
}
