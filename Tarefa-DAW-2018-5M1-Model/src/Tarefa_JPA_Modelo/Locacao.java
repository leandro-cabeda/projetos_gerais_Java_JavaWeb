/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tarefa_JPA_Modelo;

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
@Table(name="locacao")
public class Locacao implements Serializable {
    @Id
    @SequenceGenerator(name="seq_locacao",sequenceName="seq_locacao_id",allocationSize=1)
    @GeneratedValue(generator="seq_locacao",strategy=GenerationType.SEQUENCE)
    private Integer id;
    
    
    @Temporal(TemporalType.DATE)
    @NotNull(message="A data de locação deve ser informado")
    @Column(name="dataLocacao",nullable=false)
    private Calendar dataLocacao;
    
    
    @Temporal(TemporalType.DATE)
    @NotNull(message="A data de devolução deve ser informado")
    @Column(name="dataDevolucao",nullable=false)
    private Calendar dataDevolucao;
    
    
    @NotNull(message="O valor da diaria não pode ser nulo")
    @Min(value = 0,message="O valor da diaria não poder ser abaixo de zero")
    @Column(name="valorDiaria",columnDefinition ="numeric(10,2)",nullable=false)
    private Double valorDiaria;
    
    
    @NotNull(message="Os km iniciais não pode ser nulo")
    @Min(value = 0,message="Os km iniciais não poder ser abaixo de {value}")
    @Column(name="kmInicial",nullable=false)
    private Integer kmInicial;
    
    
    @NotNull(message="Os km finais não pode ser nulo")
    @Min(value = 0,message="Os km finais não poder ser abaixo de {value}")
    @Column(name="kmFinal",nullable=false)
    private Integer kmFinal;
    
    
    @NotNull(message="O valor total não pode ser nulo")
    @Min(value = 0,message="O valor total não poder ser abaixo de zero")
    @Column(name="valorTotal",columnDefinition ="numeric(10,2)",nullable=false)
    private Double valorTotal;
    
    
    
    @NotNull(message="O vendedor deve ser informado")
    @ManyToOne
    @JoinColumn(name="vendedor",referencedColumnName="id",nullable=false)
    @ForeignKey(name="fk_locacao_vendedor")
    private Vendedor vendedor;
    
    
    
    @NotNull(message="O cliente deve ser informado")
    @ManyToOne
    @JoinColumn(name="cliente",referencedColumnName="id",nullable=false)
    @ForeignKey(name="fk_locacao_cliente")
    private Cliente cliente;
    
    
    
    @NotNull(message="O carro deve ser informado")
    @ManyToOne
    @JoinColumn(name="carro",referencedColumnName="id",nullable=false)
    @ForeignKey(name="fk_locacao_carro")
    private Carro carro;
    
    
    
    @OneToMany(mappedBy="locacao",cascade=CascadeType.ALL,orphanRemoval=true,fetch=FetchType.LAZY)
    private List<Adicional>adicional=new ArrayList<>();
    
    
    public Locacao()
    {
        
    }
    
     public void adicionarAdicional(Adicional obj)
    {
        obj.setLocacao(this);
        this.adicional.add(obj);
    }
    
     public void removerAdicional(int index)
    {
        this.adicional.remove(index);
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        final Locacao other = (Locacao) obj;
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

    public Calendar getDataLocacao() {
        return dataLocacao;
    }

    public void setDataLocacao(Calendar dataLocacao) {
        this.dataLocacao = dataLocacao;
    }

    public Calendar getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(Calendar dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public Double getValorDiaria() {
        return valorDiaria;
    }

    public void setValorDiaria(Double valorDiaria) {
        this.valorDiaria = valorDiaria;
    }

    public Integer getKmInicial() {
        return kmInicial;
    }

    public void setKmInicial(Integer kmInicial) {
        this.kmInicial = kmInicial;
    }

    public Integer getKmFinal() {
        return kmFinal;
    }

    public void setKmFinal(Integer kmFinal) {
        this.kmFinal = kmFinal;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Carro getCarro() {
        return carro;
    }

    public void setCarro(Carro carro) {
        this.carro = carro;
    }

    public List<Adicional> getAdicional() {
        return adicional;
    }

    public void setAdicional(List<Adicional> adicional) {
        this.adicional = adicional;
    }
    
    
}
