/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Teste;

import Modelo.Investimento;

/**
 *
 * @author Leandro
 */
public class TesteInvestimento {
    public static void main(String[] args) {
        Investimento i= new Investimento();
        
        i.setValormensal(100);
        i.setJurosmensal(1);
        i.aplicar(1000);
        System.out.println("Saldo: "+i.getSaldo());
        i.virames();
        System.out.println("Saldo: "+i.getSaldo());
        
    }
}
