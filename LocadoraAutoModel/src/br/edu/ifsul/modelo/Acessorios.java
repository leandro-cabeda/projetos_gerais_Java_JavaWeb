package br.edu.ifsul.modelo;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author Prof. Me. Jorge Luis Boeira Bavaresco
 * @email jorge.bavaresco@passofundo.ifsul.edu.br
 * @organization IFSUL - Campus Passo Fundo
 */
@Entity
@Table(name = "acessorios")
public class Acessorios implements Serializable {

    @Id
    @SequenceGenerator(name = "seq_acessorios", sequenceName = "seq_acessorios_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_acessorios", strategy = GenerationType.SEQUENCE)
    private Integer id;
    @Length(max = 50, message = "O nome tem que ter no maximo {max} caracteres")
    @NotBlank(message = "O nome não pode ser em branco")
    @NotNull(message = "O nome não pode ser nulo")
    @Column(name = "nome", nullable = false, length = 50)
    private String nome;

    public Acessorios() {
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

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + Objects.hashCode(this.id);
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
        final Acessorios other = (Acessorios) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
}
