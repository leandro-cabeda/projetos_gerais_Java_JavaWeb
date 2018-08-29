/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conta;

import Operacao.Extrato;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author Leandro
 */
public abstract class ContaBancaria {
    //private int numero;
    protected int numero;
    private String titular;
    private double limite;
    protected double saldo;
    DecimalFormat df=new DecimalFormat("#,##0.00");
    //public ArrayList<Extrato>extrato=new ArrayList();
    protected ArrayList<Extrato>extrato=new ArrayList();
    
    public ContaBancaria(String titular, int n)
    {
        this.titular=titular;
        this.numero=n;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }
    public double getLimite() {
        return limite;
    }

    public void setLimite(double limite) {
        this.limite = limite;
    }

    public double getSaldo() {
        return saldo;
    }
    
    public void sacar( double valor)
    {
        //if((this.saldo-valor)>=(limite*-1))
        if((this.saldo+limite)>=valor)
        {
            this.saldo-=valor;
            Extrato e=new Extrato();
           // e.setDescricao("Saque");
            e.setTipo("Saque");
            e.setValor(valor);
            e.setData(new Date());// puxa a data e a hora do sistema
            extrato.add(e);
        }
        else
        {
            JOptionPane.showMessageDialog(null,"Saldo Insuficiente");
        }
    }
    public void depositar( double valor)
    {
        if(valor<=5000000)
        {
            this.saldo+=valor;
            Extrato e=new Extrato();
            e.setTipo("Deposito");
            e.setValor(valor);
            e.setData(new Date());
            extrato.add(e);
        }
        else
        {
            JOptionPane.showMessageDialog(null,"Valor além do permitido");
        }
    }
    
    public String getinformacoes()
    {
       String msg="";
        if(numero>0)
            msg="Número da conta: "+numero;
        if(titular!=null)
            msg+="\nTitular: "+titular;
        if(limite>0)
            msg+="\nTitular: "+limite;
        if(saldo>0)
            msg+="\nSaldo Disponível: "+df.format(saldo);
        return msg;
    }
    
    public abstract void processar();
    
    public String getExtrato()
    {
        String aux="";
        for(Extrato e: extrato)
        {
            aux+="\n"+e;
        }
        
        aux+="\nSaldo atual: R$"+df.format(saldo);
        return aux;
    }
    
    public void transferir(ContaBancaria c,double valor)
    {
        if((this.saldo+limite)>=valor)
        {
            this.saldo-=valor;
            Extrato e=new Extrato();
           // e.setDescricao("Saque");
           e.setDescricao("Conta Destino: "+c.titular);
            e.setTipo("Transferência");
            e.setValor(valor);
            e.setData(new Date());// puxa a data e a hora do sistema
            extrato.add(e);
            c.depositar(valor);
        }
        else
        {
            JOptionPane.showMessageDialog(null,"Saldo Insuficiente");
        }
    }
    
    
    
   /* public String toString()
    {
        String msg="";
        if(numero>0)
            msg+="Número da conta: "+numero;
        if(titular!=null)
            msg+="\nTitular: "+titular;
        if(saldo>0)
            msg+="\nSaldo Disponível: "+df.format(saldo);
        return msg;
    }
*/
    
}
