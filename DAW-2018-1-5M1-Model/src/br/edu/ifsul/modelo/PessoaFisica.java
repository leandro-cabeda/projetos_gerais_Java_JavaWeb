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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;

/**
 *
 * @author Leandro
 */
@Entity
@Table(name="pessoa_fisica")
@Inheritance(strategy=InheritanceType.JOINED)
public class PessoaFisica extends Pessoa implements Serializable {
    @Column(name="rg",nullable=false,length = 10)
    @NotNull(message="O rg não pode ser nulo")
    @NotBlank(message="O rg não pode ser em branco")
    private String rg;
    
    @CPF
    @Column(name="cpf",nullable=false,length = 14,unique=true)
    @NotNull(message="O cpf não pode ser nulo")
    @NotBlank(message="O cpf não pode ser em branco")
    private String cpf;
    
    @Temporal(TemporalType.DATE)
    @NotNull(message="O nascimento deve ser informado")
    @Column(name="nascimento",nullable=false)
    private Calendar nascimento;
    
    
    @ManyToMany
    @JoinTable(name="desejos",joinColumns=@JoinColumn(name="pessoa_fisica",referencedColumnName="id",
            nullable=false),inverseJoinColumns=@JoinColumn(name="produto",referencedColumnName="id",
            nullable=false),uniqueConstraints=
                    {@UniqueConstraint(columnNames={"pessoa_fisica","produto"})})
    private List<Produto> desejos=new ArrayList<>();
    
    public PessoaFisica()
    {
        
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

    public Calendar getNascimento() {
        return nascimento;
    }

    public void setNascimento(Calendar nascimento) {
        this.nascimento = nascimento;
    }

    public List<Produto> getDesejos() {
        return desejos;
    }

    public void setDesejos(List<Produto> desejos) {
        this.desejos = desejos;
    }
    
    
}
