/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.util.Random;
import jogo.Acao;
import jogo.Atributo;
import jogo.Item;
import jogo.Jogo;
import jogo.Personagem;

/**
 *
 * @author Elder
 */
public class JogadaP1 implements Runnable {
    

    public static void main(String[] args) throws InterruptedException {

        //Codigo para inicializar o jogo antes dos jogadores chegarem
        
        Item i1 = new Item();
        i1.setNome("Teste Defesa");
        i1.setDescricao("Aumenta defesa");
        i1.setAtributo(Atributo.DEFESA, 10.0);
        Item i2 = new Item();
        i2.setNome("Teste Ataque");
        i2.setDescricao("Aumenta ataque");
        i2.setAtributo(Atributo.ATAQUE, 10.0);
        i2.setAtributo(Atributo.DEFESA, 20.0);
        i2.setAtributo(Atributo.CURA, 20.0);
        Jogo jogo = Jogo.getJogo();
        jogo.adicionaItem(i1);
        jogo.adicionaItem(i2);
        
        JogadaP1 p1 = new JogadaP1();
        JogadaP1 p2 = new JogadaP1();
        
        Thread th1 = new Thread(p1);
        Thread th2 = new Thread(p2);
        th1.start();
        th2.start();
        
        th1.join();
        th2.join();
        
    }

    @Override
    public void run() {
        //Código do tratamento do protocolo de UM CLIENTE
        //instanciando o jogo
        Jogo jogo = Jogo.getJogo();
        //criando jogadores
        
        Personagem p1 = new Personagem(null, null, 20.0, 30.0, 20.0, 10.0);
        String saida = "";
        //adicionando ao jogo
        int id = jogo.adicionaPersonagem(p1);
        if (id == 1) {
            saida+= "Jogador é 1\n";
        }
        if (id == 2) {
            saida +="Jogador é 2\n";
        }
        if (id == 0) {
            saida += "Sem lugar no jogo\n";
            
            System.exit(0);
        }
        p1.setId(id);
        System.out.println("Entrou o id "+ id );
        p1.setNome("Jogador "+ id);
        saida+= p1+"\n";
        String nomeOponente = jogo.getNomeOponente(id);
        System.out.println("Nome Oponente " + nomeOponente);
        //Sorteia item p1 e p2
        Item itemp1 = jogo.sorteiaItem(id);
        saida+= "Item Ganho\n";
        saida+= itemp1;
        
        Item iOp = jogo.getItemOponente(id);
        saida += "ItemOponente:\n";
        saida += iOp +"\n";

        //envia item e carta para p1 e p2
        //recebe ação
        Acao aP1 = Acao.ATAQUE;
        //soteiaDados
        int dado1 = new Random().nextInt(5) + 1;

        //faz combate
        jogo.vaiParaCombate(p1.getId(), itemp1, aP1, dado1);

        saida += "Após jogada 1\n " + p1 + "\n";
        saida+="Dado retirado: " + dado1+"\n";
        //pegar dado do oponente
        saida += "Dado do Oponente: "+jogo.getDadoOponente( id ) +"\n";
        //pegar vida do oponente
        saida+= "Vida do Oponente: " + jogo.getVidaOponente(id) + "\n";
        
        System.out.println("Saida jogador "+ p1.getNome()+"\n"+ saida);
        
        int fim = jogo.verificaFim();
        if( fim == 1 )
        {
            System.out.println("Vitória do P1");
            jogo.novaPartida();
        }else if(fim == 2)
        {
            System.out.println("Vitoria do p2");
            jogo.novaPartida();
        }else
        {
            System.out.println("Jogo não acabou");
        }

    }
}
