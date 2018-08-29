/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conta.Poupança;

import Conta.ContaBancaria;
import Operacao.Extrato;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author Leandro
 */
public class ContaPoupanca extends ContaBancaria {
    private double rendimento;
    
    public ContaPoupanca(String titular, int n, double r)
    {
        super(titular,n);
        this.rendimento=r;
    }
    public ContaPoupanca(String t, int n)
    {
        super(t,n);
    }

    public double getRendimento() {
        return rendimento;
    }

    public void setRendimento(double rendimento) {
        this.rendimento = rendimento;
    }
    
    @Override
    public void processar()
    {
        if(rendimento>0)
        {
           this.saldo+=((saldo*rendimento)/100);
           Extrato e=new Extrato();
           e.setValor(rendimento);
           e.setData(new Date());
           e.setTipo("Depósito Rendimento");
           e.setDescricao("Rendimento Cesta");
           extrato.add(e);
        }
        else
        {
            JOptionPane.showMessageDialog(null,"Valor de rendimento zerado para processar");
        }
    }
    
    @Override
    public String getinformacoes()
    {
        String aux=super.getinformacoes(); // pega informações da class principal
        aux+="\nRendimento: "+rendimento+" %";
        
        return aux;
    }
    
    
   /* public String toString()
    {
        String msg="";
            if(rendimento>0)
                    msg+="Rendimento da Conta Poupança: "+rendimento;
            else
                msg+="Rendimento zero na Conta Poupança";
        
            return msg;
    }*/
    
}
