/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Emprestimo;

import Livro.Livro;
import Pessoa.Pessoa;

/**
 *
 * @author Leandro
 */
public class Emprestimo {
    private String dataemp;
    private String datadevo;
    private Livro l;
    private Pessoa p;
    
    public void setemp(String emp)
    {
        this.dataemp=emp;
    }
    public String getemp()
    {
        return dataemp;
    }
    public void setdevo(String devo)
    {
        this.datadevo=devo;
    }
    public String getdevo()
    {
        return datadevo;
    }
    public void setlivro(Livro livro)
    {
        l=livro;
    }
    public Livro getlivro()
    {
        return l;
    }
    public void setpessoa(Pessoa pessoa)
    {
        p=pessoa;
    }
    public Pessoa getpessoa()
    {
        return p;
    }
    public String info()
    {
        String aux="";
        aux += "Livro: "+l;
        aux += "\nPessoa: "+p;
        return aux;
    }
    
}
