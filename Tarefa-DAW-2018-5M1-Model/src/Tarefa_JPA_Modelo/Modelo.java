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
@Table(name="modelo")
public class Modelo implements Serializable{
    
    @Id
    @SequenceGenerator(name="seq_modelo",sequenceName="seq_modelo_id",allocationSize=1)
    @GeneratedValue(generator="seq_modelo",strategy=GenerationType.SEQUENCE)
    private Integer id;
    
    @NotNull(message="O nome não pode ser nulo")
    @NotBlank(message="O nome não pode ser em branco")
    @Length(max=30,message="O nome não pode ter mais que {max} caracteres")
    @Column(name="nome",length=30,nullable=false)
    private String nome;
    
    @NotNull(message="O fabricante deve ser informado")
    @ManyToOne
    @JoinColumn(name="fabricante",referencedColumnName="id",nullable=false)
    @ForeignKey(name="fk_modelo_fabricante")
    private Fabricante fabricante;
    
    @NotNull(message="O grupo deve ser informado")
    @ManyToOne
    @JoinColumn(name="grupo",referencedColumnName="id",nullable=false)
    @ForeignKey(name="fk_modelo_grupo")
    private Grupo grupo;

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.id);
        hash = 79 * hash + Objects.hashCode(this.nome);
        hash = 79 * hash + Objects.hashCode(this.fabricante);
        hash = 79 * hash + Objects.hashCode(this.grupo);
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
        final Modelo other = (Modelo) obj;
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.fabricante, other.fabricante)) {
            return false;
        }
        if (!Objects.equals(this.grupo, other.grupo)) {
            return false;
        }
        return true;
    }
    
    public Modelo()
    {
        
    }

    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id=id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Fabricante getFabricante() {
        return fabricante;
    }

    public void setFabricante(Fabricante fabricante) {
        this.fabricante = fabricante;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }
    
}
