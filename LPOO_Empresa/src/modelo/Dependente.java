/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author vanes
 */
public class Dependente{
    
    private int id;
    private String nome;
    private String sexo;
    private Date dtnasc;
    private double altura;
    private double peso;
    private Funcionario func;
    DecimalFormat df = new DecimalFormat("#,##0.##");

    @Override
    public String toString() {
        return nome;
    }
    
    public void getinfo()
    {
        String a="";
        if(nome!=null)
        {
            a+="Nome: "+nome;
        }
        if(sexo!=null)
        {
            a+="\nSexo: "+sexo;
        }
        if(dtnasc!=null)
        {
            a+="\nData de nascimento: "+dtnasc;
        }
        if(altura!=0)
        {
            a+="\nAltura: "+df.format(altura);
        }
        if(peso!=0)
        {
            a+="\nPeso: "+df.format(peso);
        }
        if(func!=null)
        {
            a+="\nFuncionário: "+func;
        }
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Date getDtnasc() {
        return dtnasc;
    }

    public void setDtnasc(Date dtnasc) {
        this.dtnasc = dtnasc;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public Funcionario getFunc() {
        return func;
    }

    public void setFunc(Funcionario func) {
        this.func = func;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + this.id;
        hash = 29 * hash + Objects.hashCode(this.nome);
        hash = 29 * hash + Objects.hashCode(this.sexo);
        hash = 29 * hash + Objects.hashCode(this.dtnasc);
        hash = 29 * hash + (int) (Double.doubleToLongBits(this.altura) ^ (Double.doubleToLongBits(this.altura) >>> 32));
        hash = 29 * hash + (int) (Double.doubleToLongBits(this.peso) ^ (Double.doubleToLongBits(this.peso) >>> 32));
        hash = 29 * hash + Objects.hashCode(this.func);
        hash = 29 * hash + Objects.hashCode(this.df);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Dependente other = (Dependente) obj;
        if (this.id != other.id) {
            return false;
        }
        if (Double.doubleToLongBits(this.altura) != Double.doubleToLongBits(other.altura)) {
            return false;
        }
        if (Double.doubleToLongBits(this.peso) != Double.doubleToLongBits(other.peso)) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.sexo, other.sexo)) {
            return false;
        }
        if (!Objects.equals(this.dtnasc, other.dtnasc)) {
            return false;
        }
        if (!Objects.equals(this.func, other.func)) {
            return false;
        }
        if (!Objects.equals(this.df, other.df)) {
            return false;
        }
        return true;
    }
    
    
    
    
}
