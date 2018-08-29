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
import java.util.logging.Level;
import java.util.logging.Logger;
import util.Mensagem;
import util.Status;

/**
 *
 * @author elder
 */
public class ClientDeposita {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        try {
            /*
             1. Estabelecer conexão com o servidor
             2. Trocar mensagens com o servidor
             */
            //cria a conexão entre o cliente e o servvidor
            System.out.println("Estabelecendo conexão...");
            Socket socket = new Socket("localhost", 5555);
            System.out.println("Conexão estabelecida.");

            //criação dos streams de entrada e saída
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
            System.out.println("Enviando mensagem...");
            /*protocolo
             HELLO
             nome : String
             sobrenome: String
            
             HELLOREPLY
             OK, ERRO, PARAMERROR
             mensagem : String
            
             */

            Mensagem m = new Mensagem("LOGIN");
            m.setParam("user", "ALUNO");
            m.setParam("pass", "ESTUDIOSO");

            output.writeObject(m);
            output.flush();

            m = (Mensagem) input.readObject();
            System.out.println("Resposta: " + m);
            boolean sair = false;
            while (!sair) {
                for (int i = 0; i < 1000; i++) {
                    m = new Mensagem("DEPOSITA");
                    m.setParam("valor", 10);
                    output.writeObject(m);
                    output.flush();

                    m = (Mensagem) input.readObject();
                    System.out.println("Resposta: " + m);
                }
                m = new Mensagem("CONSULTA");
                output.writeObject(m);
                output.flush();
                m = (Mensagem) input.readObject();
                System.out.println("Resposta: " + m);
                
                m = new Mensagem("SAIR");
                output.writeObject(m);
                output.flush();
                m = (Mensagem) input.readObject();
                if(m.getStatus() == Status.OK)
                    sair = true;                
            }

            input.close();
            output.close();
            socket.close();

        } catch (IOException ex) {
            System.out.println("Erro no cliente: " + ex);
            Logger.getLogger(ClientDeposita.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            System.out.println("Erro no cast: " + ex.getMessage());
            Logger.getLogger(ClientDeposita.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
