/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.modelo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author Leandro
 */
@Entity
@Table(name="usuario")
public class Usuario extends PessoaFisica implements Serializable{
    
    @Column(name="nomeUsuario",nullable=false,length = 20,unique=true)
    @NotNull(message="O nome do usuario não pode ser nulo")
    @NotBlank(message="O nome do usuario não pode ser em branco")
    @Length(max=20,message="o nome do usuário não pode ter mais de {max} caracteres")
    private String nomeUsuario;
    
    
    @Column(name="senha",nullable=false,length = 20)
    @NotNull(message="A senha  não pode ser nulo")
    @NotBlank(message="A senha não pode ser em branco")
    @Length(max=20,message="A senha não pode ter mais de {max} caracteres")
    private String senha;
    
    
    @Column(name="ativo",nullable=false)
    @NotNull(message="Informe se o usuario está ativo")
    private Boolean ativo;
    
    public Usuario()
    {
        
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
    
    
    
    
}
