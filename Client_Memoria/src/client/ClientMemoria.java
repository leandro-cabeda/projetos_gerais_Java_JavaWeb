/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;
import util.Mensagem;
import util.Status;

/**
 *
 * @author Elder
 */
public class ClientMemoria {

    Socket socket;
    Thread thread;
    ObjectInputStream input;
    ObjectOutputStream output;
    Boolean ligado;

    public ClientMemoria() {
    }

    public void conectaServidor(String host, int porta) throws IOException {

        socket = new Socket(host, porta);
        input = new ObjectInputStream(socket.getInputStream());
        output = new ObjectOutputStream(socket.getOutputStream());
        ligado = true;

    }

    public void disparaThread() {

        thread = new Thread() {
            @Override
            public void run() {
                while (ligado) {
                    try {

                        Mensagem m = (Mensagem) input.readObject();

                        System.out.println(m + "\n");

                        if (m.getOperacao().equals("SAIRREPLY") && m.getStatus() == Status.OK) {
                            
                            desliga();
                        }
                    } catch (IOException | ClassNotFoundException ex) {
                        System.out.println("Erro ao receber mensagem do servidor: " + ex.getMessage());

                    }
                }
            }
        };
        thread.start();
    }

    public void controlaJogo() throws IOException {
        Integer va1;
        Integer va2;
        Mensagem m=null;
        int op;

        while (ligado) {
            Scanner scanner = new Scanner(System.in);

            System.out.println("Digite uma opção: ");
            System.out.println("1 - Fazer uma jogada");
            System.out.println("2 - Pedir pontuação");
            System.out.println("3 - Logout");
            System.out.println("4 - Sair");

            op = scanner.nextInt();
            switch (op) {
                case 1:
                    try{
                        m = new Mensagem("JOGADA");
                        System.out.println("Digite valor de X:\n");
                        va1 = scanner.nextInt();
                        m.setParam("x", va1);
                        System.out.println("\nDigite valor de Y:\n");
                        va2 = scanner.nextInt();
                        m.setParam("y", va2);
                    }catch(Exception e)
                    {
                        System.out.println("Erro de enviar para o servidor!\n"
                                + " Atente na mensagem que recebeu do Servidor");
                    }

                    break;
                case 2:
                    m = new Mensagem("PONTUACAO");
                    break;
                case 3:
                      try{
                        m = new Mensagem("LOGOUT");
                      }catch(Exception e)
                      {
                          System.out.println("Erro de enviar para o servidor, poise vc ja deu logout!\n"
                                + " Atente na mensagem que recebeu do Servidor");
                      }
                    
                    break;
                case 4:
                    m = new Mensagem("SAIR");
                    desliga();
                    break;
                default:
                    System.out.println("Opção inválida!!!");
                    m = new Mensagem("INVALIDO");
                    break;
            }
            output.writeObject(m);
            output.flush();

        }

    }

    public void desliga() {
        ligado = false;
    }

    public void liga() {
        ligado = true;
    }

    public void fazLogin() {
        try {
            Mensagem m = new Mensagem("LOGIN");
            m.setParam("user", "ALUNO");
            output.writeObject(m);
            output.flush();

            m = (Mensagem) input.readObject();
            System.out.println("Resposta: " + m);
        } catch (IOException ex) {
            System.out.println("Erro ao fazer login: " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.out.println("Erro ao fazer login: " + ex.getMessage());
        }

    }

    public static void main(String[] args) {
        ClientMemoria cliente = new ClientMemoria();

        try {
            cliente.conectaServidor("localhost", 5555);
            cliente.fazLogin();
            cliente.disparaThread();
            cliente.controlaJogo();

        } catch (IOException ex) {
            System.out.println("Erro no cliente: " + ex.getMessage());
            cliente.desliga();
        }

    }

}
