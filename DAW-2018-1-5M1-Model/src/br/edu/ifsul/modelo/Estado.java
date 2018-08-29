/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.modelo;

import java.io.Serializable;
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
import java.util.Objects;

/**
 *
 * @author Leandro
 */
@Entity
@Table(name="estado")
public class Estado implements Serializable{
    
    @Id
    @SequenceGenerator(name="seq_estado",sequenceName="seq_estado_id",allocationSize=1)
    @GeneratedValue(generator="seq_estado",strategy=GenerationType.SEQUENCE)
    private Integer id;
    @Column(name="nome",length=50,nullable=false)
    @NotNull(message="O nome não pode ser nulo")
    @NotBlank(message="O nome não pode ser em branco")
    @Length(max=50,message="O nome não pode ter mais que {max} caracteres")
    private String nome;
    @Column(name="uf",length=2,nullable=false)
    @NotNull(message="A UF não pode ser nulo")
    @NotBlank(message="A UF não pode ser em branco")
    @Length(max=2,message="A UF não pode ter mais que {max} caracteres")
    private String UF;
    @NotNull(message="O pais deve ser informado")
    @ManyToOne
    @JoinColumn(name="pais",referencedColumnName="id",nullable=false)
    @ForeignKey(name="fk_estado_pais")
    private Pais pais;
   
    public Estado()
    {
        
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + this.id;
        hash = 59 * hash + Objects.hashCode(this.nome);
        hash = 59 * hash + Objects.hashCode(this.UF);
        hash = 59 * hash + Objects.hashCode(this.pais);
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
        final Estado other = (Estado) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.UF, other.UF)) {
            return false;
        }
        if (!Objects.equals(this.pais, other.pais)) {
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

    public String getUF() {
        return UF;
    }

    public void setUF(String UF) {
        this.UF = UF;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }
    
}
