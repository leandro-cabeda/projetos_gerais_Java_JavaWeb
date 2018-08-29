/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 *
 * @author vanes
 */
public class Funcionario extends Pessoa{
    private double horas;
    private double salarioHora;
    private ArrayList<Dependente> dependentes = new ArrayList<>();
    private Departamento departamento;
    
    DecimalFormat df = new DecimalFormat("#,##0.00");
    
    public double getHoras() {
        return horas;
    }

    public void setHoras(double horas) {
        this.horas = horas;
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
        String aux = super.getInfo();
        //Mostra info que tem dados de Func
        if(horas != 0)
            aux+= "\nQuantidade de Horas: "+horas;
        if(salarioHora != 0)
            aux+= "\nSalário por Hora: R$"+df.format(salarioHora);
        if(departamento != null)
            aux+= "\nDepartamento: "+departamento;
        if(mostraDependentes() != null)
            aux += "\n\n"+mostraDependentes();
        return aux;
    }
    
    public void adicionarDependente(Dependente d){
        dependentes.add(d);
    }
    public void removerDependente(Dependente d){
        dependentes.remove(d);
    }
}
