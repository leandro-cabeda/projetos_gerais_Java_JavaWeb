package Adivinhador;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server {
    private ServerSocket serverSocket;
    ArrayList<TrataConexao> threadsJogadores;
    int jogadas;
    ArrayList<ContaPrimo> listaContagem;
    ArrayList<Thread> listaThread;
    
    public Server() {
        jogadas = 0;
        threadsJogadores = new ArrayList<>();
    }

    public void criaServerSocket(int porta) throws IOException {

        serverSocket = new ServerSocket(porta);
    }

    public Socket esperaConexao() throws IOException {
        return serverSocket.accept();
    }

    public void fechaServerSocket() throws IOException {
        serverSocket.close();
    }

    public static void main(String[] args) {

        Server servidor = new Server();

        try {
            //1 - Criar o servidor de conexões
            System.out.println("Criando servidor...");
            servidor.criaServerSocket(55555);
            System.out.println("ServerSocket criado.");

            do {
                //2 -Esperar o um pedido de conexão;
                //2.1 e criar uma nova conexão;
                System.out.println("Esperando Conexão...");
                Socket s = servidor.esperaConexao();
                System.out.println("Conexão estabelecida com " + s.getInetAddress());
                /*3 - Criar streams de entrada e saída;
                 4 - Tratar a conversação entre cliente e 
                 servidor (tratar protocolo);
                 */
                System.out.println("Tratando conexão de " + s.getInetAddress());

                //em outro fluxo servidor.trataConexao(s);
                servidor.criaNovoJogador(s);

                System.out.println("Encerrando conexão de " + s.getInetAddress());
                //5 - voltar para o passo 2
            } while (true);
        } catch (IOException e) {
            System.out.println("Erro no servidor: " + e.getMessage());
        } finally {
            try {
                servidor.fechaServerSocket();
            } catch (IOException ex) {
                System.out.println("Erro: " + ex.getMessage());
            }
        }
    }

    public void criaNovoJogador(Socket s) {
        //criar novo jogador e criar novo tarefa
        TrataConexao trata = new TrataConexao(s, new Jogador(threadsJogadores.size()), this);
        threadsJogadores.add(trata);

        //criar novo fluxo
        Thread t = new Thread(trata);
        //iniciar novo fluxo
        t.start();
    }

    public void removeJogador(TrataConexao trata) {
        threadsJogadores.remove(trata);
    }

    void ContaPrimos(String inicio, String fim, TrataConexao t) {
        listaContagem = new ArrayList<>();
        listaThread = new ArrayList<>();
        dividirContagem(Integer.parseInt(inicio),Integer.parseInt(fim));
        criaThread();
        startThread();
        aguardaThread();
        t.enviaMensagem(mostraThread());
    }
    
    public void dividirContagem(int vlrI, int vlrF){
        int qtd = 2;
        if(vlrF - vlrI > 5000){
            qtd = 10;
        }else if(vlrF - vlrI > 1000){
            qtd = 3;
        }
        for (int i = 0; i < qtd; i++) {
            ContaPrimo cp;
            if(i == 0){
                cp = new ContaPrimo(i, vlrI, (vlrF/qtd));
            }else{
                cp = new ContaPrimo(i, vlrI, (vlrF/qtd) + vlrI - 1);
            }
            vlrI += (vlrF/qtd);
            listaContagem.add(cp);
        }
    }
    
    public void criaThread (){
        for (ContaPrimo cont : listaContagem) {
            Thread th = new Thread(cont);
            listaThread.add(th);
        }
    }
    
    public void startThread (){
        for (Thread thread : listaThread) {
            thread.start();
        }
    }
    
    public void aguardaThread (){
        for (Thread thread : listaThread) {
            try {
                thread.join();
            } catch (InterruptedException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public String mostraThread (){
        String msg = "";
        int tot = 0;
        for (ContaPrimo c : listaContagem) {
            msg += "\nThread " + c.getId() + " contou " + c.getQtd();
            tot += c.getQtd();
        }
        msg += "\nTotalizando " + tot;
        return msg;
    }
}
