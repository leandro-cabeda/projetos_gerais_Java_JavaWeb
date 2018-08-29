
package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import util.Estados;
import util.Mensagem;
import util.Status;
import conta.*;

import jogo.StatusJogada;
import jogo.Tabuleiro;

/**
 *
 * @author elder
 */
public class TrataConexao implements Runnable {

    private Socket socket;
    private Server server;
    private Server ser;
    private Conta conta;
    private Tabuleiro tab;
    Boolean primeiro = true;
    ObjectOutputStream output;
    ObjectInputStream input;
    StatusJogada status = null;
    int jog;
    int pont;

    public FilaJogadores fila;
    Estados estado = Estados.CONECTADO;
    
    public synchronized void pont(Boolean b)
    {
        if(b)
        {
            pont+=2;
        }
        else
        {
            pont-=1;
        }
    }
    
    public int getPont()
    {
        return pont;
    }
    
    
    public int getjogador()
    {
        return jog;
    }
    
    
    
    
    public String getTodos()
    {
        String msg="";
        for(TrataConexao jo: server.getClientes())
        {
            msg+="Jogador ID: "+jo.getjogador()+ "   Pontuação desse jogador é: "+jo.getPont()+"\n";
        }
        
        return msg;
    }
    


    public TrataConexao(Socket socket, Server server) throws ClassNotFoundException, IOException {
        this.socket = socket;
        this.server = server;
        this.conta = Conta.getInstance();
        this.tab = Tabuleiro.getInstance();
        this.fila = FilaJogadores.getInstance();
        output = new ObjectOutputStream(socket.getOutputStream());
        input = new ObjectInputStream(socket.getInputStream());
        this.jog=server.jog();
        this.pont=server.pont();
       

    }
   

    @Override
    public void run() {
        try {
            trataConexao(socket);
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("Erro no tratamento do cliente: " + socket.toString() + ": " + ex.getMessage());
        }
    }

