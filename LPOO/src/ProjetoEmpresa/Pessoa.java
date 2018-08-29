/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProjetoEmpresa;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Leandro
 */
public class Pessoa {
   protected String nome;
   protected Date dtnasci;
   protected char sexo;
   private double altura;
   private double peso;
   SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
   DecimalFormat df=new DecimalFormat("0.00");

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDtnasci() {
        return dtnasci;
    }
    
    public int getidade()
    {
        int idade=0;
       Calendar hoje=Calendar.getInstance();
       Calendar dtNasc=Calendar.getInstance();
       dtNasc.setTime(dtnasci);
       idade=hoje.get(Calendar.YEAR)-dtNasc.get(Calendar.YEAR);
       /*if(hoje.get(Calendar.MONTH)<dtNasc.get(Calendar.MONTH))
       {
          idade--;
       }
       else if(hoje.get(Calendar.MONTH)==dtNasc.get(Calendar.MONTH))
       {
           if(hoje.get(Calendar.DAY_OF_MONTH)<dtNasc.get(Calendar.DAY_OF_MONTH))
           {
               idade--;
           }
       }*/
       dtNasc.set(Calendar.YEAR,(hoje.get(Calendar.YEAR)));
       if(hoje.before(dtNasc))
       {
           idade--;
       }
       
       return idade;
    }

    public void setDtnasci(Date dtnasci) {
        this.dtnasci = dtnasci;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        sexo=(""+sexo).toUpperCase().charAt(0);
        this.sexo = sexo;
    }
    
    public String imc()
    {
        double imc=peso/(altura*altura);
        String aux="";
        
        if(imc<18.5)
        {
            aux+= "Grau de Obesidade: Magreza";
        }
        else if(imc<25)
        {
            aux+="Grau de Obesidade: Normal";
        }
        else if(imc<30)
        {
            aux+="Grau de Obesidade: Sobrepeso";
        }
        else if(imc>=30)
        {
            aux+="Grau de Obesidade: Obesidade";
        }
        
        return df.format(imc)+"  "+aux;
    }
    
    public String getinfo()
    {
        Calendar agora= Calendar.getInstance();
        int horaAtual=agora.get(Calendar.HOUR);
        String aux="";
        
        if(horaAtual>=0 && horaAtual<=12)
                aux+="Bom dia agora são: "+horaAtual+":"+agora.get(Calendar.MINUTE)+"\n";
        else if(horaAtual<=18)
                aux+="Boa tarde agora são: "+horaAtual+":"+agora.get(Calendar.MINUTE)+"\n";
        else
                aux+="Boa noite agora são: "+horaAtual+":"+agora.get(Calendar.MINUTE)+"\n";
        
        if(!nome.isEmpty())
            aux+="Nome: "+nome;
        
        if(dtnasci !=null)
            aux+="\nData de nascimento: "+sdf.format(dtnasci);
        aux+="\nIdade: "+getidade();
        
        if(sexo!=0)
            aux+="\nSexo: "+sexo;
        
        if(peso!=0 && altura!=0)
                aux+="\n"+imc();
        
        return aux;
    }
    
    public String toString()
    {
        return nome;
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
    
}
