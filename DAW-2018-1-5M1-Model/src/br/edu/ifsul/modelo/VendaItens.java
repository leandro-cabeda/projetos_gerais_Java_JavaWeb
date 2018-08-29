/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.modelo;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.ForeignKey;

/**
 *
 * @author Leandro
 */
@Entity
@Table(name="venda_itens")
public class VendaItens implements Serializable {
    
    
    @Id
    @SequenceGenerator(name="seq_venda_itens",sequenceName="seq_venda_itens_id",allocationSize=1)
    @GeneratedValue(generator="seq_venda_itens",strategy=GenerationType.SEQUENCE)
    private Integer id;
    
    
    @Min(value=1, message="A quantidade não pode ser negativo")
    @NotNull(message="A quantidade deve ser informado")
    @Column(name="quantidade",columnDefinition="decimal(10,2)",nullable=false)
    private Double quantidade;
    
    
    @Min(value=0, message="O valor unitario não pode ser negativo")
    @NotNull(message="O valor unitario deve ser informado")
    @Column(name="valorUnitario",columnDefinition="decimal(10,2)",nullable=false)
    private Double valorUnitario;
    
    
    @Min(value=0, message="O valor total não pode ser negativo")
    @NotNull(message="O valor total deve ser informado")
    @Column(name="valorTotal",columnDefinition="decimal(10,2)",nullable=false)
    private Double valorTotal;
    
    
    @NotNull(message="O produto deve ser informado")
    @ManyToOne
    @JoinColumn(name="produto",referencedColumnName="id",nullable=false)
    @ForeignKey(name="fk_venda_itens_produto")
    private Produto produto;
    
    
    
    @NotNull(message="A venda deve ser informado")
    @ManyToOne
    @JoinColumn(name="venda",referencedColumnName="id",nullable=false)
    @ForeignKey(name="fk_venda_itens_venda")
    private Venda venda;
    
    public VendaItens()
    {
        
    }

    public Integer getId() {
        return id;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.id);
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
        final VendaItens other = (VendaItens) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }

    public Double getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(Double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }
    
}
