
import Util.Estados;
import Util.Mensagem;
import Util.Status;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Leandro
 */
public class Tarefa implements Runnable {

    private Socket client;
    private Server server;
    private Conta conta;

    @Override
    public void run() {
        try {

            trataConexao(client);

        } catch (IOException ex) {
            System.out.println("Erro no tratamento do cliente: " + client.toString() + " : " + ex.getMessage());
        } catch (ClassNotFoundException ce) {
            // tratar exceção
            System.out.println("Erro no cast: " + ce.getMessage());
        }

    }

    public Tarefa(Socket cl, Server ser) {
        this.client = cl;
        this.server = ser;
        this.conta = Conta.getInstance();
    }

    // 4.1 Fechar Socket
    private void fecharSocket(Socket s) throws IOException {
        s.close();

    }

    private void trataConexao(Socket socket) throws IOException, ClassNotFoundException { // Cliente .......... SOCKET ...... Servidor
        // para cada Cliente passado tratamento do SOCKET ao servidor
        // protocolo da aplicação

        try {
            // Passo 3 - criar streams de entrada e saida
            // Trata o OutputStram mais claro e automatico, de saida de rede S.O
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());

            // Trata o inputStram mais claro e automatico , de entrada de rede S.O.
            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());

            /* Protocolo
            Cliente envia Hello
            Server responde  Hello World!

            Hello
            HelloReply
            Parametro
            nome: String
            sobrenome: String
       
            Codigo Status:
            OK = deu tudo bem,
            Error = se ocorreu algum erro
            Parmerror= se faltou algum parametro ou erro parametro
            mensagem: String     mensagem que vai de volta com codigo do status
            
            Protocolo Conta:
            login:
            <user>
            
            login reply:
            ok, error, not
            
            logout:
            logout reply:
            ok, not, erro
            
            consulta:
            consulta reply:
            int <valor>
            ok, error
            
            Deposita:
            <valor>
            int
            
            Deposita reply:
            ok, paramerror
            
            Saque:
            <valor>
            int
            
            Saque reply:
            ok, error(String), paramError
            
             */
            // Passo 4- Tratar a conversaçao entre cliente e servidor (tratar protocolo)
            // Servidor recebe mensagem do cliente
            System.out.println("Tratando...");
            // Classe de Mensagem para enviar varias mensagens diferentes
            Estados estado = Estados.CONECTADO;

