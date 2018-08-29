/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tarefa_JPA_Modelo;

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
@Table(name="adicional")
public class Adicional implements Serializable{
    
    @Id
    @SequenceGenerator(name="seq_adicional",sequenceName="seq_adicional_id",allocationSize=1)
    @GeneratedValue(generator="seq_adicional",strategy=GenerationType.SEQUENCE)
    private Integer id;
    
    @NotNull(message="O valor não pode ser nulo")
    @Min(value = 0,message="O valor não poder ser abaixo de zero")
    @Column(name="valor",columnDefinition ="numeric(10,2)",nullable=false)
    private Double valor;
    
    @NotNull(message="O tipo adicional deve ser informado")
    @ManyToOne
    @JoinColumn(name="tipoadicional",referencedColumnName="id",nullable=false)
    @ForeignKey(name="fk_adicional_tipoadicional")
    private TipoAdicional tipoadicional;
    
    
    @NotNull(message="A locação deve ser informado")
    @ManyToOne
    @JoinColumn(name="locacao",referencedColumnName="id",nullable=false)
    @ForeignKey(name="fk_loca_adici_locacao")
    private Locacao locacao;

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.getId());
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
        final Adicional other = (Adicional) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    
    public Adicional()
    {
        
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public TipoAdicional getTipoadicional() {
        return tipoadicional;
    }

    public void setTipoadicional(TipoAdicional tipoadicional) {
        this.tipoadicional = tipoadicional;
    }

    public Locacao getLocacao() {
        return locacao;
    }

    public void setLocacao(Locacao locacao) {
        this.locacao = locacao;
    }
    
}
