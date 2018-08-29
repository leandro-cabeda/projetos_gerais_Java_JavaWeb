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
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author Leandro
 */
@Entity
@Table(name="grupo")
public class Grupo implements Serializable{
    
    @Id
    @SequenceGenerator(name="seq_grupo",sequenceName="seq_grupo_id",allocationSize=1)
    @GeneratedValue(generator="seq_grupo",strategy=GenerationType.SEQUENCE)
    private Integer id;
    
    @NotNull(message="O nome não pode ser nulo")
    @NotBlank(message="O nome não pode ser em branco")
    @Length(max=30,message="O nome não pode ter mais que {max} caracteres")
    @Column(name="nome",length=30,nullable=false)
    private String nome;
    
    @NotNull(message="O valor não pode ser nulo")
    @Min(value = 0,message="O valor não poder ser abaixo de zero")
    @Column(name="valordiaria",columnDefinition ="numeric(10,2)",nullable=false)
    private Double valordiaria;
    
    
    @ManyToMany
    @JoinTable(name="conjuntos",joinColumns=@JoinColumn(name="grupo",referencedColumnName="id",
            nullable=false),inverseJoinColumns=@JoinColumn(name="acessorio",referencedColumnName="id",
            nullable=false),uniqueConstraints=
                    {@UniqueConstraint(columnNames={"acessorio","grupo"})})
    private List<Acessorio> acessorio=new ArrayList<>();
    
    
    public Grupo()
    {
        
    }
    
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.id;
        hash = 53 * hash + Objects.hashCode(this.nome);
        hash = 53 * hash + (int) (Double.doubleToLongBits(this.valordiaria) ^ (Double.doubleToLongBits(this.valordiaria) >>> 32));
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
        final Grupo other = (Grupo) obj;
        if (this.id != other.id) {
            return false;
        }
        if (Double.doubleToLongBits(this.valordiaria) != Double.doubleToLongBits(other.valordiaria)) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
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

    public double getValordiaria() {
        return valordiaria;
    }

    public void setValordiaria(double valordiaria) {
        this.valordiaria = valordiaria;
    }

    public List<Acessorio> getAcessorio() {
        return acessorio;
    }

    public void setAcessorio(List<Acessorio> acessorio) {
        this.acessorio = acessorio;
    }
    
}
