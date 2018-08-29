/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


//import java.util.Date;

/**
 *
 * @author vanes
 */
public class Funcionario{
    private double horas;
    private double salarioHora;
    private ArrayList<Dependente> dependentes = new ArrayList<>();
    private Departamento departamento;
    private int id;
    private String nome;
    private String sexo;
    private Date nasc;
    private double altura;
    private double peso;
    
    
    DecimalFormat df = new DecimalFormat("#,##0.##");
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    

    @Override
    public String toString() {
        return nome;
    }
    

    public double getSalarioHora() {
        return salarioHora;
    }

    public void setSalarioHora(double salarioHora) {
        this.salarioHora = salarioHora;
    }

    public ArrayList<Dependente> getDependentes() {
        return dependentes;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }
    
    
    
    public String mostraDependentes(){
        String aux = null;
        if(!dependentes.isEmpty()){
            aux = "Dependente(s): ";
            for(Dependente d: dependentes){
                aux+= "\n -"+d;
            }
        }
        return aux;
    }
    
    public String getInfo(){
        String aux =""; //super.getInfo();
        //Mostra info que tem dados de Func
        if(horas != 0)
            aux+= "\nQuantidade de Horas: "+df.format(horas);
        if(salarioHora != 0)
            aux+= "\nSalário por Hora: R$"+df.format(salarioHora);
        if(departamento != null)
        {
            aux+= "\nDepartamento: "+departamento;
            aux+="\nEmpresa: "+departamento.getEmpresa();
        }
        if(mostraDependentes() != null)
            aux += "\n"+mostraDependentes();
        if(nome!=null)
        {
            aux+="\nNome: "+nome.trim();
        }
        if(sexo!=null)
        {
            aux+="\nSexo: "+sexo;
        }
        if(nasc!=null)
        {
            aux+="\nData Nascimento: "+nasc;
            
        }
        if(altura!=0)
        {
            aux+="\nAltura: "+df.format(altura);
        }
        if(peso!=0)
        {
            aux+="\nPeso: "+df.format(peso);
        }
        return aux;
    }
    
    public void adicionarDependente(Dependente d){
        dependentes.add(d);
    }
    public void removerDependente(Dependente d){
        dependentes.remove(d);
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

    public Date getNasc() {
        return nasc;
    }

    public void setNasc(Date nasc) {
        this.nasc = nasc;
    }

    public double getHoras() {
        return horas;
    }

    public void setHoras(double horas) {
        this.horas = horas;
    }

}
