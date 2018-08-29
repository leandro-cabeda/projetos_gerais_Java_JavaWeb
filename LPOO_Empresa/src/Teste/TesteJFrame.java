/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Teste;

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
 * @author vanes
 */
public class TesteJFrame extends JFrame implements ActionListener{
    //Componentes
    JLabel lblNome = new JLabel("Nome");
    JTextField txtNome = new JTextField(20);
    JButton btnEnviar = new JButton("Enviar");
    
    public TesteJFrame(){//Construtor
        //Definição das características da Janela
        setTitle("Empresa");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());//Gerenciador de Layout em linha
        add(lblNome);
        add(txtNome);
        add(btnEnviar);
        
        //Monitorando componente
        btnEnviar.addActionListener(this);
    }
    public static void main(String[] args) {
        TesteJFrame tela = new TesteJFrame();
        tela.setVisible(true);
        
    }

    @Override
    public void actionPerformed(ActionEvent acao) {
        if(acao.getSource() == btnEnviar){
            JOptionPane.showMessageDialog(rootPane, "Bem-vindo(a) "+txtNome.getText());
        }
    }
}
