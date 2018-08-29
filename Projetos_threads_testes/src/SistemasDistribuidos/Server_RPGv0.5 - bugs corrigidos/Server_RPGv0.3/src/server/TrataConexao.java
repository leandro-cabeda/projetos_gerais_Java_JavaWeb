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
            Personagem p = null;
            int id = 0, dado = 0;
            while (estado != Estados.SAIR) {

                Mensagem m = (Mensagem) input.readObject();

             
                String operacao = m.getOperacao();
                Mensagem reply = new Mensagem(operacao + "REPLY");

                //estados conectado autenticado
                switch (estado) {
                    case NAOJOGANDO:
                        switch (operacao) {
                            case "ENTRARNOJOGO":
                                //pega valores do personagem
                                //veriifca valores...
                                //se td certo
                                p = (Personagem) m.getParam("personagem");
                                jogo = Jogo.getJogo();
                                System.out.println("Chegou jogador " + p);
                                id = jogo.adicionaPersonagem(p);
                                //aqui neste ponto o cliente ficará "pedurado" esperando o seu oponente chegar
                                //precisa rodar 2 clientes para continuar
                                
                                JOptionPane.showMessageDialog(null, "OPONENTE : " +p.getNome()+ " com a senha :" +id);
                                
                                p.setId(id);
                                
                                if (id != 0) {
                                    estado = Estados.JOGANDO;
                                    
                                    item = jogo.sorteiaItem(id);
                                    Item iOp = jogo.getItemOponente(id);
                                    String nomeOp = jogo.getNomeOponente(id);
                                    Double vidaOp = jogo.getVidaOponente(id);

                                    //Construindo a resposta...
                                    reply.setStatus(Status.OK);
                                    reply.setParam("carta", p);
                                    reply.setParam("item", item);
                                    reply.setParam("nomeOponente", nomeOp);
                                    reply.setParam("vidaOponente", vidaOp);
                                    reply.setParam("itemOponente", iOp);
                                    reply.setParam("id", p.getId());

                                } else {
                                    reply.setStatus(Status.DENY);
                                }
                                break;
                        }
                        break;
                    case JOGANDO:
                        switch (operacao) {
                            case "JOGADA":
                                
                               estado = Estados.ESPERANDO;
                               
                                Acao a = (Acao) m.getParam("acao");
                                
                                int dado1 = new Random().nextInt(5 + 1);
                                
                                jogo.vaiParaCombate(id, item, a, dado1);
                                
                                reply.setParam("vidaOponente", jogo.getVidaOponente(id));
                                
                                reply.setParam("acaoOponente", jogo.getAcaoOponente(id));
                                reply.setParam("dadoOponente", jogo.getDadoOponente(id));
                                reply.setParam("dadoJogador", jogo.getDadoJogador(id));
                                reply.setParam("acaoJogador", jogo.getAcaoJogador(id));
                                
                                reply.setParam("vidaJogador", jogo.getVidaJogador(id));
                                
                                JOptionPane.showMessageDialog(null, jogo.getAcaoJogador(id));
                                JOptionPane.showMessageDialog(null, jogo.getAcaoOponente(id));
                                
                                int fim = jogo.verificaFim();
                                
                                if(fim == 1 || fim == 2){
                                    operacao = "SAIR";   
                                    reply = new Mensagem(operacao + "REPLY");
                                    reply.setParam("ganhador", fim);
                                    
                                   
                                    
                                }else{
                                    estado = Estados.JOGANDO;
                                    item = jogo.sorteiaItem(id);
                                    Item iOp = jogo.getItemOponente(id);
                                    String nomeOp = jogo.getNomeOponente(id);
                                    Double vidaOp = jogo.getVidaOponente(id);

                                    //Construindo a resposta...
                                    reply.setStatus(Status.OK);
                                    reply.setParam("carta", p);
                                    reply.setParam("item", item);
                                    reply.setParam("nomeOponente", nomeOp);
                                    reply.setParam("vidaOponente", vidaOp);
                                    reply.setParam("itemOponente", iOp);

                                }
                                
                                break;

                            case "SAIR":
                                reply.setStatus(Status.OK);
                                estado = Estados.SAIR;
                                break;
                            default:
                                //responder mensagem de erro: Não autorizado/ou inválida
                                reply.setStatus(Status.NOTAUTHORIZED);
                                reply.setParam("msg", "MENSAGEM NÃO AUTORIZADA OU INVÁLIDA!");

                                break;
                        }
                        break;
                    case ESPERANDO:
                        switch (operacao) {

                            default:
                                reply.setStatus(Status.NOTAUTHORIZED);
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