    private void trataConexao(Socket socket) throws IOException, ClassNotFoundException {
        try {
            System.out.println("Tratando...");
            
            server.avisa();
            
            
            while (estado != Estados.SAIR) {

                Mensagem m = (Mensagem) input.readObject();

                String operacao = m.getOperacao();
                Mensagem reply = new Mensagem(operacao + "REPLY");

                switch (estado) {
                    case CONECTADO:
                        switch (operacao) {
                            case "LOGIN":
                                try {
                                    String user = (String) m.getParam("user");

                                    if (user.equals("ALUNO")) {
                                        reply.setStatus(Status.OK);
                                        fila.enfileira(this);
                                        

                                    } else {
                                        reply.setStatus(Status.ERROR);
                                        reply.setParam("mensagem", "Errado o Login do usuário\n");

                                    }

                                } catch (Exception e) {
                                    reply.setStatus(Status.PARAMERROR);
                                    reply.setParam("mensagem", "Erro nos parâmetros do protocolo.\n");
                                }
                                break;
                            case "SAIR":
                                reply.setStatus(Status.OK);
                                estado = Estados.SAIR;
                                
                                break;
                            default:
                                
                                reply.setStatus(Status.ERROR);
                                reply.setParam("mensagem", "OPERAÇÃO INVÁLIDA! Você tem que esperar sua vez pra jogar\n"
                                        + "No momento só SAIR DISPONIVEL ou LOGIN caso você não entrou!\n");

                                break;
                        }
                        break;
                    case JOGANDO:
                            
                        switch (operacao) {
                            case "PONTUACAO":
                                try {
                                    reply.setStatus(Status.OK);
                                    reply.setParam("pontos", "Pontuação geral de todos é:\n"+getTodos());
                                } catch (Exception e) {
                                    reply.setStatus(Status.ERROR);
                                    reply.setParam("pontos", "Ocorreu erro: " + e+"\n");
                                }

                                break;

                            case "LOGOUT":
                                reply.setStatus(Status.OK);
                                estado = Estados.CONECTADO;
                                reply.setParam("res", "Você deu logout, foi para estado conectado com Opção Sair\n"
                                        + "ou Login caso não entrou!\n");
                                
                                break;

                            case "SAIR":
                                reply.setStatus(Status.OK);
                                estado = Estados.SAIR;
                                fila.desenfileira();

                                break;

                            default:
                                reply.setStatus(Status.ERROR);
                                reply.setParam("erro", "Operação Inválida | PONTUACAO/LOGOUT OU SAIR são as operação\n");
                                break;

                        }

                        break;

                    case VEZ:
                        switch (operacao) {
                            case "JOGADA":
                                try {
                                   Integer x = (Integer) m.getParam("x");
                                   Integer y = (Integer) m.getParam("y");

                                    if (x!=null && y!=null) {
                                        reply.setStatus(Status.OK);

                                        if (primeiro) {
                                            status = this.tab.jogada(x, y, true);
                                            primeiro = false;
                                           
                                            
                                            reply.setParam("res", "Jogando a primeira!");
                                            if (status == StatusJogada.FIMJOGO) {
                                                reply.setParam("res", "Todas Cartas foram reveladas, porém abriu novo jogo, pode jogar\n");
                                                reply.setStatus(Status.FINALJOGO);
                                                estado=Estados.VEZ;
                                                primeiro=true;
                                               
                                                this.tab.novojogo();
                                                
                                            }
                                            if (status == StatusJogada.INVALIDA) {
                                                reply.setParam("res", "Operação Inválida!! Digitou no mesmo lugar\n");
                                                reply.setStatus(Status.INVALIDA);
                                                primeiro=true;
                                            }
                                        } else {
                                            
                                            status = this.tab.jogada(x, y, false);
                                            primeiro=true;
                                            if (status == StatusJogada.INVALIDA) {
                                                reply.setParam("res", "Operação Inválida!! Digitou no mesmo lugar\n");
                                                reply.setStatus(Status.INVALIDA);
                                                primeiro=false;
                                                
                                            }
                                            else
                                            {
                                                if(status!=StatusJogada.FIMJOGO)
                                                {
                                                    

                                                    if (status == StatusJogada.VITORIA) {
                                                        estado = Estados.VEZ;
                                                        
                                                         this.pont(true);                                                                                                                                                                 
                                                       
                                                        reply.setParam("acerto", "Acertou");

                                                    } else {

                                                           
                                                           this.pont(false);
                                                           
                                                            
                                                            this.tab.menosRev();
                                                            estado = Estados.JOGANDO;
                                                            fila.enfileira(this);
                                                            fila.desenfileira();
                                                            
                                                            reply.setParam("error", "Errou");
                                                            if(fila.verificaUltimo())
                                                            {
                                                                System.out.println("Entrou no IF que verifica ultimo do Trata Conexão\n");
                                                                fila.enfileira(this);
                                                                
                                                            }

                                                    }
                                                }
                                                else
                                                {
                                                    reply.setParam("res", "Todas Cartas foram reveladas, porém abriu novo jogo, pode jogar\n");
                                                    reply.setStatus(Status.FINALJOGO);
                                                    estado=Estados.VEZ;
                                                    primeiro=true;
                                                    this.tab.novojogo();
                                                   
                                                }
                                            }
                                        }

                                        reply.setParam("tabela", "\n" + this.tab.desenhaTabuleiro());

                                    } else {
                                        reply.setStatus(Status.PARAMERROR);
                                        reply.setParam("erro", "Ocorreu erro por falta de parametro\n");
                                    }
                                } catch (Exception e) {
                                    reply.setStatus(Status.ERROR);
                                    reply.setParam("erro", "Ocorreu erro: " + e+"\n");
                                }

                                break;

                            case "PONTUACAO":
                                try {
                                    reply.setStatus(Status.OK);
                                    reply.setParam("pontos", "Pontuação é:\n"+getTodos());
                                } catch (Exception e) {
                                    reply.setStatus(Status.ERROR);
                                    reply.setParam("pontos", "Ocorreu erro: " + e+"\n");
                                }

                                break;

                            case "LOGOUT":
                                reply.setStatus(Status.OK);
                                estado = Estados.CONECTADO;
                                reply.setParam("res", "Você deu logout, foi para estado conectado com Opção Sair\n"
                                        + "ou Login caso não entrou!\n");
                               

                                break;

                            case "SAIR":
                                reply.setStatus(Status.OK);
                                estado = Estados.SAIR;
                                break;

                            default:
                                reply.setStatus(Status.ERROR);
                                reply.setParam("erro", "Operação Inválida| só | JOGADA, PONTUACAO | LOGOUT OU SAIR disponivel\n");
                                break;

                        }

                        break;

                    case SAIR:
                        reply.setStatus(Status.OK);
                        estado = Estados.SAIR;
                        
                        break;

                }

                output.writeObject(reply);
                output.flush();

            }
            
            input.close();
            output.close();
        } catch (IOException e) {
            //tratamento de falhas
            System.out.println("Problema no tratamento da conexão com o cliente: " + socket.getInetAddress());
            System.out.println("Erro: " + e.getMessage());
            throw e;
        } finally {
            fechaSocket(socket);
        }

    }

    public synchronized void avisaVez() {
        try {

            Mensagem m = new Mensagem("VEZ");
            m.setStatus(Status.OK);
            
            estado = Estados.VEZ;
            
            output.writeObject(m);
            output.flush();
            
        } catch (IOException ex) {
            System.out.println("Erro no tratamento do cliente: " + socket.toString() + ": " + ex.getMessage());
        }
    }

    private void fechaSocket(Socket s) throws IOException {
        s.close();
    }

}
