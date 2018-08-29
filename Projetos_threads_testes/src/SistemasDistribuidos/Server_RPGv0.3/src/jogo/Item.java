/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jogo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 *  
nome : String
descricao: String
atributoAlterado: enum[ ]: DEFESA, ATAQUE, CURA
peso : float

 * @author Elder
 */
public class Item implements Serializable{
    private String nome, descricao;
    private Map<Atributo, Double> atributosAlterados;

    public Item() {
        atributosAlterados = new HashMap<>();
    }

    public Item(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
        this.atributosAlterados = new HashMap<>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    public void setAtributo( Atributo a, Double peso )
    {
        atributosAlterados.put(a, peso);
    }
    
    public Double getPesoAtributo( Atributo a )
    {
        Double temp =  atributosAlterados.get(a);
        if (temp != null)
        {
            return temp;
        }else
            return 0.0;
    }
    
    public void aplicaDado(int dado)
    {
        for (Map.Entry<Atributo, Double> entrySet : atributosAlterados.entrySet()) {
            switch(dado)
            {
                case 1:
                    entrySet.setValue(entrySet.getValue() * 0.5);
                    break;
                case 2:
                    entrySet.setValue(entrySet.getValue() * 0.75);
                    break;
                case 3:
                    entrySet.setValue(entrySet.getValue() * 1);
                    break;
                case 4:
                    entrySet.setValue(entrySet.getValue() * 1.25);
                    break;
                case 5:
                    
                    entrySet.setValue(entrySet.getValue() * 1.5);
                    break;
                case 6:
                    entrySet.setValue(entrySet.getValue() * 1.75);
                    break;
            }
            
        }
    }
    @Override
    public String toString()
    {
        String s = "Item: \n"+ nome+";\n"+
                descricao+"\nAltera:\n";
        for (Map.Entry<Atributo, Double> entrySet : atributosAlterados.entrySet()) {
            s+= entrySet.getKey() + " em " + entrySet.getValue()+"\n";            
        }
        
        return s;
    }
    
}
