/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.util.ArrayList;

/**
 *
 * @author Elder
 */
public class FilaJogadores  {
    private ArrayList<TrataConexao>  fila;
    public static FilaJogadores instance;
    public static Boolean primeiro=true;
   
    
    
    public static FilaJogadores getInstance()
    {
        if( instance == null)
            instance = new FilaJogadores();
        return instance;
    }
    private FilaJogadores() {
        fila = new ArrayList<>();
    }
    
    public Boolean verificaUltimo()
    {
        System.out.println("Entrou na função verificaUltimo\n");
        
            if(retornofila()<0 || retornofila()==null)
            {
                System.out.println("Entrou se ultimo é nullo\n");
                return true;
            }
        return false;
    }
    
    public void enfileira( TrataConexao jogador )
    {
        if(primeiro)
        {
            fila.add(jogador);
            desenfileira();
            primeiro=false;
        }
        else
        {
            fila.add(jogador);
          
        }
        
    }
    
    public Integer retornofila()
    {
        System.out.println("Quantidade jogador: "+(fila.size()+1));
        return fila.size();
    }
    
    public void desenfileira()
    {
        TrataConexao jogador = fila.remove(0);
        jogador.avisaVez();
       
        
    }

            
    
}
