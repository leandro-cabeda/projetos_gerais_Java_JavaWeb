/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conta.Processa;

import Conta.ContaBancaria;
import Conta.Corrente.ContaCorrente;
import Conta.Poupança.ContaPoupanca;

/**
 *
 * @author Leandro
 */
public class Processa{
    private double saldototal;

    public double getSaldototal() {
        return saldototal;
    }

    public void bonificar(ContaBancaria conta)
    {
        double bonifica=conta.getSaldo();
        double valorB=0;
        if(conta instanceof ContaCorrente)
        {
            ContaCorrente cc=(ContaCorrente)conta;
            if(cc.getTarifa()<10)
            {
                valorB=0.05;
            }
        }
        else if(conta instanceof ContaPoupanca)
        {
            if(conta.getSaldo()>=100)
            {
                valorB=0.3;
            }
            else if(conta.getSaldo()<100)
            {
                valorB=0.1;
            }
        }
        bonifica=valorB*bonifica;
        conta.depositar(bonifica);
    }
   
    public void descontar(ContaBancaria conta)
    {
        double d=conta.getSaldo()*0.5;
        conta.sacar(d);
    }

    public void somarsaldo(ContaBancaria conta)
    {
        saldototal+=conta.getSaldo();
    }
    
    @Override
    public String toString()
    {
        String aux="";
        return  aux="Saldo Total de tudo é:\n"+saldototal;
    }
    
    
}
