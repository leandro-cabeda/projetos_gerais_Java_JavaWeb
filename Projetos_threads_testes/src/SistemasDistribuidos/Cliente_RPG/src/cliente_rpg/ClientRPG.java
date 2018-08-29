/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente_rpg;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import jogo.Acao;
import jogo.Produto;
import jogo.Usuario;
import util.Mensagem;
import util.Status;

/**
 *
 * @author elder
 */
public class ClientRPG {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Acao acao = null;
        Produto p;
        Usuario usuario = new Usuario();
        int op = 0, id;
        Boolean naoenumero;
        Status status;
        Mensagem m;

        try {
            /*
             1. Estabelecer conexão com o servidor
             2. Trocar mensagens com o servidor
             */
            //cria a conexão entre o cliente e o servvidor
            System.out.println("Estabelecendo conexão...");
            Socket socket = new Socket("localhost", 5555);
            System.out.println("Conexão estabelecida.");

            //criação dos streams de entrada e saída
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
            System.out.println("Enviando mensagem...");
            
            
            
            do{
                String user = "user";
                String pass = "pass";
//                String user = JOptionPane.showInputDialog("Informe o usuário");
//                String pass = JOptionPane.showInputDialog("Informe a senha");

                usuario.setUser(user);
                usuario.setPass(pass);

                m = new Mensagem("LOGIN");
                m.setParam("usuario", usuario);

                output.writeObject(m);
                output.flush();
                m = (Mensagem) input.readObject();
                System.out.println("Resposta: " + m);

                status = m.getStatus();
            }while(status == Status.ERROR);
            

            boolean sair = false;
            
            while (!sair) {
                JOptionPane.showMessageDialog(null, m);

                do {
                    naoenumero = false;
                    try {
                        op = Integer.parseInt(JOptionPane.showInputDialog("Informe a opcao desejada: "
                                + "\n 1 - Inserir"
                                + "\n 2 - Remover"
                                + "\n 3 - Consultar"
                                + "\n 4 - Listar"
                                + "\n 5 - Sair"));
                    } catch (Exception e) {
                        naoenumero = true;
                    }
                } while (naoenumero == true);
                
                p = new Produto();

                if (op == 1) {
                    acao = Acao.INSERIR;
                    p.setCodigo(Integer.parseInt(JOptionPane.showInputDialog("Informe o codigo do produto")));
                    p.setNome(JOptionPane.showInputDialog("Informe o nome do produto"));
                    p.setCategoria(JOptionPane.showInputDialog("Informe a categoria"));
                    p.setValor(Double.parseDouble(JOptionPane.showInputDialog("Informe o valor")));
                } 
                else if (op == 2) {
                    p.setCodigo(Integer.parseInt(JOptionPane.showInputDialog("Informe o id do produto a ser removido")));
                    acao = Acao.REMOVER;
                } else if (op == 3) {
                    p.setCodigo(Integer.parseInt(JOptionPane.showInputDialog("Informe o id do produto que será procurado")));
                    acao = Acao.CONSULTAR;
                } else if(op == 4){
                    acao = Acao.LISTAR;
                }else {
                    acao = Acao.SAIR;
                }
                
                System.out.println(m);
                
                m = new Mensagem("CADASTRO");
                m.setParam("produto", p);
                m.setParam("acao", acao);

                //envia os parametros que foram definidos através da variável m
                output.writeObject(m);
                output.flush(); //libera buffer para envio
                

                System.out.println("Mensagem " + m + " enviada.");

                m = (Mensagem) input.readObject();
                System.out.println("Resposta: " + m);

                if (m.getParam("sair").equals("1")) {
                    sair = true;
                }

            }

            input.close();
            output.close();
            socket.close();

        } catch (IOException ex) {
            System.out.println("Erro no cliente: " + ex);
            Logger.getLogger(ClientRPG.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            System.out.println("Erro no cast: " + ex.getMessage());
            Logger.getLogger(ClientRPG.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
