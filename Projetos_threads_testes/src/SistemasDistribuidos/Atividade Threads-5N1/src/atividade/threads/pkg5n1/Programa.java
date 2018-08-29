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
public class Programa {

    int iInicial, iFinal;
    int nProcs;
    ArrayList<Thread> threads;
    ArrayList<Tarefa> tarefas;
    
    
    int inicio, fim;

    ArrayList<Integer> teste = new ArrayList<>();

    public Programa(int iInicial, int iFinal, int nProcs) {
        this.iInicial = iInicial;
        this.iFinal = iFinal;
        this.nProcs = nProcs;
        threads = new ArrayList<>();
        tarefas = new ArrayList<>();
        this.inicio = 1;
        this.fim = inicio + 999;
    }

    public void processa() throws InterruptedException {

        int job = iFinal - iInicial;
        int fatia = job / nProcs;
        int inicio = iInicial;
        int fim = iInicial + fatia;
        for (int i = 0; i < nProcs; i++) {
            Tarefa t = new Tarefa(inicio, fim, i, this);
            Thread th = new Thread(t);
            th.start();

            threads.add(th);
            tarefas.add(t);

            inicio = fim + 1;

            if (i == nProcs - 1) {
                fim = iFinal;
            } else {
                fim = inicio + fatia;
            }
        }

    }

    public void pegaResultado() {
        //teste = t.mostraValores();

        for (Tarefa t : tarefas) {
            teste.addAll(t.mostraValores());
        }
        
        //System.out.println("teste: " +teste );
        for (int k = 0; k < teste.size(); k++) {
            System.out.println("\n " + teste.get(k));
        }
        System.out.println("Encontrou "+ teste.size()+ " primos." );
    }

    public void esperaThreads() throws InterruptedException {
        for (Thread t : threads) {
            t.join();
        }
    }
    
    public synchronized Boolean pedirTarefa(Tarefa t){
        
        System.out.println("\n Tarefa "+t.id+" pediu.");
        if(fim == iFinal){
            return false;
        }
        
        t.setiInicial(inicio);
        t.setiFinal(fim);
        
        this.inicio = fim + 1;
        this.fim = inicio + 999;
        
        if(fim > iFinal){
            this.fim = iFinal;
        }
        
        return true;
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        Programa p = new Programa(1, 800000, 8);
        p.processa();
        p.esperaThreads();
        p.pegaResultado();
        System.exit(0);

    }

}
