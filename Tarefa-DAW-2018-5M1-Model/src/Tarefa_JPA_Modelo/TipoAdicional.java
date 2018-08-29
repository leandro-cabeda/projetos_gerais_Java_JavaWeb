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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author Leandro
 */
@Entity
@Table(name="tipoadicional")
public class TipoAdicional implements Serializable {
    
    @Id
    @SequenceGenerator(name="seq_tipoadicional",sequenceName="seq_tipoadicional_id",allocationSize=1)
    @GeneratedValue(generator="seq_tipoadicional",strategy=GenerationType.SEQUENCE)
    private Integer id;
    
    @Column(name="nome_usuario",length=20,nullable=false)
    @NotNull(message="O nome não pode ser nulo")
    @NotBlank(message="O nome não pode ser em branco")
    @Length(max=20,message="O nome não pode ter mais que {max} caracteres")
    private String nome;
    
    @NotNull(message="O valor não pode ser nulo")
    @Min(value = 0,message="O valor não poder ser abaixo de zero")
    @Column(name="valor",columnDefinition ="numeric(10,2)",nullable=false)
    private Double valor;
    
    public TipoAdicional()
    {
        
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.id);
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
        final TipoAdicional other = (TipoAdicional) obj;
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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
    
}
