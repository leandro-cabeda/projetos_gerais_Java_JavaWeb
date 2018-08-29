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
@Table(name="endereco")
public class Endereco implements Serializable{
    
    @Id
    @SequenceGenerator(name="seq_endereco",sequenceName="seq_endereco_id",allocationSize=1)
    @GeneratedValue(generator="seq_endereco",strategy=GenerationType.SEQUENCE)
    private Integer id;
    
    @Column(name="nickname",length=15,nullable=false)
    @NotNull(message="O nickname não pode ser nulo")
    @NotBlank(message="O nickname não pode ser em branco")
    @Length(max=15,message="O nickname não pode ter mais que {max} caracteres")
    private String nickname;
     
    @Column(name="endereco",length=50,nullable=false)
    @NotNull(message="O endereço não pode ser nulo")
    @NotBlank(message="O endereço não pode ser em branco")
    @Length(max=50,message="O endereço não pode ter mais que {max} caracteres")
    private String endereco;
    
    @Column(name="numero",length=10,nullable=false)
    @NotNull(message="O numero não pode ser nulo")
    @NotBlank(message="O numero não pode ser em branco")
    @Length(max=10,message="O numero não pode ter mais que {max} caracteres")
    private String numero;

    private String complemento;
    
    @Column(name="cep",length=9,nullable=false)
    @NotNull(message="O cep não pode ser nulo")
    @NotBlank(message="O cep não pode ser em branco")
    @Length(max=9,message="O cep não pode ter mais que {max} caracteres")
    private String cep;
    
    @Column(name="bairro",length=20,nullable=false)
    @NotNull(message="O bairro não pode ser nulo")
    @NotBlank(message="O bairro não pode ser em branco")
    @Length(max=20,message="O bairro não pode ter mais que {max} caracteres")
    private String bairro;
    
    private String referencia;
    
    @NotNull(message="A cidade deve ser informado")
    @ManyToOne
    @JoinColumn(name="cidade",referencedColumnName="id",nullable=false)
    @ForeignKey(name="fk_endereco_cidade")
    private Cidade cidade;
    
    @NotNull(message="A pessoa deve ser informado")
    @ManyToOne
    @JoinColumn(name="pessoa",referencedColumnName="id",nullable=false)
    @ForeignKey(name="fk_endereco_pessoa")
    private Pessoa pessoa;
    
    public Endereco()
    {
        
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + Objects.hashCode(this.id);
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
        final Endereco other = (Endereco) obj;
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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }
    
    
    
}