            while (estado != Estados.SAIR) {
                server.avisa();
                Mensagem m = (Mensagem) input.readObject();
                System.out.println("Mensagem recebida do cliente: \n" + m);

                String operacao = m.getOperacao();
                Mensagem reply = new Mensagem(operacao + " REPLY");

                // Estados Conectado  Autenticado
                switch (estado) {
                    case CONECTADO:
                        switch (operacao) {
                            case "LOGIN":
                                try {
                                    String user = (String) m.getParam("user");
                                    //String pass = (String) m.getParam("pass");

                                    //if (user.equals("aluno") && pass.equals("123")) {
                                    if (user.equals("aluno")) {
                                        reply.setStatus(Status.OK);
                                        estado = Estados.AUTENTICADO;
                                    } else {
                                        reply.setStatus(Status.ERROR);
                                    }

                                } catch (Exception e) {
                                    reply.setStatus(Status.PARAMERROR);
                                    reply.setParam("msg", "Erro nos parametros do protocolo..");
                                }
                                break;
                            /*case "Hello":
                                String nome = (String) m.getParam("nome");
                                String sobrenome = (String) m.getParam("sobrenome");

                                if (nome == null || sobrenome == null) {
                                    reply.setStatus(Status.PARAMERROR);
                                } else {
                                    reply.setStatus(Status.OK);
                                    reply.setParam("mensagem", "Hello World, " + nome + " " + sobrenome);
                                    System.out.println("Mensagem enviado para o cliente: \n" + reply);
                                }
                                break;*/
                            case "SAIR":
                                reply.setStatus(Status.OK);
                                estado = Estados.SAIR;
                                break;
                            default:
                                // mensagem de erro: Não autorizado/ ou inválida
                                reply.setStatus(Status.ERROR);
                                reply.setParam("msg", "Mensagem não Autorizada ou Inválida..");
                                break;
                        }
                        break;
                    case AUTENTICADO:
                        switch (operacao) {
                            /*case "DIV":
                                try {
                                    Integer op1 = (Integer) m.getParam("op1");
                                    Integer op2 = (Integer) m.getParam("op2");


                                    if (op2 == 0) {
                                        reply.setStatus(Status.DIVZERO);
                                    } else {
                                        reply.setStatus(Status.OK);

                                        double div = (double) op1 / op2;
                                        reply.setParam("res", div);
                                    }
                                } catch (Exception e) {
                                    reply.setStatus(Status.PARAMERROR);
                                }
                                System.out.println("Mensagem enviado para o cliente: \n" + reply);
                                break;
                            case "SUB":
                                break;
                            case "MUL":
                                break;
                            case "SOMA":
                                break;*/
                            case "DEPOSITA":
                                try {
                                    int valor = (int) m.getParam("valor");

                                    conta.deposita(valor);
                                    reply.setStatus(Status.OK);
                                    reply.setParam("deposita","Deposito feito de: "+valor);
                                    
                                } catch (Exception e) {
                                    reply.setStatus(Status.PARAMERROR);
                                    reply.setParam("msg", "Erro nos parametros do protocolo..");
                                }
                                break;
                            case "SALDO":

                                int saldo = conta.getSaldo();
                                reply.setStatus(Status.OK);
                                reply.setParam("valor","Saldo disponível é: R$ "+ saldo);
                                break;
                            case "SAQUE":
                                try {
                                    
                                    int valor = (int) m.getParam("valor");

                                    int sacar = conta.saca(valor);

                                    if (sacar == 0) {
                                        reply.setStatus(Status.ERROR);
                                        reply.setParam("error", "Não há saldo sufuciente!");
                                    } else {
                                        reply.setStatus(Status.OK);
                                        saldo = conta.getSaldo();
                                        reply.setParam("saque", "Saldo atual de R$ " + saldo);
                                    }
                                   

                                } catch (Exception e) {
                                    reply.setStatus(Status.PARAMERROR);
                                    reply.setParam("msg", "Erro nos parametros do protocolo..");
                                }
                                break;

                            case "LOGOUT":
                                reply.setStatus(Status.OK);
                                estado = Estados.CONECTADO;
                                break;
                            case "SAIR":
                                // DESIGN PATTERN STATE(Pesquisa): implementa bem mais especificado essa modelagem de orientação objetos
                                reply.setStatus(Status.OK);
                                estado = Estados.SAIR;
                                break;
                            default:
                                reply.setStatus(Status.ERROR);
                                reply.setParam("msg", "Mensagem não Autorizada ou Inválida..");
                                break;
                        }
                        break;
                    case SAIR: // estado sair
                        reply.setStatus(Status.OK);
                        estado = Estados.SAIR;
                        break;

                }

                // Servidor responde para o cliente
                output.writeObject(reply);
                output.flush(); // output.flush(); força a saida da mensagem dizendo que terminou , chegou ao fim
                // output.flush(); libera o buff para envio
            }

            // 4.2 - Fechar Streams de entrada e saida
            input.close();
            output.close();

        } catch (IOException e) {
            // tratamento de falhas
            System.out.println("Problema no tratamento da conexão do cliente IP: " + socket.getInetAddress());
            System.out.println("Erro: " + e.getMessage());
            throw e;
        } catch (IllegalArgumentException ex) {
            System.out.println("" + ex.getMessage());
            System.out.println("Informa a porta certa");

        } finally {
            // final do tratamento  do protocolo

            // 4.1 - Fechar o socket entre o cliente e o servidor
            fecharSocket(socket);
        }
    }

}
