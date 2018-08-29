/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Leandro
 */
public class Mapeamento_Chave implements Serializable {
    
    private static Map<String,Object> params;
    private Object ob;
    private String key;

    public Mapeamento_Chave() 
    {
        params=new HashMap<>();
    }
    
    public void alterarChave(String chave, Object novovalor)
    {
        ob=params.get(chave); // pega o valor da chave
        key=""+params.keySet().equals(ob); // pegar a chave do objeto
        params.put(key,novovalor); // altera o valor da chave pega
    }
    
    public void removerChave(String chave)
    {
        params.remove(chave);
    }

    public void setParam(String chave, Object valor)
    {
        params.put(chave, valor);
    }
    
    public Object getParam(String chave)
    {
        return params.get(chave);
    }
    
    
    
}
