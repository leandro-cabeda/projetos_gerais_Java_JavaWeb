
package server;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
    
    private ServerSocket serverSocket;
    private int cont;
    public List<Thread> threads;
    public List<TrataConexao> clientes;
    public int jog;
    public int pont;
    
    
   

    private void iniciaServidor() throws IOException, ClassNotFoundException{
    
        this.criarServerSocket(5555);
            while (true) {
                //System.out.println("Saldo: " + conta.Conta.getInstance().consultaSaldo());
                System.out.println("Aguardando conexão...");
                Socket socket = this.esperaConexao();//protocolo
                System.out.println("Cliente conectado.");
                //Outro processo
                TrataConexao tarefa = new TrataConexao( socket, this );
                Thread th = new Thread( tarefa );
                threads.add(th);
                clientes.add(tarefa);
                
                th.start();
                
               
                System.out.println("Tratando cliente conectado...");
            }
    
    }
    

    private void criarServerSocket(int porta) throws IOException {
        serverSocket = new ServerSocket(porta);
        cont = 0;
        jog=0;
        pont=0;
        threads = new ArrayList<>();
        clientes = new ArrayList<>();
    }
    
    private Socket esperaConexao() throws IOException {
        Socket socket = serverSocket.accept();
        return socket;
    }
    
     public static void main(String[] args) throws ClassNotFoundException {
        try {

            Server server = new Server();
            server.iniciaServidor();
            
        } catch (IOException e) {
            //trata exceção
            System.out.println("Erro no servidor: " + e.getMessage());
        }
    }
    
      
    
    
    
    private void fechaSocket(Socket s) throws IOException {
        s.close();
    }

    private void enviaMsg(Object o, ObjectOutputStream out) throws IOException {
        out.writeObject(o);
        out.flush();
    }
    public synchronized void avisa()
    {
        System.out.println("Contador: " + ++cont);
    }
    
    public synchronized int jog()
    {
        return ++jog;
    }
    public synchronized int pont()
    {
        return pont;
    }

    public List<TrataConexao> getClientes() {
        return clientes;
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }
    
}
