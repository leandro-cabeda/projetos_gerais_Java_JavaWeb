/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tarefa_JPA_Modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author Leandro
 */
@Entity
@Table(name="acessorio")
public class Acessorio implements Serializable{
    
    @Id
    @SequenceGenerator(name="seq_acessorio",sequenceName="seq_acessorio_id",allocationSize=1)
    @GeneratedValue(generator="seq_acessorio",strategy=GenerationType.SEQUENCE)
    private Integer id;
    
    @NotNull(message="O nome deve ser informado")
    @NotBlank(message="O nome não pode ser em branco")
    @Length(max=25,message="O nome não pode ter mais que {max} caracteres")
    @Column(name="nome",length=25,nullable=false,unique=true)
    private String nome;
    
    
    
    @ManyToMany
    @JoinTable(name="conjuntos",joinColumns=@JoinColumn(name="acessorio",referencedColumnName="id",
            nullable=false),inverseJoinColumns=@JoinColumn(name="grupo",referencedColumnName="id",
            nullable=false),uniqueConstraints=
                    {@UniqueConstraint(columnNames={"acessorio","grupo"})})
    private List<Grupo> grupos=new ArrayList<>();
    
    public Acessorio()
    {
        
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + Objects.hashCode(this.id);
        hash = 89 * hash + Objects.hashCode(this.nome);
        hash = 89 * hash + Objects.hashCode(this.grupos);
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
        final Acessorio other = (Acessorio) obj;
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.grupos, other.grupos)) {
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

    public List<Grupo> getGrupos() {
        return grupos;
    }

    public void setGrupos(List<Grupo> grupos) {
        this.grupos = grupos;
    }
    
}
