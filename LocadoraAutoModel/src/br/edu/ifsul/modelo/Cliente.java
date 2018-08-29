package br.edu.ifsul.modelo;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "cliente")
public class Cliente extends Pessoa implements Serializable {

    @NotNull(message = "O telefone não pode ser nulo")
    @NotBlank(message = "O telefone não pode ser em branco")
    @Length(max = 14, message = "O telefone não pode ter mais que {max} caracteres")
    @Column(name = "telefone", length = 14, nullable = false)
    private String telefone;
    @NotNull(message = "O endereco não pode ser nulo")
    @NotBlank(message = "O endereco não pode ser em branco")
    @Column(name = "endereco", length = 50, nullable = false)
    private String endereco;
    @NotNull(message = "O cep não pode ser nulo")
    @NotBlank(message = "O cep não pode ser em branco")
    @Length(max = 8, message = "O cep não pode ter mais que {max} caracteres")
    @Column(name = "cep", length = 8, nullable = false)
    private String cep;
    @NotNull(message = "O bairro não pode ser nulo")
    @NotBlank(message = "O bairro não pode ser em branco")
    @Column(name = "bairro", length = 20, nullable = false)
    private String bairro;
    @NotNull(message = "A cidade não pode ser nula")
    @ManyToOne
    @JoinColumn(name = "cidade", referencedColumnName = "id", nullable = false)
    @ForeignKey(name = "fk_cliente_cidade")    
    private Cidade cidade;

    public Cliente() {
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.telefone);
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
        final Cliente other = (Cliente) obj;
        if (!Objects.equals(this.telefone, other.telefone)) {
            return false;
        }
        return true;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }
}
