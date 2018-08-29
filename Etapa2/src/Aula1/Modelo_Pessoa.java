
package Aula1;

import java.text.DecimalFormat;



public class Modelo_Pessoa {
   private String nome;
    private int idade;
    private double altura;
    private double peso;
    DecimalFormat df=new DecimalFormat("0.##");
    private Empresa localdetrabalho;
           
    //DecimalFormat df=new DecimalFormat("#,##0.00");
    // 0.00 é obrigação caso haver 2 casas depois da virgula, mostra 2 casas   
    //#,## é opcional caso haver 2 casas depois da virgula, mostra só numero q ta depois da virgula
    
    public static int quantidade;
    
    /*public Modelo_Pessoa(){
        nome="Fulano";
        idade=1;
        
    }*/
    
    public Modelo_Pessoa(String nome){
        this.nome=nome; // this referencia  a variavel global
        quantidade++;
    }
    
    public Modelo_Pessoa(String n,int idade)
    {
        nome=n;
        this.idade=idade; // this referencia a variavel global e não local
        quantidade++;
    }
    
    public String exibirdados()
    {
        String msg="Nome: "+nome;
        if(idade!=0)
            msg+="\nIdade: "+idade;
        if(peso!=0)
            msg+="\nPeso: "+peso +" Kg";
        if(altura!=0)
            msg+="\nAltura: "+altura+ " m";
        else if(altura!=0 && peso!=0){
            msg+="\nAltura: "+altura+ " m";
            msg+="\nPeso: "+peso +" Kg";
        }
        if(localdetrabalho!=null)
                msg+="\nLocal de Trabalho: "+localdetrabalho;
       return msg;
    }
    public Empresa getlocaltrabalho()
    {
        return localdetrabalho;
    }
    public void setlocaltrabalho(Empresa e)
    {
        localdetrabalho=e;
    }
    
    public int getidade()
    {
        return idade;
    }
    public void setaltura(double altura)
    {
        this.altura=altura;
    }
    public double getaltura()
    {
        return altura;
    }
    
    public void setpeso(double peso)
    {
        this.peso=peso;
    }
    
    public double getpeso()
    {
        return peso;
    }
    
    public double calcularIMC()
    {
        double imc=peso/(altura*altura);
        return imc;
    }
    
    public String graudeobesidade()
    {
        double imc=calcularIMC();
        String grau;
        if(imc<18.5)
        {
            grau="Você está abaixo do peso ideal";
        }
        else if(imc<25)
        {
            grau="Parabéns - Você está em seu peso ideal";
        }
        
        else if(imc<30)
        {
            grau="Você está acima de seu peso (sobrepeso)";
        }
        else if(imc<35)
        {
            grau="Obesidade Grau I";
        }
        else if(imc<40)
        {
            grau="Obesidade Grau II";
        }
        else
        {
            grau="Obesidade Grau III";
        }
       return grau; 
    }
    
    public String exibirdadosmc()
    {
        double imc=calcularIMC();
        String msg="Nome: "+nome;
               msg+="\nCom IMC:  "+df.format(imc).replace(',', '.');
               
               return msg;
    }
    
    public String getnome()
    {
        return nome;
    }
    
    public void setnome(String nome)
    {
        this.nome=nome;
    }
    
    public void fazeraniversario()
    {
        idade++;
    }
    
    public String toString()
    {
        return nome+"   "+localdetrabalho;
    }
    
}

