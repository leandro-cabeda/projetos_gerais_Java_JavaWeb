package br.edu.ifsul.modelo;

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
@Table(name = "grupo")
public class Grupo implements Serializable {

    @Id
    @SequenceGenerator(name = "seq_grupo", sequenceName = "seq_grupo_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_grupo", strategy = GenerationType.SEQUENCE)
    private Integer id;
    @Length(max = 50, message = "O nome tem que ter no maximo {max} caracteres")
    @NotBlank(message = "O nome não pode ser em branco")
    @NotNull(message = "O nome não pode ser nulo")
    @Column(name = "nome", nullable = false, length = 50)
    private String nome;
    @NotNull(message = "O valor deve ser informado")
    @Column(name = "valor", nullable = false,  columnDefinition = "numeric(10,2)")
    private Double valor;
    @ManyToMany
    @JoinTable(name = "opcionais",
            joinColumns
            = @JoinColumn(name = "grupo", referencedColumnName = "id", nullable = false),
            inverseJoinColumns
            = @JoinColumn(name = "acessorio", referencedColumnName = "id", nullable = false))
    private List<Acessorios> acessorios = new ArrayList<>();

    public Grupo() {
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

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
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
        final Grupo other = (Grupo) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    public List<Acessorios> getAcessorios() {
        return acessorios;
    }

    public void setAcessorios(List<Acessorios> acessorios) {
        this.acessorios = acessorios;
    }
}
