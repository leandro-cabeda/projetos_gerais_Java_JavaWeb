/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Operacao;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Leandro
 */
public class Extrato{
    private int id;
    private String descricao;
    private String tipo;
    private Date data;
    private Double valor;
    private SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    } 
    
    public String toString()
    {
        String aux="";
        if(id!=0)
        {
            aux+=id;
        }
        return aux+=" ("+sdf.format(data)+" - "+ tipo+" - R$ "+valor+")";
    }
    
    
}
