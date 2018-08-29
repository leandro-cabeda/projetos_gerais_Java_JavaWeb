/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProjetoEmpresa;

import Empresa.Departamento;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 *
 * @author Leandro
 */
public class Funcionario extends Pessoa {
   private double horas;
   private double salariohora;
   private Departamento departamento;
   private ArrayList<Depedente> depedentes=new ArrayList<>();
   DecimalFormat df=new DecimalFormat("#,##0.00");

    public double getHoras() {
        return horas;
    }

    public void setHoras(double horas) {
        this.horas = horas;
    }

    public double getSalariohora() {
        return salariohora;
    }

    public void setSalariohora(double salariohora) {
        this.salariohora = salariohora;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public ArrayList<Depedente> getDepedentes() {
        return depedentes;
    }
    
    public String mostradepedentes()
    {
        String aux=null;
        if(!depedentes.isEmpty())
        {
            aux="\nDepedentes: ";
            for(Depedente d: depedentes)
            {
                aux+=" - "+d.nome;
            }
        
        }
        return aux;
    }
    
    public String getinfo()
    {
        String aux=super.getinfo();
        if(horas!=0)
            aux+="\nHoras: "+horas;
        if(salariohora!=0)
            aux+="\nSalario hora: "+salariohora;
        
        if(departamento!=null)
        {
            aux+="\nDepartamento: "+departamento;
        }
        if(mostradepedentes()!=null)
        {
            aux+=mostradepedentes();
        }
        
        return aux;
    }
    
    public void adicionardepedente(Depedente d)
    {
        depedentes.add(d);
    }
    public void removerdepedente(Depedente d)
    {
        depedentes.remove(d);
    }
    
    
}
