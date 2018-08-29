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
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import util.Estados;
import util.Mensagem;
import util.Status;
import jogo.*;

/**
 *
 * @author elder
 */
class TrataConexao implements Runnable {

    Socket socket;
    Server server;
    Jogo jogo;
    Usuario usuario;

    public TrataConexao(Socket socket, Server server) {
        this.socket = socket;
        this.server = server;
    }

    private void trataConexao(Socket socket) throws IOException, ClassNotFoundException {
        // * Cliente ------SOCKET-----servidor
        //protocolo da aplicação
        /*
         4 - Tratar a conversação entre cliente e 
         servidor (tratar protocolo);
         */

        try {
            /* 3 - Criar streams de entrada e saída;*/

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
            Estados estado = Estados.NAOJOGANDO;
            /*vars do jogo */
            Item item = null;
            int id = 0, dado = 0;

            while (estado != Estados.SAIR) {

                Mensagem m = (Mensagem) input.readObject();

                //System.out.println("Mensagem do cliente:\n" + m);
                String operacao = m.getOperacao();
                JOptionPane.showMessageDialog(null, "operacao: " + operacao);
                Mensagem reply = new Mensagem(operacao + "REPLY");

                //estados conectado autenticado
                switch (estado) {
                    case NAOJOGANDO:
                        switch (operacao) {
                            case "LOGIN":

                                usuario = (Usuario) m.getParam("usuario");

                                if (usuario.getUser().equals("user") && usuario.getPass().equals("pass")) {
                                    reply.setStatus(Status.OK);
                                } else if (usuario.getUser() == null || usuario.getPass() == null) {
                                    reply.setStatus(Status.PARAMERROR);
                                } else {
                                    reply.setStatus(Status.ERROR);
                                }

                                jogo = Jogo.getJogo();
                                JOptionPane.showMessageDialog(null, "aqui 1");

                                if (reply.getStatus() == Status.OK) {
                                    estado = Estados.JOGANDO;
                                    operacao = "CADASTRO";
                                } else {
                                    reply.setStatus(Status.ERROR);
                                }
                                break;
                        }
                        break;
                    case JOGANDO:
                        switch (operacao) {
                            case "CADASTRO":

                                Produto p = new Produto();

                                Acao a = (Acao) m.getParam("acao");
                                p = (Produto) m.getParam("produto");

                                JOptionPane.showMessageDialog(null, "acao recebida: " + a);

                                if (a == Acao.INSERIR) {
                                    jogo.adicionaProduto(p);
                                } else if (a == Acao.REMOVER) {
                                    jogo.removerProduto(p.getCodigo());
                                } else if (a == Acao.CONSULTAR) {
                                    jogo.pesquisarProduto(p.getCodigo());
                                } else if (a == Acao.LISTAR) {
                                    jogo.listarProdutos();
                                } else if (a == Acao.SAIR) {
                                    operacao = "SAIR";
                                    reply.setStatus(Status.OK);
                                    estado = Estados.SAIR;
                                    reply.setParam("sair", "1");
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
                    case ESPERANDO:
                        switch (operacao) {

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

    @Override
    public void run() {
        System.out.println("Iniciando thread do cliente +" + socket.getInetAddress());
        try {
            trataConexao(socket);
        } catch (IOException ex) {
            Logger.getLogger(TrataConexao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TrataConexao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
