/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atividade.threads.pkg5n1;

import java.util.ArrayList;

/**
 *
 * @author elder
 */
public class Tarefa implements Runnable{
    
    int iInicial, iFinal, id;
    ArrayList<Integer> valores = new ArrayList<>();
    
    Boolean ePrimo;
    int contador = 1;
    Programa mestre;
    
    @Override
    public void run()
    {  
        boolean continua;
        
        System.out.println("Tarefa "+ id+ " Começou");
        do{
            continua = mestre.pedirTarefa(this);
            System.out.println("Tarefa"+id+" pedindo... ");
            
            if(continua){
                verificaPrimo();
                System.out.println("Tarefa "+id+ "recebeu: Inicio:"+iInicial+ " fim: "+iFinal);
            }else
            {
                System.out.println("Sem mais tarefas para "+ id);
            }
        }while(continua);
        
        System.out.println("Tarefa "+ id+ " terminour");
    }

    public int getiInicial() {
        return iInicial;
    }

    public void setiInicial(int iInicial) {
        this.iInicial = iInicial;
    }

    public int getiFinal() {
        return iFinal;
    }

    public void setiFinal(int iFinal) {
        this.iFinal = iFinal;
    }
    
    
    public void verificaPrimo(){
        for(int i = iInicial; i<=iFinal; i++)
        {
            //System.out.println("Tarefa "+ id + " contando "+ i);
            
            ePrimo = true;
            for(int k = 2; k<i;k++){
                if(i % k == 0){
                    ePrimo = false;
                    break;
                }
            }
            
            if(ePrimo){
                valores.add(i);
            }
        }
    }
    

    public Tarefa(int iInicial, int iFinal, int id, Programa p) {
        this.iInicial = iInicial;
        this.iFinal = iFinal;
        this.id = id;
        this.mestre = p;
    }
    
    
    public ArrayList<Integer> mostraValores(){
        return valores;
    }
    
    
    
}
