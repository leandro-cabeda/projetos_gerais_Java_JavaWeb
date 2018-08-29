/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jogo;

import java.io.Serializable;

/**
 * 
nome : String
ataque : Float
defesa : Float
cura : Float
vida: Float

 * @author Elder
 */
public class Personagem implements Serializable{

    private Integer id;
    private String nome;
    private Double ataque, defesa, vida, cura;
    private Item aplicado;
    private Acao acao;

    public Acao getAcao() {
        return acao;
    }

    public void setAcao(Acao acao) {
        this.acao = acao;
    }

    public Personagem() {
    }

    public Personagem(Integer id, String nome, Double ataque, Double defesa, Double vida, Double cura) {
        this.id = id;
        this.nome = nome;
        this.ataque = ataque;
        this.defesa = defesa;
        this.vida = 100.0;
        this.cura = cura;
    }

    public void aplicaCura()
    {
        if(acao == Acao.CURA)
            vida += cura;
    }
    public void aplicaItem(Item i)
    {
       aplicado = i;
       cura += aplicado.getPesoAtributo(Atributo.CURA);
       ataque += aplicado.getPesoAtributo(Atributo.ATAQUE);
       defesa += aplicado.getPesoAtributo(Atributo.DEFESA);       
    }
    
    public void retiraItem()
    {
        //retira todos os itens, menos a vida
       cura -= aplicado.getPesoAtributo(Atributo.CURA);
       ataque -= aplicado.getPesoAtributo(Atributo.ATAQUE);
       defesa -= aplicado.getPesoAtributo(Atributo.DEFESA);
    }
    
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getAtaque() {
        return ataque;
    }

    public void setAtaque(Double ataque) {
        this.ataque = ataque;
    }

    public Double getDefesa() {
        return defesa;
    }

    public void setDefesa(Double defesa) {
        this.defesa = defesa;
    }

    public Double getVida() {
        return vida;
    }

    public void setVida(Double vida) {
        this.vida = vida;
    }

    public Double getCura() {
        return cura;
    }

    public void setCura(Double cura) {
        this.cura = cura;
    }

    @Override
    public String toString() {
        String s = "Carta Jogador " + id + ":\n" + nome + "\n";
        s+= "Vida: "+ vida+"\n";
        s+= "Ataque: " +ataque+ "\n";
        s+= "Defesa: " +defesa +"\n";
        s+= "Cura: " +cura +"\n";
        
        return s;
    }
    
    
    
}
