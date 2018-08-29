/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.ForeignKey;

/**
 *
 * @author Leandro
 */
@Entity
@Table(name="venda")
public class Venda implements Serializable {
    
    @Id
    @SequenceGenerator(name="seq_venda",sequenceName="seq_venda_id",allocationSize=1)
    @GeneratedValue(generator="seq_venda",strategy=GenerationType.SEQUENCE)
    private Integer id;
    
    
    @Temporal(TemporalType.DATE)
    @NotNull(message="A data deve ser informado")
    @Column(name="data",nullable=false)
    private Calendar data;
    
    
    @Min(value=0, message="O valorTotal não pode ser negativo")
    @NotNull(message="O valorTotal não pode ser nulo")
    @Column(name="valorTotal",columnDefinition="decimal(10,2)",nullable=false)
    private Double valorTotal;
    
    
    @Min(value=1, message="A quantidade de parcela não pode ser negativa")
    @NotNull(message="A quantidade de parcela deve ser informado")
    @Column(name="quantidadeParcela",nullable=false)
    private Integer quantidadeParcela;
    
    
    @NotNull(message="O cliente deve ser informado")
    @ManyToOne
    @JoinColumn(name="cliente",referencedColumnName="id",nullable=false)
    @ForeignKey(name="fk_venda_cliente")
    private PessoaFisica cliente;
    
    
    
    @NotNull(message="O vendedor deve ser informado")
    @ManyToOne
    @JoinColumn(name="vendedor",referencedColumnName="id",nullable=false)
    @ForeignKey(name="fk_venda_vendedor")
    private Usuario vendedor;
    
    
    @OneToMany(mappedBy="venda",cascade=CascadeType.ALL,orphanRemoval=true,fetch=FetchType.LAZY)
    private List<VendaItens> itens= new ArrayList<>();
    
   
    @OneToMany(mappedBy="id.venda",cascade=CascadeType.ALL,orphanRemoval=true,fetch=FetchType.LAZY)
    private List<Parcela> parcelas;
    
    public Venda()
    {
        this.valorTotal=0.0;
    }
    
    
    public void gerarParcelas()
    {
        Double valorParcela=this.valorTotal/this.quantidadeParcela;
        
        for (int i=1; i<=this.quantidadeParcela;i++)
        {
            Parcela p=new Parcela();
            ParcelaID id=new ParcelaID(this,i);
            p.setId(id);
            // fazendo um clone da data da venda
            Calendar vencimento=(Calendar)this.data.clone();
            // adicionando mes a data do vencimento
            vencimento.add(Calendar.MONTH,i);
            p.setVencimento(vencimento);
            p.setValor(valorParcela);
            this.parcelas.add(p);
        }
    }
    
    public void adicionarItem(VendaItens obj)
    {
        obj.setVenda(this);
        this.valorTotal+=obj.getValorTotal();
        this.itens.add(obj);
    }
    
    public void removerItem(int index)
    {
        // obtendo o objeto pelo indice na Lista
        VendaItens obj=this.itens.get(index);
        
        // ajustando o valor total
        this.valorTotal-=obj.getValorTotal();
        
        // removendo o objeto da lista pelo indice
        this.itens.remove(index);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.id);
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
        final Venda other = (Venda) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Calendar getData() {
        return data;
    }

    public void setData(Calendar data) {
        this.data = data;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Integer getQuantidadeParcela() {
        return quantidadeParcela;
    }

    public void setQuantidadeParcela(Integer quantidadeParcela) {
        this.quantidadeParcela = quantidadeParcela;
    }

    public PessoaFisica getCliente() {
        return cliente;
    }

    public void setCliente(PessoaFisica cliente) {
        this.cliente = cliente;
    }

    public Usuario getVendedor() {
        return vendedor;
    }

    public void setVendedor(Usuario vendedor) {
        this.vendedor = vendedor;
    }

    public List<VendaItens> getItens() {
        return itens;
    }

    public void setItens(List<VendaItens> itens) {
        this.itens = itens;
    }
    
}
