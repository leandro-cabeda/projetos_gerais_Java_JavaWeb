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
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;

/**
 *
 * @author Leandro
 */
@Entity
@Table(name="pessoa")
@Inheritance(strategy=InheritanceType.JOINED)
public class Pessoa implements Serializable {
    @Id
    @SequenceGenerator(name="seq_pessoa",sequenceName="seq_pessoa_id",allocationSize=1)
    @GeneratedValue(generator="seq_pessoa",strategy=GenerationType.SEQUENCE)
    private Integer id;
     
    @Column(name="nome",length=50,nullable=false)
    @NotNull(message="O nome não pode ser nulo")
    @NotBlank(message="O nome não pode ser em branco")
    @Length(max=50,message="O nome não pode ter mais que {max} caracteres")
    private String nome;
    
    @Column(name="rg",nullable=false,length = 10)
    @NotNull(message="O rg não pode ser nulo")
    @NotBlank(message="O rg não pode ser em branco")
    private String rg;
    
    @CPF
    @Column(name="cpf",nullable=false,length = 14,unique=true)
    @NotNull(message="O cpf não pode ser nulo")
    @NotBlank(message="O cpf não pode ser em branco")
    private String cpf;
    
    
    public Pessoa()
    {
        
    }
    

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 11 * hash + Objects.hashCode(this.getId());
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
        final Pessoa other = (Pessoa) obj;
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

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    
}
