package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import produtos.Produtos;
import util.Estados;
import util.Mensagem;
import util.Status;

public class Server {

    public void cadastraProduto(int codigo, String nome, Double valor) {
        Produtos p = new Produtos();
        p.setCodigo(codigo);
        p.setNome(nome);
        p.setValor(valor);
    }
    
    public Produtos procuraProduto(int index, ArrayList<Produtos>listaProdutos){
        Produtos p;
        p = listaProdutos.get(index);
        return p;
    }
    
    public void removeProduto(int codigo, ArrayList<Produtos>listaProdutos){
        listaProdutos.remove(codigo);
    }
    

    private ServerSocket serverSocket;

    private void criarServerSocket(int porta) throws IOException {
        serverSocket = new ServerSocket(porta);

    }

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

    private void trataConexao(Socket socket) throws IOException, ClassNotFoundException {

        /*
            Cadastro        -       CadastroReply
                                    OK, REPETIDO
            Param:                  Param:                                   
            Produto : produto       Status : Status
            
            Procura         -       ProcuraReply
                                    OK, Error
            Param:
            Integer : index         Status : status
        
            Remove          -       RemoveReply
                                    OK, ERROR
            Param:
            Integer : index         Status : status 
        */
        
        try {
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());

            Mensagem m = (Mensagem) input.readObject();
            System.out.println("Mensagem do cliente:\n" + m);
            Status status = null;
            Produtos p = null;
            int index;
            ArrayList<Produtos> listaProdutos = new ArrayList<>();

            String operacao = m.getOperacao();
            Mensagem reply = new Mensagem(operacao + "REPLY");
            
            switch (operacao) {
                case "CADASTRO":
                    p = (Produtos) m.getParam("produto");
                    if (listaProdutos.contains(p)) {
                        status = Status.REPETIDO;
                    } else {
                        cadastraProduto(p.getCodigo(), p.getNome(), p.getValor());
                        status = Status.OK;
                    }
                    reply.setParam("status", status);
                    break;

                case "PROCURA":
                    index = (Integer) m.getParam("index");
                    p = procuraProduto(index, listaProdutos);
                    if(p != null){
                        status = Status.OK;
                        reply.setParam("produto", p);
                    }else{
                        status = Status.ERROR;
                    }
                    reply.setParam("status", status);
                    break;
                case "REMOVE":
                    index = (Integer) m.getParam("index");
                    if(listaProdutos.get(index) != null) {
                        removeProduto(index, listaProdutos);
                        status = Status.OK;
                    }else {
                        status = Status.ERROR;
                    }
                    reply.setParam("status", status);
                    break;
            }

            output.writeObject(reply);
            output.flush();//cambio do rádio amador

            input.close();
            output.close();
        } catch (IOException e) {
            System.out.println("Problema no tratamento da conexão com o cliente: " + socket.getInetAddress());
            System.out.println("Erro: " + e.getMessage());
            throw e;
        } finally {
            fechaSocket(socket);
        }
    }

    public static void main(String[] args) {
        try {

            Server server = new Server();
            System.out.println("Aguardando conexão...");
            server.criarServerSocket(5555);
            while (true) {
                Socket socket = server.esperaConexao();
                System.out.println("Cliente conectado.");

                server.trataConexao(socket);
                System.out.println("Cliente finalizado.");
            }
        } catch (IOException e) {
            System.out.println("Erro no servidor: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Erro no cast: " + e.getMessage());
        }
    }
}
