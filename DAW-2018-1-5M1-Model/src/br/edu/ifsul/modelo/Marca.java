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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author Leandro
 */
@Entity
@Table(name="marca")
public class Marca implements Serializable{
    @Id
    @SequenceGenerator(name="seq_marca",sequenceName="seq_marca_id",allocationSize=1)
    @GeneratedValue(generator="seq_marca",strategy=GenerationType.SEQUENCE)
    private Integer id;
    
    
    
    @NotNull(message="A marca não pode ser nulo")
    @NotBlank(message="A marca não pode ser em branco")
    @Length(max=20,message="A  marca não pode ter mais que {max} caracteres")
    @Column(name="nome",length=20,nullable=false,unique=true) 
    private String nome;
    
    public Marca ()
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


    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + this.id;
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
        final Marca other = (Marca) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    
    
}
