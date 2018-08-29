/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.Min;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author Leandro
 */
public class Animal implements Serializable{
    
    private Integer id;
    
    @Length(max=40, message="{animal.nome.tamanho}")
    @NotBlank(message="{animal.nome.embranco}")
    private String nome;
    
    @Length(max=40, message="{animal.raca.tamanho}")
    @NotBlank(message="{animal.raca.embranco}")
    private String raca;
    
    @Length(max=30, message="{animal.cor.tamanho}")
    @NotBlank(message="{animal.cor.embranco}")
    private String cor;
    
    @Length(max=50, message="{animal.descricao.tamanho}")
    @NotBlank(message="{animal.descricao.embranco}")
    private String descricao;
    
  
    @Min(value = 0,message="{animal.peso.minimo}")
    private Double peso;
    
    public Animal()
    {
        
    }
    
    public Animal(Integer id, String nome, String raca, String cor, String descricao, Double peso) {
        this.id = id;
        this.nome = nome;
        this.raca = raca;
        this.cor=cor;
        this.descricao=descricao;
        this.peso=peso;
        
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

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.id);
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
        final Animal other = (Animal) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    
    
    
}
