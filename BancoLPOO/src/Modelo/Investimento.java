/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.text.DecimalFormat;

/**
 *
 * @author Leandro
 */
public class Investimento {
    private double saldo;
    private double valormensal;
    private double jurosmensal;
    DecimalFormat df=new DecimalFormat("#,###,##0.00");
    
    
    public void aplicar (double valor) // manipulo saldo
    {
        saldo+=valor;
    }
    public void virames() // alterar o saldo, saldo que tem mais o deposito mensal e calcular o juro
    {
        aplicar(valormensal);
         saldo+=((saldo)*(jurosmensal/100));
    }
    
    
    public void SimulaInvestimento (int tempo)
    {
        int i;
        
        for(i=0;i<tempo;i++)
        {
            virames();
        }
        
    }
    

    public double getValormensal() {
        return valormensal;
    }

    public void setValormensal(double valormensal) {
        this.valormensal = valormensal;
        
    }

    public double getJurosmensal() {
        return jurosmensal;
    }

    public void setJurosmensal(double jurosmensal) {
        this.jurosmensal = jurosmensal;
    }

    public double getSaldo() {
        return saldo;
    }
    
    public String gettotal()
    {
        String aux="";
        if(saldo!=0)
               aux+="R$: "+df.format(saldo);
        
        return aux;
    }
    
   public String getinfo()
   {
       String aux="";
       if(saldo!=0)
            aux+="Saldo: "+df.format(saldo);
       if(valormensal!=0)
            aux+="\nAplicação Mensal: "+valormensal;
       if(jurosmensal!=0)
            aux+="\nJuros Mensal: "+jurosmensal;
       
       return aux;
   }
    
}
