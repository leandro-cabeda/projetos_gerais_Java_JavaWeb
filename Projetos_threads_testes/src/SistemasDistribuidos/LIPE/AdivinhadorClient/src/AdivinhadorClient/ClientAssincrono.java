/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AdivinhadorClient;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author elder
 */
public class ClientAssincrono {

    private Socket socket;
    private DataInputStream input;
    private DataOutputStream output;
    private Boolean ligado;
    
    public void conectaServidor(String host, int porta) throws IOException
    {
        socket = new Socket(host, porta);
    }
    
    public void criaStreams() throws IOException
    {
        input = new DataInputStream(socket.getInputStream());
        output = new DataOutputStream(socket.getOutputStream());
    }
    
    public void trataComunicacao() throws IOException
    {
        ligado = true;
        String nome = "";

        InputHelper in = new InputHelper(input);
        Thread inThread = new Thread(in);
        inThread.start();
        System.out.println("Digite a mensagem ao servidor. \n Digite MENU a qualquer momento para verificar as opcoes.");
        
        Scanner scanner = new Scanner(System.in);
        StringBuilder msg = new StringBuilder();
            
        do{
            System.out.println("\n$ ");
            msg = msg.delete(0, msg.length()).append(scanner.nextLine());

            if(msg.toString().equalsIgnoreCase("MENU")){
                System.out.println("_________________");
                System.out.println(" ____M_E_N_U____");
                System.out.println("1 - Entrar no jogo");
                System.out.println("2 - Jogar Par");
                System.out.println("3 - Jogar Impar");
                System.out.println("4 - Pontuação");
                System.out.println("5 - Contar Primos");
                System.out.println("6 - Sair");
                System.out.println("Selecione:");
                System.out.println("_________________");
                
                msg = msg.delete(0, msg.length()).append(scanner.nextLine());
            }
            
            switch(msg.toString().toUpperCase()){
                case "1":
                    msg = msg.delete(0, msg.length()).append("JOGAR");
                break;
                case "2":
                    msg = msg.delete(0, msg.length()).append("APOSTAPAR");
                break;
                case "3":
                    msg = msg.delete(0, msg.length()).append("APOSTAIMPAR");
                break;
                case "4":
                    msg = msg.delete(0, msg.length()).append("PONTUACAO");
                break;
                case "5":
                    System.out.println("Informe o valor inicial: ");
                    int i,f;
                    try {
                        i = Integer.parseInt(scanner.nextLine());
                    } catch (Exception e) {
                        i = -1;
                    }
                    System.out.println("Informe o valor final: ");
                    try {
                        f = Integer.parseInt(scanner.nextLine());   
                    } catch (Exception e) {
                        f = -1;
                    }
                    msg = msg.delete(0, msg.length()).append("CONTAPRIMOS#").append(i).append("#").append(f);
                break;
                case "6":
                    msg = msg.delete(0, msg.length()).append("SAIR");
                break;
                case "MENU":
                    msg = msg.delete(0, msg.length()).append("ERRO");
                break;
            }
            
            output.writeUTF(msg.toString());
            if(msg.toString().toLowerCase().contains("sair")){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(ClientAssincrono.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            ligado = in.getLigado();
        }while(ligado);
    }
    
    public void fechaConexao()
    {
        try{ 
        input.close();
        output.close();
        socket.close();
        }catch(IOException e)
        {
            System.out.println("Erro ao fechar conexao: "+ e.getMessage());
        }
    }
    
    private String perguntaJogada(){
        Scanner ler = new Scanner(System.in);
        int x, y;
        
        System.out.println("Informe o X:");
        try{
            x = ler.nextInt();
        }catch(Exception e){
            x = -1;
        }
        System.out.println("Informe o Y:");
        try{
            y = ler.nextInt();
        }catch(Exception e){
            y = -1;
        }
        
        return new StringBuilder().append(x).append("#").append(y).toString();
    }
    
    public static void main(String[] args) {
        
         ClientAssincrono cliente = new ClientAssincrono();
           
        try {
            
            System.out.println("Conectando com o servidor...");
            cliente.conectaServidor("localhost", 55555);
            System.out.println("Conexão estabelecida.");
            System.out.println("Criando streams...");
            cliente.criaStreams();
            System.out.println("Streams criados.");
            System.out.println("Tratando comunicação...");
            cliente.trataComunicacao();
            System.out.println("Encerrando aplicação.");
        
        } catch (IOException ex) {
            System.out.println("Erro no cliente: "+ ex.getMessage());
        
        }finally{
                 
            cliente.fechaConexao();
        }
        
    }
    
}
