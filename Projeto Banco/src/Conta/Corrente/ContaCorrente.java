/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conta.Corrente;

import Conta.ContaBancaria;
import Operacao.Extrato;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author Leandro
 */
public class ContaCorrente extends ContaBancaria {
    private double tarifa;

    public ContaCorrente(String titular, int n) {
        super(titular, n);
    }

    public double getTarifa() {
        return tarifa;
    }

    public void setTarifa(double tarifa) {
        this.tarifa = tarifa;
    }
    
    @Override
    public void processar()
    {
        if(tarifa>0)
        {
           this.saldo-=this.tarifa;
           Extrato e=new Extrato();
           e.setValor(tarifa);
           e.setData(new Date());
           e.setTipo("Saque Tarifa");
           e.setDescricao("Débito da Tarifa");
           extrato.add(e);
        }
        else
        {
            JOptionPane.showMessageDialog(null,"Valor de tarifa zerado para processar");
        }
    }
    @Override
    public String getinformacoes()
    {
        String aux=super.getinformacoes(); // pega informações da class principal
        aux+="\nTarifa: "+tarifa;
        
        return aux;
    }
    
    
    /*public String toString()
    {
        String msg="";
               if(tarifa>0)
                     msg+="Tarifa da Conta Corrente: "+tarifa;
               else
                   msg+="Tarifa zero na Conta Corrente";
        
               return msg;
    }*/
}
