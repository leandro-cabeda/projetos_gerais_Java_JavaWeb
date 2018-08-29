/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import util.Estados;
import util.Mensagem;
import util.Status;
import conta.*;

/**
 *
 * @author elder
 */
public class TrataConexao implements Runnable {

    @Override
    public void run() {
        try {
            trataConexao(socket);
        } catch (IOException ex) {
            System.out.println("Erro no tratamento do cliente: " + socket.toString() + ": " + ex.getMessage());
        } catch (ClassNotFoundException ex) {

        }
    }

    private void trataConexao(Socket socket) throws IOException, ClassNotFoundException {
        // * Cliente ------SOCKET-----servidor
        //protocolo da aplicação
        /*
         4 - Tratar a conversação entre cliente e 
         servidor (tratar protocolo);
         */

        try {

            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());

            /*protocolo
             HELLO
             nome : String
             sobrenome: String
            
             HELLOREPLY
             OK, ERRO, PARAMERROR
             mensagem : String
            
             */
            /*4 - Tratar a conversação entre cliente e 
             servidor (tratar protocolo);*/
            System.out.println("Tratando...");
            Estados estado = Estados.CONECTADO;
            while (estado != Estados.SAIR) {
                server.avisa();
                Mensagem m = (Mensagem) input.readObject();
                System.out.println("Mensagem do cliente:\n" + m);

                String operacao = m.getOperacao();
                Mensagem reply = new Mensagem(operacao + "REPLY");

                //estados conectado autenticado
                switch (estado) {
                    case CONECTADO:
                        switch (operacao) {
                            case "LOGIN":
                                try {
                                    String user = (String) m.getParam("user");

                                    if (user.equals("ALUNO")) {
                                        reply.setStatus(Status.OK);
                                        estado = Estados.AUTENTICADO;
                                    } else {
                                        reply.setStatus(Status.ERROR);
                                    }

                                } catch (Exception e) {
                                    reply.setStatus(Status.PARAMERROR);
                                    reply.setParam("msg", "Erro nos parâmetros do protocolo.");
                                }
                                break;
                            case "SAIR":
                                reply.setStatus(Status.OK);
                                estado = Estados.SAIR;
                                break;
                            default:
                                //responder mensagem de erro: Não autorizado/ou inválida
                                reply.setStatus(Status.ERROR);
                                reply.setParam("msg", "MENSAGEM NÃO AUTORIZADA OU INVÁLIDA!");

                                break;
                        }
                        break;
                    case AUTENTICADO:
                        switch (operacao) {
                            case "DEPOSITA":
                                try {
                                    int valor = (int) m.getParam("valor");

                                    conta.deposita(valor);
                                    reply.setStatus(Status.OK);

                                } catch (Exception e) {
                                    reply.setStatus(Status.PARAMERROR);
                                    reply.setParam("msg", "Erro nos parâmetros do protocolo.");
                                }
                                break;
                            case "CONSULTA":

                                int saldo = conta.consultaSaldo();
                                reply.setStatus(Status.OK);
                                reply.setParam("valor", saldo);

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
                                        saldo = conta.consultaSaldo();
                                        reply.setParam("ok", "Saldo atual de R$ " + saldo);
                                    }

                                } catch (Exception e) {
                                    reply.setStatus(Status.PARAMERROR);
                                    reply.setParam("msg", "Erro nos parâmetros do protocolo.");
                                }
                                break;
                            case "LOGOUT":
                                reply.setStatus(Status.OK);
                                estado = Estados.CONECTADO;
                                break;
                            case "SAIR":
                                //DESIGN PATTERN STATE
                                reply.setStatus(Status.OK);
                                estado = Estados.SAIR;
                                break;
                            default:
                                reply.setStatus(Status.ERROR);
                                reply.setParam("msg", "MENSAGEM NÃO AUTORIZADA OU INVÁLIDA!");
                                break;
                        }
                        break;
                    case SAIR: //ESTADP
                        break;

                }

                output.writeObject(reply);
                output.flush();//cambio do rádio amador
            }
            //4.2 - Fechar streams de entrada e saída
            input.close();
            output.close();
        } catch (IOException e) {
            //tratamento de falhas
            System.out.println("Problema no tratamento da conexão com o cliente: " + socket.getInetAddress());
            System.out.println("Erro: " + e.getMessage());
            throw e;
        } finally {
            //final do tratamento do protocolo
            /*4.1 - Fechar socket de comunicação entre servidor/cliente*/
            fechaSocket(socket);
        }

    }

    private void fechaSocket(Socket s) throws IOException {
        s.close();
    }

    private Socket socket;
    private Server server;
    private Conta conta;

    public TrataConexao(Socket socket, Server server) throws ClassNotFoundException, IOException {
        this.socket = socket;
        this.server = server;
        this.conta = Conta.getInstance();
        

    }

}
