/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jogo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Elder
 */
public class Jogo {

    //tem dois personagens
    Personagem p1, p2, pc1, pc2;
    private static Jogo instance;
    private ArrayList<Item> itens;
    Boolean espera;
    Boolean esperaInicio;
    Integer dado1, dado2;
    Item iP1, iP2;

    /*construtor do jogo é um Singleton
    
     */
    private Jogo() {
        itens = new ArrayList<>();
        espera = true;
        esperaInicio = true;
        
        p1 = null;
        p2 = null;
    }

    public synchronized static Jogo getJogo() {
        if (instance == null) {
            instance = new Jogo();
        }
        return instance;
    }

    public void adicionaItem(Item i) {
        itens.add(i);
    }

    public synchronized Item sorteiaItem(int id) {
        Collections.shuffle(itens);
        Item i = itens.get(0);
        if (id == 1) {
            iP1 = i;
        } else {
            iP2 = i;
        }
        return i;
    }

    /*Adiciona um personagem ao jogo.
     Retorna: 
     1 - se for o primeiro personagem;
     2 - se for o 2o personagem;
     0 - se já houverem 2 personagens, não adicionando o novo personagem.
     */
    public synchronized Integer adicionaPersonagem(Personagem p) {
        int id;
        if (p1 == null) {
            p1 = p;
            id =  1;
        } else if (p2 == null) {
            p2 = p;
            id = 2;
        } else {
            id = 0;
        }
        if(esperaInicio)
        {
            esperaInicio = false;
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Jogo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if( id != 0 )
            notifyAll();
        
        return id;
        

    }

    /*verifica o fim do jogo
     Retorna
     1 - se o player 1 ganhou
     2 - se o player 2 ganhou
     0 - se ainda não acabou
     */
    public Integer verificaFim() {
        if (p1.getVida() > 0 && p2.getVida() > 0) {
            return 0;
        }
        if (p1.getVida() > p2.getVida()) {
            return 1;
        } else {
            return 2;
        }
    }

    public synchronized void vaiParaCombate(int id, Item i, Acao a, int dado) {
        try {
            //faz 2o jogador que enviou jogada
            if (espera) {
                espera = false;
                //aplica itens e acões
                if (id == 1) {
                    iP1 = i;
                    dado1 = dado;
                    i.aplicaDado(dado);
                    p1.aplicaItem(i);
                    p1.setAcao(a);
                } else {
                    iP2 = i;
                    dado2 = dado;
                    i.aplicaDado(dado);
                    p2.aplicaItem(i);
                    p2.setAcao(a);
                }
                wait();
            } else {
                if (id == 1) {
                    iP1 = i;
                    dado1 = dado;
                    i.aplicaDado(dado);
                    p1.aplicaItem(i);
                    p1.setAcao(a);
                } else {
                    iP2 = i;
                    dado2 = dado;
                    i.aplicaDado(dado);
                    p2.aplicaItem(i);
                    p2.setAcao(a);
                }

                //combate
                //primeiro aplica cura
                p1.aplicaCura();
                p2.aplicaCura();
                //aplica defesa
                Double defesaP1 = p1.getDefesa();
                Double defesaP2 = p2.getDefesa();
                if (p1.getAcao() == Acao.DEFESA) {
                    defesaP1 += (defesaP1 / 2);
                }
                if (p2.getAcao() == Acao.DEFESA) {
                    defesaP2 += (defesaP2 / 2);
                }
                //aplica ataque 
                if (p1.getAcao() == Acao.ATAQUE) {
                    Double ataqueP1 = p1.getAtaque();
                    Double danoP2 = ataqueP1 - (ataqueP1 * defesaP2 / 100);
                    if (danoP2 > 0) {
                        p2.setVida(p2.getVida() - danoP2);
                    } else {
                        p1.setVida(p1.getVida() + danoP2);
                    }
                }
                //ataque do p2           
                if (p2.getAcao() == Acao.ATAQUE) {
                    Double ataqueP2 = p2.getAtaque();
                    Double danoP1 = ataqueP2 - (ataqueP2 * defesaP1 / 100);
                    if (danoP1 > 0) {
                        p1.setVida(p1.getVida() - danoP1);
                    } else {
                        p2.setVida(p2.getVida() + danoP1);
                    }
                }
                //retira valores do item do personagem
                p1.retiraItem();
                p2.retiraItem();
                //retira Defesa , se houverem
                if (p1.getAcao() == Acao.DEFESA) {
                    defesaP1 -= (defesaP1 / 2);
                }
                if (p2.getAcao() == Acao.DEFESA) {
                    defesaP2 -= (defesaP2 / 2);
                }
                //não retira atributo de vida
                //acorda thread parada
                espera = true;
                notifyAll();
            }
        } catch (Exception e) {
            System.out.println("Erro no combate. " + e.getMessage());
        }

    }

    public static void main(String[] args) {
        //criando itens
        Item i1 = new Item();
        i1.setNome("Teste Defesa");
        i1.setDescricao("Aumenta defesa");
        i1.setAtributo(Atributo.DEFESA, 10.0);
        Item i2 = new Item();
        i2.setNome("Teste Ataque");
        i2.setDescricao("Aumenta ataque");
        i2.setAtributo(Atributo.ATAQUE, 10.0);
        Jogo jogo = Jogo.getJogo();
        jogo.adicionaItem(i1);
        jogo.adicionaItem(i2);

        Thread t1 = new Thread(new Runnable() {

            @Override
            public void run() {
                //Código do tratamento do protocolo de UM CLIENTE
                //instanciando o jogo
                Jogo jogo = Jogo.getJogo();
                //criando jogadores
                Personagem p1 = new Personagem(null, "P1", 20.0, 30.0, 20.0, 10.0);

                //adicionando ao jogo
                int id = jogo.adicionaPersonagem(p1);
                if (id == 1) {
                    System.out.println("Jogador é 1");
                }
                if (id == 2) {
                    System.out.println("Jogador é 2");
                }
                if (id == 0) {
                    System.out.println("Sem lugar no jogo");
                    System.exit(0);
                }
                p1.setId(id);
                System.out.println(p1);

                //Sorteia item p1 e p2
                Item itemp1 = jogo.sorteiaItem(id);
                System.out.println("Item Ganho");
                System.out.println(itemp1);

                //envia item e carta para p1 e p2
                //recebe ação
                Acao aP1 = Acao.ATAQUE;
                //soteiaDados
                int dado1 = new Random().nextInt(5) + 1;

                //faz combate
                jogo.vaiParaCombate(p1.getId(), itemp1, aP1, dado1);

                System.out.println("Após jogada 1\n " + p1);
                System.out.println("Dado retirado: " + dado1);
                //pegar dado do oponente
                //pegar vida do oponente
                System.out.println("");
            }
        });
        t1.start();

        //Código do tratamento do protocolo de UM CLIENTE
        //instanciando o jogo
        //Jogo jogo = Jogo.getJogo();
        //criando jogadores
        Personagem p1 = new Personagem(null, "P1", 20.0, 30.0, 20.0, 10.0);

        //adicionando ao jogo
        int id = jogo.adicionaPersonagem(p1);
        if (id == 1) {
            System.out.println("Jogador é 1");
        }
        if (id == 2) {
            System.out.println("Jogador é 2");
        }
        if (id == 0) {
            System.out.println("Sem lugar no jogo");
            System.exit(0);
        }
        p1.setId(id);
        System.out.println(p1);

        //Sorteia item p1 e p2
        Item itemp1 = jogo.sorteiaItem(id);
        System.out.println("Item Ganho");
        System.out.println(itemp1);

        //envia item e carta para p1 e p2
        //recebe ação
        Acao aP1 = Acao.ATAQUE;
        //soteiaDados
        int dado1 = new Random().nextInt(5) + 1;

        //faz combate
        jogo.vaiParaCombate(p1.getId(), itemp1, aP1, dado1);

        System.out.println("Após jogada 1\n " + p1);
        System.out.println("Dado retirado: " + dado1);
        //pegar dado do oponente
        //pegar vida do oponente
        System.out.println("");
        //envia resultados
    }

    public int getDadoOponente(int id) {
        if (id == 1) {
            return dado2;
        } else {
            return dado1;
        }
    }
    
    public int getDadoJogador(int id) {
        if (id == 1) {
            return dado1;
        } else {
            return dado2;
        }
    }

    public Double getVidaOponente(int id) {
        if (id == 1) {
            return p2.getVida();
        } else {
            return p1.getVida();
        }
    }
    
    public Double getVidaJogador(int id) {
        if (id == 1) {
            return p1.getVida();
        } else {
            return p2.getVida();
        }
    }

    public void novaPartida() {
        instance = new Jogo();
    }

    public synchronized String getNomeOponente(int id) {
        
        if (id == 1) {
            return p2.getNome();
        } else {
            return p1.getNome();
        }

    }

    public synchronized Item getItemOponente(int id) {

        if (id == 1) {
            if (iP2 == null) {
                try {
                    wait();
                } catch (InterruptedException ex) {
                    Logger.getLogger(Jogo.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            notifyAll();
            return iP2;
        } else {
            if (iP1 == null) {
                try {
                    wait();
                } catch (InterruptedException ex) {
                    Logger.getLogger(Jogo.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            notifyAll();
            return iP1;
        }
    }
    
    public Acao getAcaoOponente(int id) {
        if (id == 1) {
            return p2.getAcao();
        } else {
            return p1.getAcao();
        }
    }
    
    public Acao getAcaoJogador(int id) {
        if (id == 1) {
            return p1.getAcao();
        } else {
            return p2.getAcao();
        }
    }
    
    

}
