/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aula1;
import java.util.ArrayList;
/**
 *
 * @author Leandro
 */
public class Empresa {
    
   private String nome;
    private String cnpj;
    private String razaosocial;
    private ArrayList<Modelo_Pessoa> empregados=new ArrayList();
    
    public String getnome()
    {
        return nome;
    }
    public void setnome(String nome)
    {
        this.nome=nome;
    }
    public void adicionarempregado(Modelo_Pessoa p)
    {
        empregados.add(p);
        p.setlocaltrabalho(this);
    }
    public void removerempregado(Modelo_Pessoa p)
    {
        empregados.remove(p);
        p.setlocaltrabalho(null);
    }
    public String mostrarempregado()
    {
        String msg="Lista de empregados:";
        //for(int i=0;i<empregados.size();i++)
       // {
           // msg+="\n"+empregados.get(i).getnome()+" (idade: "+empregados.get(i).getidade();
       // }
       for(Modelo_Pessoa p:empregados)
       {
           msg+="\nNome: "+p.getnome()+"\n( idade: "+p.getidade()+" )";
       }
        return msg;
    }
    
    public String getcnpj()
    {
        return cnpj;
    }
    public void setcnpj(String cnpj)
    {
        this.cnpj=cnpj;
    }
    public String getrazaosocial()
    {
        return razaosocial;
    }
    public void setrazaosocial(String razaosocial)
    {
        this.razaosocial=razaosocial;
    }
    
    public String exibirdados()
    {
        String msg="";
        if(nome!=null)
                msg+="Nome:  "+nome;
        if(cnpj!=null)
                msg+="CNPJ:  "+cnpj;
        if(razaosocial!=null)
                msg+="Razão Social:  "+razaosocial;
        
        return msg;
    }
    public ArrayList<Modelo_Pessoa> getempregados()
    {
        return empregados;
    }
    
    public String toString()
    {
        return nome +"("+razaosocial+")";
    }
}
