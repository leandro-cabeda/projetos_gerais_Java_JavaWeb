/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Teste;

import ProjetoEmpresa.Funcionario;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Leandro
 */
public class TesteJFrame extends JFrame implements ActionListener {
    //Componentes
    JLabel lblnome=new JLabel("Nome: ");
    JTextField txtnome=new JTextField(20);
    JLabel lblIdade=new JLabel("Data de nascimento:");
       JTextField txtIdade=new JTextField (20);
       JLabel lblAltura=new JLabel("Altura: ");
       JLabel lblPeso=new JLabel("Peso: ");
       JTextField txtAltura=new JTextField(20);
       JTextField txtPeso=new JTextField(20);
       SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
    JButton btnenviar= new JButton("Enviar"); // cria um botão
    JButton btnlimpar= new JButton("Limpar");
    
    
    public TesteJFrame()//Construtor
    {
        //definição das caracteristicas da janela
        
        setTitle("Cadastro de Funcionário");
        setSize(650,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// Encerra a tela quando clicado em fechar
        setLayout(new FlowLayout());// gerenciador de linha
        txtIdade.setToolTipText("Informe data (dd/mm/yyyy)");// mostra par ao usuario de forma de ajuda na hora que digitar
        add(lblnome); // adiciona dentro da tela os campos
        add(txtnome);
        add(lblIdade);
        add(txtIdade);
        add(lblAltura);
        add(txtAltura);
        add(lblPeso);
        add(txtPeso);
        add(btnenviar);
        add(btnlimpar);
        
        //Monitorando componente
        btnenviar.addActionListener(this);
        btnlimpar.addActionListener(this);
        getRootPane().setDefaultButton(btnenviar);
       // txtnome.addActionListener(this);
        //txtIdade.addActionListener(this);
        //txtAltura.addActionListener(this);
        //txtPeso.addActionListener(this);
    }
    
    public static void main(String[] args) {
        TesteJFrame tela= new TesteJFrame();
        
        tela.setVisible(true);// abre e mostra uma tela
        

}
    public void limpacampos()
    {
        // limpar campos
            txtnome.setText("");
            txtIdade.setText("");
            txtPeso.setText("");
            txtAltura.setText("");
    }

    @Override
    public void actionPerformed(ActionEvent acao) {
        if(acao.getSource()==btnenviar) //|| acao.getSource()==txtnome
               // || acao.getSource()==txtIdade
                //|| acao.getSource()==txtAltura
                //|| acao.getSource()==txtPeso
        {
            String aux="Campos obrigatórios:";
            int cont=0;
            Funcionario f=new Funcionario();
            if(!txtnome.getText().isEmpty())
            {
                f.setNome(txtnome.getText());
            }
            else
            {
                cont++;
                aux+="\n-Nome";
            }
            if(!txtAltura.getText().isEmpty())
            {
                f.setAltura(Double.parseDouble(txtAltura.getText().replace(",", ".")));
            }
            else
            {
                cont++;
                aux+="\n- Altura";
            }
            if(!txtPeso.getText().isEmpty())
            {
                f.setPeso(Double.parseDouble(txtPeso.getText().replace(",", ".")));
            }
            else
            {
                cont++;
                aux+="\n- Peso";
            }    
        
        
        try{
            f.setDtnasci(sdf.parse(txtIdade.getText()));
        }catch(ParseException ex)
        {
            //System.out.println("Data Inválida");
            aux+="\n - Data de Nascimento";
            cont++;
        } 
            if(cont!=0)
            {
                JOptionPane.showMessageDialog(rootPane,aux);// rootPane Tela Principal
                
            }
            else
            {
                JOptionPane.showMessageDialog(rootPane,f.getinfo());
                limpacampos();
            }
        }
        else if(acao.getSource()==btnlimpar)
        {
            limpacampos();
        }
        
    }
    
}
