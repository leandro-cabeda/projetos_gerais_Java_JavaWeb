/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Teste;

import static java.lang.Math.random;
import java.util.Random;

/**
 *
 * @author Leandro
 */
public class Teste_Gerador_Random {
    public static void main(String[] args) {
        Random r= new Random();
        int i;
       int vetor[]=new int[10];
       String letras = "ABCDEFGHIJKLMNOPQRSTUVYWXZ";
       String res="";
       int n;
       
        
        for(i=0;i<10;i++)
        {
            vetor[i]=r.nextInt(20);
            System.out.println(vetor[i]);
        }
        
            for ( i = 0; i < 10; i++)
            {
              n=r.nextInt(letras.length());
              res+=letras.substring(n,n+1);
            }
            
            System.out.println(res);
        
    }

    
}
