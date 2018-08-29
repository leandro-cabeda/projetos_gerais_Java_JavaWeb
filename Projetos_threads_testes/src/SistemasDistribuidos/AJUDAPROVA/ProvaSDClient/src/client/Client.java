package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import produtos.Produtos;
import util.Mensagem;

public class Client{

    public static Produtos cadastraProduto() {
        Produtos p = new Produtos();
        p.setCodigo(Integer.parseInt(JOptionPane.showInputDialog(null, "Informe o codigo do produto: ")));
        p.setNome(JOptionPane.showInputDialog(null, "Informe o nome do produto"));
        p.setValor(Double.parseDouble(JOptionPane.showInputDialog(null,"Informe o valor do produto")));
        return p;
    }

    public static void main(String[] args) {

        try {
            System.out.println("Estabelecendo conexão...");
            Socket socket = new Socket("localhost", 5555);
            System.out.println("Conexão estabelecida.");

            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
            System.out.println("Enviando mensagem...");
            //----------------------------------------------------------------------------------------------
            Mensagem m = new Mensagem("CADASTRO");
            Produtos p = cadastraProduto();
            m.setParam("produto", p);
            output.writeObject(m);
            output.flush(); //libera buffer para envio
            System.out.println("Mensagem " + m + " enviada.");          
            m = (Mensagem) input.readObject();
            System.out.println("Resposta: " + m);
            // CADASTRANDO O SEGUNDO PRODUTO ----------------------------------------------------------------
            m = new Mensagem("CADASTRO");
            Produtos p2 = cadastraProduto();
            m.setParam("produto", p2);
            output.writeObject(m);
            output.flush(); //libera buffer para envio
            System.out.println("Mensagem " + m + " enviada.");
            m = (Mensagem) input.readObject();
            System.out.println("Resposta: " + m);
            //----------------------------------------------------------------------------------------------
            
            m = new Mensagem("PROCURA");
            int index = Integer.parseInt(JOptionPane.showInputDialog(null, "Informe o código do produto que deseja procurar"));
            m.setParam("index", index);
            output.writeObject(m);
            output.flush(); //libera buffer para envio
            System.out.println("Mensagem " + m + " enviada.");
            m = (Mensagem) input.readObject();
            System.out.println("Resposta: " + m);
            //----------------------------------------------------------------------------------------------
            
            m = new Mensagem("REMOVE");
            index = Integer.parseInt(JOptionPane.showInputDialog(null, "Informe o código do produto que deseja remover"));
            m.setParam("index", index);
            output.writeObject(m);
            output.flush(); //libera buffer para envio
            System.out.println("Mensagem " + m + " enviada.");
            m = (Mensagem) input.readObject();
            System.out.println("Resposta: " + m);

            input.close();
            output.close();
            socket.close();
            
        } catch (IOException ex) {
            System.out.println("Erro no cliente: " + ex);
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            System.out.println("Erro no cast: " + ex.getMessage());
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
