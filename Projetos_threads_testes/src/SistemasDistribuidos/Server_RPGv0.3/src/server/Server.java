/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import util.Mensagem;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import jogo.Atributo;
import jogo.Item;
import util.Estados;
import util.Status;
import jogo.Jogo;

/**
 *
 * @author elder
 */
public class Server {
    /* 1 - Criar o servidor de conexões
     2 -Esperar o um pedido de conexão;
     Outro processo
     2.1 e criar uma nova conexão;
     3 - Criar streams de enechar socket de comunicação entre servidor/cliente
     4.2 - Fechar streams de entrada e saída
     trada e saída;
     4 - Tratar a conversação entre cliente e 
     servidor (tratar protocolo);
     4.1 - Fechar socket de comunicação entre servidor/cliente
     4.2 - Fechar streams de entrada e saída
           
     5 - voltar para o passo 2, até que finalize o programa;
     6 - fechar serverSocket
     */

    private ServerSocket serverSocket;
    private int cont = 0;
    
    public synchronized void conta(String ip)
    {
        System.out.println(ip+" Contou: "+ ++cont);
    }
    
    /*- Criar o servidor de conexões*/   
    private void criarServerSocket(int porta) throws IOException {
        serverSocket = new ServerSocket(porta);
        cont = 0;
    }
    

    /*2 -Esperar o um pedido de conexão;
     Outro processo*/
    private Socket esperaConexao() throws IOException {
        Socket socket = serverSocket.accept();
        return socket;
    }

    private void fechaSocket(Socket s) throws IOException {
        s.close();
    }

    private void enviaMsg(Object o, ObjectOutputStream out) throws IOException {
        out.writeObject(o);
        out.flush();
    }

    
   
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ClassNotFoundException {
        try {

            Server server = new Server();
            server.criarServerSocket(5555);
            while (true) {
                System.out.println("Aguardando conexão...");
                Socket socket = server.esperaConexao();//protocolo
                System.out.println("Cliente conectado.");
                //Outro processo
                TrataConexao trataConexao = new TrataConexao(socket, server);
                Thread t = new Thread(trataConexao);
                t.start();
                //server.trataConexao(socket);
                //System.out.println("Cliente finalizado.");
            }
        } catch (IOException e) {
            //trata exceção
            System.out.println("Erro no servidor: " + e.getMessage());
        }
    }

}
