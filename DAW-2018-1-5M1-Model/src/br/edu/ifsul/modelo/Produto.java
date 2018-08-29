/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.ForeignKey;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author Leandro
 */
@Entity
@Table(name="produto")
public class Produto implements Serializable{
    
    @Id
    @SequenceGenerator(name="seq_produto",sequenceName="seq_produto_id",allocationSize=1)
    @GeneratedValue(generator="seq_produto",strategy=GenerationType.SEQUENCE)
    private Integer id;
    
    @NotNull(message="O nome não pode ser nulo")
    @NotBlank(message="O nome não pode ser em branco")
    @Length(max=50,message="O nome não pode ter mais que {max} caracteres")
    @Column(name="nome",length=50,nullable=false) 
    private String nome;
    
    
    @Column(name="descricao",columnDefinition="text") 
    private String descricao;
    
    
    @Min(value=0, message="O preço não pode ser negativo")
    @NotNull(message="O preço não pode ser nulo")
    @Column(name="preco",columnDefinition="decimal(10,2)",nullable=false) 
    private Double preco;
    
    
    @Min(value=0, message="A quantidade de estoque não pode ser negativo")
    @NotNull(message="A quantidade de estoque não pode ser nulo")
    @Column(name="quantidadeEstoque",columnDefinition="decimal(10,2)",nullable=false) 
    private Double quantidadeEstoque;
    
    
    @NotNull(message="A marca deve ser informado")
    @ManyToOne
    @JoinColumn(name="marca",referencedColumnName="id",nullable=false)
    @ForeignKey(name="fk_produto_marca")
    private Marca marca;
    
    
    @NotNull(message="A categoria deve ser informado")
    @ManyToOne
    @JoinColumn(name="categoria",referencedColumnName="id",nullable=false)
    @ForeignKey(name="fk_produto_categoria")
    private Categoria categoria;
    
    
    
    @ManyToMany
    @JoinTable(name="desejos",joinColumns=@JoinColumn(name="produto",referencedColumnName="id",
            nullable=false),inverseJoinColumns=@JoinColumn(name="pessoa_fisica",referencedColumnName="id",
            nullable=false),uniqueConstraints=
                    {@UniqueConstraint(columnNames={"pessoa_fisica","produto"})})
    private List<PessoaFisica> desejam=new ArrayList<>();
    
    public Produto()
    {
        
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + this.id;
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
        final Produto other = (Produto) obj;
        if (this.id != other.id) {
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Double getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(Double quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public List<PessoaFisica> getDesejam() {
        return desejam;
    }

    public void setDesejam(List<PessoaFisica> desejam) {
        this.desejam = desejam;
    }
    
}
