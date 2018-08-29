/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jogo;

/**
 *
 * @author elder
 */
public class Carta {
    int valor;
    Boolean revelada;
   
    public Carta(int valor) {
        this.valor = valor;
        revelada = false;
    }

    public int getValor() {
        return valor;
    }

    public Boolean getRevelada() {
        return revelada;
    }

    public void setRevelada(Boolean revelada) {
        this.revelada = revelada;
    }
    
}
