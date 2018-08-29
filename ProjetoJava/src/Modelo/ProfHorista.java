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
public class ProfHorista extends Professor implements SalarioApurado {
    
    private int totalhora;
    private double salariohora;
    private double bonus;
     private DecimalFormat df =  new DecimalFormat("#,##0.00");

    public ProfHorista(String nome, int matricula,int idade, int hora, double salariohora) {
        super(nome, matricula, idade);
        if(hora>0)
            this.totalhora=hora;
        
        if(salariohora>0)
            this.salariohora=salariohora;
    }

    public int getTotalhora() {
        return totalhora;
    }

    public void setTotalhora(int totalhora) {
        this.totalhora = totalhora;
    }

    public double getSalariohora() {
        return salariohora;
    }

    public void setSalariohora(double salariohora) {
        this.salariohora = salariohora;
    }
    
    
    public double retornasalario2()
    {
        double salariobase=salariohora*totalhora;
        
        return salariobase;
    }

    @Override
    public void salarioapurado() {
            double salbase=retornasalario2();
            
         this.salariotodo=salbase*bonus;
    }

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }
    
    @Override
    public String getinfo(){
        String aux = super.getinfo();
        aux += "    Salariohora:  "+df.format(salariohora);
        aux += "    Totalhora:  "+totalhora;
        aux += "    Bonus:  "+bonus;
        return aux;
    }
    
    
    @Override
    public String toString(){
        String aux = super.getinfo();
        aux += "   Salariohora:  "+df.format(salariohora);
        aux += "   Totalhora:  "+totalhora;
        aux += "   Bonus:  "+bonus;
        return aux;
    }
    
    
    
}
