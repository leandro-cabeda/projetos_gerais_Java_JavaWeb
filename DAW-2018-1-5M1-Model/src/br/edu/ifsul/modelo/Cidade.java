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
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.ForeignKey;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author Leandro
 */
@Entity
@Table(name="cidade")
public class Cidade implements Serializable{
    @Id
    @SequenceGenerator(name="seq_cidade",sequenceName="seq_cidade_id",allocationSize=1)
    @GeneratedValue(generator="seq_cidade",strategy=GenerationType.SEQUENCE)
    private Integer id;
    
    @Column(name="nome",length=50,nullable=false)
    @NotNull(message="O nome não pode ser nulo")
    @NotBlank(message="O nome não pode ser em branco")
    @Length(max=50,message="O nome não pode ter mais que {max} caracteres")
    private String nome;
    
    @NotNull(message="A cidade deve ser informado")
    @ManyToOne
    @JoinColumn(name="estado",referencedColumnName="id",nullable=false)
    @ForeignKey(name="fk_cidade_estado")
    private Estado estado;

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.id);
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
        final Cidade other = (Cidade) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    public Cidade()
    {
        
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

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
    
}
