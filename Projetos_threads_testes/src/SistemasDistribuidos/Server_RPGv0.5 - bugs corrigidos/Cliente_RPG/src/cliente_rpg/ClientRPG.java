/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente_rpg;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import jogo.Acao;
import jogo.Personagem;
import util.Mensagem;
import util.Status;

/**
 *
 * @author elder
 */
public class ClientRPG {

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
            
            Double ataque,defesa,vida,cura;
            Scanner scc = new Scanner(System.in);
            Personagem p = new Personagem();

            
            System.out.println("Qual seu NOME ??");
            String nome = scc.nextLine();
           
            System.out.printf("Pontos de ATAQUE? ");
            ataque = scc.nextDouble();
            
            System.out.printf("Pontos de DEFESA? ");
            defesa = scc.nextDouble();
            
            System.out.printf("Pontos de VIDA? ");
            vida = scc.nextDouble();
          
             System.out.printf("Pontos de CURA? ");
            cura = scc.nextDouble();
            
            

            p.setNome(nome);
            p.setAtaque(ataque);
            p.setCura(cura);
            p.setDefesa(defesa);
            p.setVida(vida);

            Mensagem m = new Mensagem("ENTRARNOJOGO");
            m.setParam("personagem", p);

            output.writeObject(m);
            output.flush();

            m = (Mensagem) input.readObject();
            System.out.println("Resposta: " + m);
            
            if(m.getParam("id").toString() == null){
                JOptionPane.showMessageDialog(null, "nulo");
            }else{
                p.setId(Integer.parseInt(m.getParam("id").toString()));
            }
            
           
            System.out.println(m.getParam("id"));

            boolean sair = false;
            while (!sair) {
                JOptionPane.showMessageDialog(null, m);

                m = new Mensagem("JOGADA");


               
                String teste = (
                         "Id: "+p.getId()
                        + "\nVida: " + p.getVida() + ""
                                + "\nCura: " + p.getCura() + ""
                                        + "\nAtaque: " + p.getAtaque() + ""
                                                + "\nDefesa: " + p.getDefesa() + ""
                                                        + "\n\nO que deseja Fazer?? "
                                                                + "\n 1 - Defender"
                                                                + "\n 2 - Se curar"
                                                                + "\n 3 - Atacar");
                
                System.out.println(teste);
                Scanner sc = new Scanner(System.in);
                int op = sc.nextInt();
                
                if (op == 1) {
                    m.setParam("acao", Acao.DEFESA);
                } else if (op == 2) {
                    m.setParam("acao", Acao.CURA);
                } else if (op == 3) {
                    m.setParam("acao", Acao.ATAQUE);
                }

                //envia os parametros que foram definidos através da variável m
                output.writeObject(m);
                output.flush(); //libera buffer para envio

                System.out.println("Mensagem " + m + " enviada.");

                m = (Mensagem) input.readObject();
                System.out.println("Resposta: " + m);

                p.setVida((Double) m.getParam("vidaJogador"));

                if (m.getOperacao().equals("SAIRREPLY")) {
                    
                    if(Integer.parseInt(m.getParam("ganhador").toString()) == p.getId()){
                        JOptionPane.showMessageDialog(null, "Parabéns " +p.getNome()+ " você foi o campeão");
                    }else{
                        JOptionPane.showMessageDialog(null, "Japonês da Federal te achou " +p.getNome()+ " !!!");
                    }
                    

                    sair = true;
                }

            }

            input.close();
            output.close();
            socket.close();

        } catch (IOException ex) {
            System.out.println("Erro no cliente: " + ex);
            Logger.getLogger(ClientRPG.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            System.out.println("Erro no cast: " + ex.getMessage());
            Logger.getLogger(ClientRPG.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
