/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Leandro
 */
@ManagedBean(name="controlesalario")
@SessionScoped
public class ControleSalario implements Serializable {
    
    private String nome;
    private String salariobruto;
    private String inss;
    private String salarioliquido;
    
    
    public ControleSalario()
    {
        
    }
    
    public String irCalcularSalario()
    {
        nome="";
        salariobruto="";
        return "index?faces-redirect=true";
    }
    
    public String calcularsalario()
    {
        Double sb=Double.parseDouble(salariobruto);
        Double ins;
        Double sl;
        
        if(sb < 1556.95)
        {
            salariobruto="R$ "+getSalariobruto();
            ins= sb*8/100;
            inss="R$ "+ins;
            sl=sb-ins;
            salarioliquido="R$ "+sl;
        }
        else if(sb < 2594.93)
        {
            salariobruto="R$ "+getSalariobruto();
            ins= sb*9/100;
            inss="R$ "+ins;
            sl=sb-ins;
            salarioliquido="R$ "+sl;
        }
        else
        {
            salariobruto="R$ "+getSalariobruto();
            ins= sb*11/100;
            inss="R$ "+ins;
            sl=sb-ins;
            salarioliquido="R$ "+sl;
        }
        
        return "Salario?faces-redirect=true";
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSalariobruto() {
        return salariobruto;
    }

    public void setSalariobruto(String salariobruto) {
        this.salariobruto = salariobruto;
    }

    public String getInss() {
        return inss;
    }

    public void setInss(String inss) {
        this.inss = inss;
    }

    public String getSalarioliquido() {
        return salarioliquido;
    }

    public void setSalarioliquido(String salarioliquido) {
        this.salarioliquido = salarioliquido;
    }



    
}
