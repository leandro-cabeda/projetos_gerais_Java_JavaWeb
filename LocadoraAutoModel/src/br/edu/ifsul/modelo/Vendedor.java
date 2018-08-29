package br.edu.ifsul.modelo;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "vendedor")
public class Vendedor extends Pessoa implements Serializable {
    @NotNull(message = "O nome de usuario não pode ser nulo")
    @NotBlank(message = "O nome de usuario não pode ser em branco")
    @Length(max = 20, message = "O nome de usuario não pode ter mais que {max} caracteres")
    @Column(name = "nome_usuario", length = 20, nullable = false, unique = true)
    private String nomeUsuario;
    @NotNull(message = "A senha não pode ser nula")
    @NotBlank(message = "A senha não pode ser em branco")
    @Length(max = 12, message = "A senha não pode ter mais que {max} caracteres")
    @Column(name = "senha", length = 12, nullable = false)
    private String senha;
    @NotNull(message = "Informe se o vendedor está ativo")
    @Column(name = "ativo", nullable = false)
    private Boolean ativo;    

    public Vendedor() {
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + Objects.hashCode(this.nomeUsuario);
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
        final Vendedor other = (Vendedor) obj;
        if (!Objects.equals(this.nomeUsuario, other.nomeUsuario)) {
            return false;
        }
        return true;
    }
}
