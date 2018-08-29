/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author vanes
 */
public class Pessoa {
    protected String nome;
    protected Date dataNascimento;
    protected char sexo;
    private double peso;
    private double altura;
    
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        sexo = (""+sexo).toUpperCase().charAt(0);
        this.sexo = sexo;
    }
    
//Criar método para mostrar todas as informações que tiverem dados
    public String getInfo(){
        Calendar agora = Calendar.getInstance();
        int horaAtual = agora.get(Calendar.HOUR);
        String aux = "";
        if(horaAtual >=0 && horaAtual <=12)
            aux += "Bom dia, agora são "+horaAtual+":"+agora.get(Calendar.MINUTE)+"\n";
        else if(horaAtual <= 18)
            aux += "Boa tarde, agora são "+horaAtual+":"+agora.get(Calendar.MINUTE)+"\n";
        else
            aux += "Boa noite, agora são "+horaAtual+":"+agora.get(Calendar.MINUTE)+"\n";
        
        if(!nome.isEmpty())
            aux+= "Nome: "+nome;
        if(dataNascimento != null){
            aux += "\nData de Nascimento: "+sdf.format(dataNascimento);
            aux += "\nIdade: "+getIdade();
        }
        if(sexo != 0)
            aux += "\nSexo: "+sexo;
        
        if(peso != 0 && altura != 0)
            aux+= "\n"+calculaIMC();
        return aux;
        
    }
    public int getIdade(){
        int idade = 0;
        Calendar hoje = Calendar.getInstance();
        Calendar dtNasc = Calendar.getInstance();
        dtNasc.setTime(dataNascimento);
        idade = hoje.get(Calendar.YEAR) - dtNasc.get(Calendar.YEAR);
//        if(hoje.get(Calendar.MONTH) < dtNasc.get(Calendar.MONTH)){
//            idade--;
//        }else if(hoje.get(Calendar.MONTH) == dtNasc.get(Calendar.MONTH)){
//            if(hoje.get(Calendar.DAY_OF_MONTH) < dtNasc.get(Calendar.DAY_OF_MONTH)){
//                idade--;
//            }
//        }
        dtNasc.set(Calendar.YEAR, (hoje.get(Calendar.YEAR)));
        if(hoje.before(dtNasc))
            idade--;
        return idade;
    }
    
    
    
    public String toString(){
        return nome;
    }
    
    
//    public int getIdade(){
//        int idade;
//        Calendar dtNasc2 = Calendar.getInstance();
//        dtNasc2.setTime(dataNascimento);
//        Calendar hoje = Calendar.getInstance();
//        int anoAt = hoje.get(Calendar.YEAR);
//        int anoNasc = dtNasc2.get(Calendar.YEAR);
//        idade = anoAt - anoNasc;
//        Calendar dtAux = dtNasc2;
//        dtAux.set(Calendar.YEAR,anoAt);
//        if(hoje.before(dtAux)){//Se não chegou no dia/mês do aniversário
//            idade--;
//        }
//        
//        //System.out.println("Idade: "+idade);
//        return idade;
//    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public String calculaIMC(){
        double imc = peso / (altura*altura);
        String gimc = "IMC: "+imc;
        if(imc < 18.5)
            gimc += "\nGrau de Obesidade: Magresa";
        else if(imc < 25)
            gimc += "\nGrau de Obesidade: Normal";
        else if(imc < 30)
            gimc += "\nGrau de Obesidade: Sobrepeso";
        else
            gimc += "\nGrau de Obesidade: Obesidade";
        
        return gimc;
    }
}

