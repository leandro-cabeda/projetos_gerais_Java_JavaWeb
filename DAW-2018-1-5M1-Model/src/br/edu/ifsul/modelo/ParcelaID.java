/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.modelo;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.ForeignKey;

/**
 *
 * @author Leandro
 */
@Embeddable
public class ParcelaID implements Serializable{
    
    @NotNull(message="A venda deve ser informado")
    @ManyToOne
    @JoinColumn(name="venda",referencedColumnName="id",nullable=false)
    @ForeignKey(name="fk_venda_itens_venda")
    private Venda venda;
    
    
    
    @NotNull(message="O numero da parcela deve ser informado")
    @JoinColumn(name="numero",referencedColumnName="id",nullable=false)
    private Integer numero;
    
    public ParcelaID(Venda venda,Integer numero)
    {
        this.venda=venda;
        this.numero=numero;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.venda);
        hash = 83 * hash + Objects.hashCode(this.numero);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ParcelaID other = (ParcelaID) obj;
        if (!Objects.equals(this.venda, other.venda)) {
            return false;
        }
        if (!Objects.equals(this.numero, other.numero)) {
            return false;
        }
        return true;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }
    
}
