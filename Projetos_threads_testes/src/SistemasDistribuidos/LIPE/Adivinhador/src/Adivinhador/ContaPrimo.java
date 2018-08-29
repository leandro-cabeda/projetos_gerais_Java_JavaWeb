/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Adivinhador;

/**
 *
 * @author 201423260317
 */
public class ContaPrimo implements Runnable{
    private int id;
    private int inicio;
    private int fim;
    private int qtd;

    public int getId() {
        return id;
    }
    
    public int getQtd() {
        return qtd;
    }
    
    public ContaPrimo(int id, int inicio, int fim) {
        this.id = id;
        this.inicio = inicio;
        this.fim = fim;
        qtd = 0;
    }
    
    public void contaThread(){
        for (int i = inicio; i <= fim; i++) {
            if(primo(i)){
                qtd++;
            }
        }
    }

    @Override
    public void run() {
        contaThread();
    }

    public boolean primo(int num){
        int primo = 0;
        for(int i = 2; i < num;i++){
            if(num%i==0){
                primo = 1;
            }
        }
        if(primo==0){
            return true;
        }
        else{
            return false;
        }
    }

}
