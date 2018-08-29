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
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.ForeignKey;

/**
 *
 * @author Prof. Me. Jorge Luis Boeira Bavaresco
 * @email jorge.bavaresco@passofundo.ifsul.edu.br
 * @organization IFSUL - Campus Passo Fundo
 */
@Entity
@Table(name = "Adicional")
public class Adicional implements Serializable {

    @Id
    @SequenceGenerator(name = "seq_adicional", sequenceName = "seq_adicional_id",
            allocationSize = 1)
    @GeneratedValue(generator = "seq_adicional", strategy = GenerationType.SEQUENCE)
    private Integer id;
    @Min(value = 0, message = "O valor não pode ser negativo")
    @NotNull(message = "O valor deve ser informado")
    @Column(name = "valor", nullable = false, columnDefinition = "numeric(10,2)")
    private Double valor;
    @NotNull(message = "O tipo do adicional não pode ser nulo")
    @ManyToOne
    @JoinColumn(name = "tipo_adicional", referencedColumnName = "id", nullable = false)
    @ForeignKey(name = "fk_adicional_tipo")
    private TipoAdicional tipoadicional;
    @NotNull(message = "A locação não pode ser nula")
    @ManyToOne
    @JoinColumn(name = "locacao_id", referencedColumnName = "id", nullable = false)
    @ForeignKey(name = "fk_adicional_locacao")
    private Locacao locacao;

    public Adicional() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public TipoAdicional getTipoadicional() {
        return tipoadicional;
    }

    public void setTipoadicional(TipoAdicional tipoadicional) {
        this.tipoadicional = tipoadicional;
    }

    public Locacao getLocacao() {
        return locacao;
    }

    public void setLocacao(Locacao locacao) {
        this.locacao = locacao;
    }

    @Override
    public int hashCode() {
        int hash = 3;
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
        final Adicional other = (Adicional) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
}
