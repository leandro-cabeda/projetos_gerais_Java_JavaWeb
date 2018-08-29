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
@Table(name="carro")
public class Carro implements Serializable{
    
    @Id
    @SequenceGenerator(name="seq_carro",sequenceName="seq_carro_id",allocationSize=1)
    @GeneratedValue(generator="seq_carro",strategy=GenerationType.SEQUENCE)
    private Integer id;
    
    @NotNull(message="A placa não pode ser nulo")
    @NotBlank(message="A placa não pode ser em branco")
    @Length(max=30,message="A placa não pode ter mais que {max} caracteres")
    @Column(name="placa",length=30,nullable=false,unique = true)
    private String placa;
    
    @NotNull(message="O ano da fabricação não pode ser nulo")
    @Min(value = 1930,message="O ano da fabricação não poder ser abaixo de {value}")
    @Column(name="anofabricacao",nullable=false)
    private Integer anofabricacao;
    
    @NotNull(message="O  ano modelo não pode ser nulo")
    @Min(value = 1990,message="O ano modelo não poder ser abaixo de {value}")
    @Column(name="anomodelo",nullable=false)
    private Integer anomodelo;
    
    @NotNull(message="A versao não pode ser nulo")
    @NotBlank(message="A versao não pode ser em branco")
    @Length(max=30,message="A versao não pode ter mais que {max} caracteres")
    @Column(name="versao",length=30,nullable=false)
    private String versao;
    
    @NotNull(message="O modelo deve ser informado")
    @ManyToOne
    @JoinColumn(name="modelo",referencedColumnName="id",nullable=false)
    @ForeignKey(name="fk_carro_modelo")
    private Modelo modelo;

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + this.id;
        hash = 71 * hash + Objects.hashCode(this.placa);
        hash = 71 * hash + Objects.hashCode(this.anofabricacao);
        hash = 71 * hash + Objects.hashCode(this.anomodelo);
        hash = 71 * hash + Objects.hashCode(this.versao);
        hash = 71 * hash + Objects.hashCode(this.modelo);
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
        final Carro other = (Carro) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.placa, other.placa)) {
            return false;
        }
        if (!Objects.equals(this.versao, other.versao)) {
            return false;
        }
        if (!Objects.equals(this.anofabricacao, other.anofabricacao)) {
            return false;
        }
        if (!Objects.equals(this.anomodelo, other.anomodelo)) {
            return false;
        }
        if (!Objects.equals(this.modelo, other.modelo)) {
            return false;
        }
        return true;
    }
    
    
    
    public Carro()
    {
        
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public Integer getAnofabricacao() {
        return anofabricacao;
    }

    public void setAnofabricacao(Integer anofabricacao) {
        this.anofabricacao = anofabricacao;
    }

    public Integer getAnomodelo() {
        return anomodelo;
    }

    public void setAnomodelo(Integer anomodelo) {
        this.anomodelo = anomodelo;
    }

    public String getVersao() {
        return versao;
    }

    public void setVersao(String versao) {
        this.versao = versao;
    }

    public Modelo getModelo() {
        return modelo;
    }

    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }

    
}
