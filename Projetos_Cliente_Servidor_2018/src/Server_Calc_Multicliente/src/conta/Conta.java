/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conta;

/**
 *
 * @author Elder
 */
public class Conta {
    
    private int saldo, limite;
    private static Conta instance;
    
    
    
    public static Conta getInstance()
    {
        if( instance == null )
            instance = new Conta();
        
        
        return instance;
    }
    
    
    private Conta()
    {
        
       saldo = 0; 
    }
    
    public void deposita(int valor){
        saldo += valor;
    }
    
    public int saca(int valor){
        if( saldo >= valor ){
            saldo -= valor;
            return valor;
        }else
            return 0;
    }
    
    public int consultaSaldo(){
        return saldo;
    }
    
}
