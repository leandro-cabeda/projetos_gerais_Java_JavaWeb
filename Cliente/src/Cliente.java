
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
public class Cliente {

    public static void main(String[] args) {
        try {
            /*
            1 - Estabelecer conexão com servidor
            2 - Trocar mensagem com Servidor
             */

            // Cliente enviar Hello
            // Cria a conexão entre o cliente e o servidor
            System.out.println("Estabelecendo conexão....");
            //Socket socket= new Socket("10.5.6.147",5555); //acessar outro servidor na outra máquina
            Socket socket = new Socket("localhost", 5555);
            System.out.println("Conexão estabelecida");

            // Criação dos strams de entrada e saida
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
            System.out.println("Enviando mensagem ....");

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
            
             */
            // Cliente envia mensagem ao servidor
            // Classe de Mensagem para enviar varias mensagens diferentes
            Mensagem m = new Mensagem("LOGIN");
            m.setParam("user", "aluno");
            //m.setParam("user", "ALUNO");
            //m.setParam("pass", "123");
            //m.setParam("pass", "ESTUDIOSO");

            output.writeObject(m);
            output.flush();

            m = (Mensagem) input.readObject();
            System.out.println("Resposta: " + m);

            /*output.writeObject(m);
            output.flush();

            m = (Mensagem) input.readObject();
            if (m.getStatus() == Status.OK) {
                //Mensagem m2 = new Mensagem("DIV");
                Mensagem m2 = new Mensagem("DEPOSITA");
                //m.setStatus(Status.SOLICITACAO);
                // m.setParam("nome","Leandro");
                //m.setParam("sobrenome","Cabeda");
                //m2.setParam("op1", 2);
                //m2.setParam("op2", 1);
                m2.setParam("valor", 2700.00);
                //String resposta=(String)m.getParam("mensagem");
                //String m="Olá professor, Cabeda";
                output.writeObject(m2);  // passa o objeto para enviar pra rede do servidor
                System.out.println("Mensagem:\n" + m2 + "enviada para o servidor");
                output.flush(); // output.flush(); força a saida da mensagem dizendo que terminou , chegou ao fim
                // output.flush(); libera o buff para envio
                m = (Mensagem) input.readObject();
                //Cliente recebe a mensagem do servidor e print na tela

                if (m.getStatus() == Status.OK) {
                    //String resposta=(String)m.getParam("mensagem");
                    //double resposta = (double) m.getParam("res");
                    double resposta = (double) m.getParam("valor");
                    System.out.println("Mensagem recebida do servidor:\n" + resposta);
                } else {
                    //System.out.println("Mensagem recebida do servidor: Erro " + m.getStatus() + " " + m.getParam("msg"));
                    System.out.println("Mensagem recebida do servidor: Erro " + m.getStatus() + " " + m.getParam("valor"));
                }

            } else {
                System.out.println("Mensagem recebida do servidor: Erro " + m.getStatus() + " " + m.getParam("msg"));
            }*/
            boolean sair = false;

            //Mensagem m3 = new Mensagem("SAIR");
            while (!sair) {
                for (int i = 0; i < 1000; i++) {
                    m = new Mensagem("DEPOSITA");
                    m.setParam("valor", 50);
                    output.writeObject(m);
                    output.flush();

                    m = (Mensagem) input.readObject();
                    System.out.println("Resposta " + m + "\n");

                    if (i%4==0) {
                        m = new Mensagem("SALDO");
                        output.writeObject(m);
                        output.flush();

                        m = (Mensagem) input.readObject();
                        System.out.println("Resposta " + m + "\n");
                    }
                }
                m = new Mensagem("SALDO");
                output.writeObject(m);
                output.flush();

                m = (Mensagem) input.readObject();
                System.out.println("Resposta " + m + "\n");

                m = new Mensagem("SAIR");
                output.writeObject(m);
                output.flush();

                m = (Mensagem) input.readObject();
                if (m.getStatus() == Status.OK) {
                    sair = true;
                }

            }

            // Fechar Streams de entrada e saida
            input.close();
            output.close();
            // fechar socket
            socket.close();

        } catch (IOException ex) {
            System.out.println("Erro no Cliente: " + ex);
        } catch (ClassNotFoundException ex) {
            System.out.println("Erro no cast: " + ex.getMessage());
        }

    }

}
