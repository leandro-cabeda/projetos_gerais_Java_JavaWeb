
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Leandro
 */
public class Server{

    private ServerSocket servidorsocket;
    private List<Tarefa> tarefas;
    private List<Thread> threads;
    private int cont;

    // Passo 1 - criar o servidor de conexões
    private void criarServidorSocket(Integer porta) throws IOException, IllegalArgumentException {
        try {
            if (porta == null || porta < 100 || porta > 60000) {
                throw new IllegalArgumentException("Porta nula ou inválida");
            }
            cont = 0;
            tarefas=new ArrayList<>();
            threads=new ArrayList<>();
            servidorsocket = new ServerSocket(porta);
        } catch (IllegalArgumentException ex) {
            System.out.println("Tratandoa  exceção dentro do metodo");
            throw ex; // dispara para devolta a quem utilizar e tratar da maneira que quiser
        }
    }

    //Passo 2 - Esperar um pedido de conexao, outro processo
    private Socket esperarConexao() throws IOException {
        Socket socket = servidorsocket.accept();

        return socket;
    }
    
    public synchronized void avisa()
    {
        System.out.println("Contador: " + cont++);
    }

   

    public static void main(String[] args) {
        try {
            Server server = new Server(); // tratar exceção
            server.iniciaServidor();
        } catch (IOException ex) {
            //trata exceção
            System.out.println("Erro no servidor: " + ex.getMessage());
        }

    }
    
    
    private void iniciaServidor() throws IOException
    {
        this.criarServidorSocket(5555);
        while (true) {
                System.out.println("Aguardando conexão....");
                Socket socket = this.esperarConexao();  // protocolo
                System.out.println("Cliente conectado.....");
                // outro processo
                //Thread
                
                Tarefa tf = new Tarefa(socket, this);
                Thread t = new Thread(tf);
                threads.add(t);
                tarefas.add(tf);
                t.start();
               
                System.out.println("Tratando cliente conectado...");
            }
        
    }

}
