/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.modelo;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Leandro
 */
@Entity
@Table(name="parcela")
public class Parcela implements Serializable{
    
    @EmbeddedId
    private ParcelaID id;
    
    
    @Min(value=0, message="O valor não pode ser negativo")
    @NotNull(message="O valor deve ser informado")
    @Column(name="valor",columnDefinition="decimal(10,2)",nullable=false)
    private Double valor;
    
    
    @Temporal(TemporalType.DATE)
    @NotNull(message="O vencimento de ser informado")
    @Column(name="vencimento",nullable=false)
    private Calendar vencimento;
    
    @Column(name="valorPago",columnDefinition="decimal(10,2)")
    private Double valorPago;
    
    
    @Temporal(TemporalType.DATE)
    @Column(name="dataPagamento")
    private Calendar dataPagamento;
    

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.id);
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
        final Parcela other = (Parcela) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    public Parcela() {
    }

    public ParcelaID getId() {
        return id;
    }

    public void setId(ParcelaID id) {
        this.id = id;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Calendar getVencimento() {
        return vencimento;
    }

    public void setVencimento(Calendar vencimento) {
        this.vencimento = vencimento;
    }

    public Double getValorPago() {
        return valorPago;
    }

    public void setValorPago(Double valorPago) {
        this.valorPago = valorPago;
    }

    public Calendar getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(Calendar dataPagamento) {
        this.dataPagamento = dataPagamento;
    }
    
}
