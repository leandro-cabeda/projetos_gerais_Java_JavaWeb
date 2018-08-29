/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Teste;

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
import modelo.Funcionario;

/**
 *
 * @author vanes
 */
public class MostraInfo extends JFrame implements ActionListener {

    JLabel lblNome = new JLabel("Nome:");
    JLabel lblIdade = new JLabel("Data Nascimento:");
    JLabel lblPeso = new JLabel("Peso:");
    JLabel lblAltura = new JLabel("Altura:");

    JTextField txtNome = new JTextField(28);
    JTextField txtIdade = new JTextField(22);
    JTextField txtPeso = new JTextField(28);
    JTextField txtAltura = new JTextField(28);

    JButton btnEnviar = new JButton("Enviar");
    JButton btnLimpar = new JButton("Limpar");
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public MostraInfo() {
        setTitle("Cadastro de Funcionário");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        this.dispose();
        setLayout(new FlowLayout());//Gerenciador de Layout em linha
        txtIdade.setToolTipText("Informe data (dd/mm/yyyy)");

        add(lblNome);
        add(txtNome);
        add(lblIdade);
        add(txtIdade);
        add(lblPeso);
        add(txtPeso);
        add(lblAltura);
        add(txtAltura);
        add(btnEnviar);
        add(btnLimpar);

        //monitorar componentes
        btnEnviar.addActionListener(this);
//        txtNome.addActionListener(this);
//        txtIdade.addActionListener(this);
//        txtPeso.addActionListener(this);
//        txtAltura.addActionListener(this);
        getRootPane().setDefaultButton(btnEnviar);
        btnLimpar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnEnviar //                || e.getSource() == txtNome ||
                //                e.getSource() == txtIdade ||
                //                e.getSource() == txtPeso ||
                //                e.getSource() == txtAltura
                ) {
            String aux = "Campos Obrigatórios:";
            int cont = 0;
            Funcionario f = new Funcionario();
            if (!txtNome.getText().isEmpty()) {
                f.setNome(txtNome.getText());
            } else {
                cont++;
                aux += "\n- Nome";
            }
            try {
                f.setDataNascimento(sdf.parse(txtIdade.getText()));
            } catch (ParseException ex) {
//                System.out.println("Data Inválida");
                aux += "\n- Data de Nascimento";
                cont++;
            }
            if (!txtPeso.getText().isEmpty()) {
                f.setPeso(Double.parseDouble(txtPeso.getText().replace(',', '.')));
            } else {
                aux += "\n- Peso";
                cont++;
            }
            if (!txtAltura.getText().isEmpty()) {
                f.setAltura(Double.parseDouble(txtAltura.getText().replace(',', '.')));
            } else {
                aux += "\n- Altura";
                cont++;
            }
            if (cont != 0) {
                JOptionPane.showMessageDialog(rootPane, aux);//Msg erro
            } else {
                JOptionPane.showMessageDialog(rootPane, f.getInfo());
                limparCampos();
            }
        } else if (e.getSource() == btnLimpar) {
            limparCampos();
        }
    }

    public static void main(String[] args) {
        MostraInfo tela = new MostraInfo();
        tela.setVisible(true);
    }

    public void limparCampos() {
        //Limpar Campos
        txtNome.setText("");
        txtIdade.setText("");
        txtPeso.setText("");
        txtAltura.setText("");
    }

}
