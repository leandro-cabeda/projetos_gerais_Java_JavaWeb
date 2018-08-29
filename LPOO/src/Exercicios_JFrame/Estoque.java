/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exercicios_JFrame;

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
public class Estoque extends JFrame implements ActionListener{
    JLabel lblc=new JLabel("Controle de Estoque de Computadores");
    JLabel lblq=new JLabel("A quantidade em Estoque é: ");
    JLabel lbln=new JLabel("10");
    JLabel lblv=new JLabel(" Valor:");
    JTextField txtv=new JTextField(15);
    JButton btnretirar= new JButton("Retirar");
    JButton btnincluir= new JButton("Incluir");
    JButton btnsair= new JButton("Sair");
    
    public Estoque()
    {
    setTitle("Controle de Estoque");
        setSize(650,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        add(lblc);
        add(lblq);
        add(lbln);
        add(lblv);
        add(txtv);
        add(btnretirar);
        add(btnincluir);
        add(btnsair);
        
        btnretirar.addActionListener(this);
        btnincluir.addActionListener(this);
        btnsair.addActionListener(this);
        //getRootPane().setDefaultButton(btnincluir);
    }
    
    public static void main(String[] args) {
         Estoque tela= new Estoque();
           tela.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==btnincluir)
        {
            if(!txtv.getText().isEmpty())
            {
                int val=Integer.parseInt(txtv.getText());
                int qtd=Integer.parseInt(lbln.getText());
                int r;
                
                    r=qtd+val;
                    lbln.setText(""+r);
                    txtv.setText("");
                
               
                
            }
            else
            {
                JOptionPane.showMessageDialog(rootPane,"Campo obrigatório para digitar");
            }
        }
        else if(e.getSource()==btnretirar)
        {
            if(!txtv.getText().isEmpty())
            {
                int val=Integer.parseInt(txtv.getText());
                int qtd=Integer.parseInt(lbln.getText());
                int r;
                if(qtd>=val)
                {
                    r=qtd-val;
                    lbln.setText(""+r);
                    txtv.setText("");
                }
                else
                {
                    JOptionPane.showMessageDialog(rootPane,"Não tem quantidade disponível no estoque");
                }
            }
            else
            {
                JOptionPane.showMessageDialog(rootPane,"Campo obrigatório para digitar");
            }
        }
        else if(e.getSource()==btnsair)
        {
            System.exit(0);
        }
        
    }
    
}
