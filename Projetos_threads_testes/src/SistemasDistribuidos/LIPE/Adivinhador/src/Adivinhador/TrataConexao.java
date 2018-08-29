package Adivinhador;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TrataConexao implements Runnable {

    private Socket socket;
    private Jogador jogador;
    private Server servidor;
    private Estados estado;
    private DataInputStream input;
    private DataOutputStream output;

    public Jogador getJogador(){
        return jogador;
    }
    
    public TrataConexao(Socket socket, Jogador jogador, Server servidor) {
        try {
            this.socket = socket;
            this.jogador = jogador;
            this.servidor = servidor;
            input = new DataInputStream(socket.getInputStream());
            output = new DataOutputStream(socket.getOutputStream());
        } catch (IOException ex) {
            Logger.getLogger(TrataConexao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void trataConexao() throws IOException {
        String protocolo[] = {""};
        try {
            Boolean ligado = true;
            StringBuilder resposta = new StringBuilder();
            estado = Estados.CONECTADO;
            
            do {
                String msgCliente = input.readUTF();
                resposta = resposta.delete(0, resposta.length());
                System.out.println("Cliente disse: " + msgCliente);
                msgCliente = msgCliente.toUpperCase();

                protocolo = msgCliente.split("#");
                
                if(protocolo[0].equalsIgnoreCase("CONTAPRIMOS")){
                    if (protocolo.length != 3) {
                        resposta.append("CONTAPRIMOS#FAIL");
                    }else{
                        servidor.ContaPrimos(protocolo[1], protocolo[2], this);
                        resposta.append("CONTAPRIMOS#OK");
                    }
                }else if(protocolo[0].equalsIgnoreCase("SAIR")){
                    resposta.append("SAIRREPLY#OK");
                    servidor.removeJogador(this);
                    ligado = false;
                }else{
                    switch (estado) {
                        case CONECTADO:
                            switch (protocolo[0]) {
                                case "JOGAR":
                                    if (protocolo.length != 1) {
                                        resposta.append("JOGARREPLY#FAIL");
                                    }else{
                                        resposta.append("JOGARREPLY#OK");
                                        estado = Estados.JOGANDO;
                                    }
                                    break;
                                default:
                                    resposta.append("ERRO#MENSAGEM INVALIDA");
                                    break;
                            }
                            break;
                        case JOGANDO:
                            switch (protocolo[0]) {
                                case "APOSTAPAR":
                                    if (protocolo.length != 1) {
                                        resposta.append("APOSTAPARREPLY#FAIL");
                                    }else{
                                        servidor.jogadas++;
                                        if(servidor.jogadas % 2 == 0){
                                            jogador.setPontuacao(2);
                                            resposta.append("APOSTAPARREPLY#VITORIA");
                                        }else{
                                            jogador.setPontuacao(-1);
                                            resposta.append("APOSTAPARREPLY#DERROTA");
                                        }
                                    }
                                    break;
                                case "APOSTAIMPAR":
                                    if (protocolo.length != 1) {
                                        resposta.append("APOSTAIMPARREPLY#FAIL");
                                    }else{
                                        servidor.jogadas++;
                                        if(servidor.jogadas % 2 != 0){
                                            jogador.setPontuacao(2);
                                            resposta.append("APOSTAIMPARREPLY#VITORIA");
                                        }else{
                                            jogador.setPontuacao(-1);
                                            resposta.append("APOSTAIMPARREPLY#DERROTA");
                                        }
                                    }
                                    break;
                                case "PONTUACAO":
                                    if (protocolo.length != 1) {
                                        resposta.append("PONTUACAOREPLY#FAIL");
                                    }else{
                                        resposta.append("PONTUACAOREPLY#").append(jogador.getPontuacao());
                                    }
                                    break;
                                default:
                                    resposta.append("ERRO#MENSAGEM INVALIDA");
                                    break;
                            }
                            break;
                    }
                }
                output.writeUTF(resposta.toString());

                System.out.println("Reposta para : " + socket.getInetAddress() + ": " + resposta);

            } while (ligado);
        } catch (Exception e) {
            System.out.println("Erro no tratamento de msg: " + e.getMessage());
        } finally {
            fechaSocket(socket);
            input.close();
            output.close();
        }
    }
    
    public void fechaSocket(Socket s) throws IOException {
        s.close();
    }
    protected void enviaMensagem(String mensagem) {
        try {
            output.writeUTF(mensagem);
        } catch (IOException ex) {
            Logger.getLogger(TrataConexao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    public void run() {
        try {
            //comeca o tratamento do protocolo para este jogador
            this.trataConexao();
        } catch (IOException ex) {
            Logger.getLogger(TrataConexao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
