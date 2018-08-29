package Util;


import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Leandro
 */
public class Mensagem implements Serializable{
    
    private String operacao;
    private Status status;
    
    // chave: Object
    
    Map<String,Object> params;
    
    public Mensagem( String operacao)
    {
        this.operacao=operacao;
        params=new HashMap<>();
    }
    
    public String getOperacao()
    {
        return operacao;
    }
    
    public void setStatus(Status s)
    {
        this.status=s;
    }
    
    public Status getStatus()
    {
        return status;
    }
    /*
        Chave Nome --> valor Jose     Quando der um get e o parametro se existe irá trazer o valor
        Chave Idade --> valor 35
    */
    
    public void setParam(String chave, Object valor)
    {
        params.put(chave, valor);
    }
    
    public Object getParam(String chave)
    {
        return params.get(chave);
    }
    
    @Override
    public String toString()
    {
        String m= "Operacao: "+operacao;
        m+="\nStatus: "+status;
        m+="\nParametros:\n";
        for(String p: params.keySet())
        {
            m+=p+": "+params.get(p)+"\n";
        }
        
        return m;
    }
    
}
