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
 * @author Prof. Me. Jorge Luis Boeira Bavaresco
 * @email jorge.bavaresco@passofundo.ifsul.edu.br
 * @organization IFSUL - Campus Passo Fundo
 */
@Entity
@Table(name = "carro")
public class Carro implements Serializable{
    @Id
    @SequenceGenerator(name = "seq_carro", sequenceName = "seq_carro_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_carro", strategy = GenerationType.SEQUENCE)
    private Integer id;
    @NotNull(message = "A placa não pode ser nula")
    @NotBlank(message = "A placa não pode ser em branco")
    @Length(max = 7, message = "A placa não pode ter mais que {max} caracteres")
    @Column(name = "placa", length = 7, nullable = false, unique = true)
    private String placa;
    @NotNull(message = "O ano de fabricacao não pode ser nulo")
    @Column(name = "anoFabricacao",  nullable = false)
    private Integer anoFabricacao;
    @NotNull(message = "O ano de modelo não pode ser nulo")
    @Column(name = "anoModelo", nullable = false)
    private Integer anoModelo;
    @NotNull(message = "A versão não pode ser nula")
    @NotBlank(message = "A versão não pode ser em branco")
    @Length(max = 20, message = "A versao não pode ter mais que {max} caracteres")
    @Column(name = "versao", length = 20, nullable = false)
    private String versao;
    @NotNull(message = "O modelo não pode ser nulo")
    @ManyToOne
    @JoinColumn(name = "modelo", referencedColumnName = "id", nullable = false)
    @ForeignKey(name = "fk_carro_modelo_id")
    private Modelo modelo;    

    public Carro() {
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

    public Integer getAnoFabricacao() {
        return anoFabricacao;
    }

    public void setAnoFabricacao(Integer anoFabricacao) {
        this.anoFabricacao = anoFabricacao;
    }

    public Integer getAnoModelo() {
        return anoModelo;
    }

    public void setAnoModelo(Integer anoModelo) {
        this.anoModelo = anoModelo;
    }

    public String getVersao() {
        return versao;
    }

    public void setVersao(String versao) {
        this.versao = versao;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.id);
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
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    public Modelo getModelo() {
        return modelo;
    }

    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }
}
