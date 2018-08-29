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
public class ProfRegime extends Professor implements SalarioApurado {
    
    private double salario;
    private double bonus;
     private DecimalFormat df =  new DecimalFormat("#,##0.00");

    public ProfRegime(String nome, int matricula, int idade, double valor) {
        super(nome, matricula, idade);
        if(valor>0)
        {
            this.salario=valor;
        }
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        if(salario>0)
            this.salario = salario;
    }

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    @Override
    public void salarioapurado() {
            
         this.salariotodo=salario*bonus;
    }
    
    @Override
    public String getinfo(){
        String aux = super.getinfo();
        aux += "    Salario:  "+df.format(salario);
        aux += "    Bonus:  "+bonus;
        return aux;
    }
    
    @Override
    public String toString(){
        String aux = super.getinfo();
        aux += "    Salario:  "+df.format(salario);
        aux += "    Bonus:  "+bonus;
        return aux;
    }
    
    
}
