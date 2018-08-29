/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tarefa_JPA_Modelo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name="cliente")
public class Cliente extends Pessoa implements Serializable{
    
    @Column(name="telefone",length=14,nullable=false)
    @NotNull(message="O telefone não pode ser nulo")
    @NotBlank(message="O telefone não pode ser em branco")
    @Length(max=14,message="O telefone não pode ter mais que {max} caracteres")
    private String telefone;
    
    
    @Column(name="endereco",length=50,nullable=false)
    @NotNull(message="O endereço não pode ser nulo")
    @NotBlank(message="O endereço não pode ser em branco")
    @Length(max=50,message="O endereço não pode ter mais que {max} caracteres")
    private String endereco;

    
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
    
    
    @NotNull(message="A cidade deve ser informado")
    @ManyToOne
    @JoinColumn(name="cidade",referencedColumnName="id",nullable=false)
    @ForeignKey(name="fk_cliente_cidade")
    private Cidade cidade;
   
    
    public Cliente()
    {
        
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
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

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    
}
