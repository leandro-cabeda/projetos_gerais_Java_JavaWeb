/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tarefa_JPA_Modelo;

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
@Table(name="vendedor")
public class Vendedor extends Pessoa implements Serializable{
    
    @Column(name="nome_usuario",length=20,nullable=false)
    @NotNull(message="O nome não pode ser nulo")
    @NotBlank(message="O nome não pode ser em branco")
    @Length(max=20,message="O nome não pode ter mais que {max} caracteres")
    private String nome_usuario;
    
    
    @Column(name="senha",length=50,nullable=false)
    @NotNull(message="A senha não pode ser nulo")
    @NotBlank(message="A senha não pode ser em branco")
    @Length(max=50,message="A senha não pode ter mais que {max} caracteres")
    private String senha;

    
    @Column(name="ativo",nullable=false)
    private boolean ativo=false;
    
   
    
    public Vendedor()
    {
        
    }
    
    public boolean Ativo_ou_Inativo(boolean ativo)
    {
        if(ativo==true)
        {
            ativo=true;
        }
        return ativo;
    }

    public String getNome_usuario() {
        return nome_usuario;
    }

    public void setNome_usuario(String nome_usuario) {
        this.nome_usuario = nome_usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
    
    
}
