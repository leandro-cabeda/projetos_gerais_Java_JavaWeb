package br.edu.ifsul.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
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
@Table(name = "locacao")
@Inheritance(strategy = InheritanceType.JOINED)
public class Locacao implements Serializable {

    @Id
    @SequenceGenerator(name = "seq_locacao", sequenceName = "seq_locacao_id",
            allocationSize = 1)
    @GeneratedValue(generator = "seq_locacao", strategy = GenerationType.SEQUENCE)
    private Integer id;
    @Temporal(javax.persistence.TemporalType.DATE)
    @NotNull(message = "A data de locacao deve ser informada")
    @Column(name = "data_locacao", nullable = false)
    private Calendar dataLocacao;
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name = "data_devolucao")
    private Calendar dataDevolucao;
    @Min(value = 0, message = "O valor da diaria não pode ser negativo")
    @NotNull(message = "O valor da diaria deve ser informado")
    @Column(name = "valor_diaria", nullable = false, columnDefinition = "decimal(10,2)")
    private Double valorDiaria;
    @Min(value = 1, message = "A Km inicial não pode ser negativa")
    @NotNull(message = "A Km inicial deve ser informada")
    @Column(name = "km_inicial", nullable = false)
    private Integer kmInicial;
    @Min(value = 1, message = "A Km final não pode ser negativa")
    @Column(name = "km_final")
    private Integer kmFinal;
    @Min(value = 0, message = "O valor total não pode ser negativo não pode ser negativo")
    @Column(name = "valor_total", columnDefinition = "decimal(10,2)")
    private Double valorTotal;
    @NotNull(message = "O cliente deve ser informado")
    @ManyToOne
    @JoinColumn(name = "cliente", referencedColumnName = "id", nullable = false)
    @ForeignKey(name = "fk_locacao_cliente")
    private Cliente cliente;
    @NotNull(message = "O vendedor deve ser informado")
    @ManyToOne
    @JoinColumn(name = "vendedor", referencedColumnName = "id", nullable = false)
    @ForeignKey(name = "fk_locacao_vendedor")
    private Vendedor vendedor;
    @NotNull(message = "O carro deve ser informado")
    @ManyToOne
    @JoinColumn(name = "carro", referencedColumnName = "id", nullable = false)
    @ForeignKey(name = "fk_locacao_carro")
    private Carro carro;
    @OneToMany(mappedBy = "locacao", cascade = CascadeType.ALL,
            orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Adicional> adicionais = new ArrayList<>();

    public Locacao() {
    }
    
    public void adicionarAdicional(Adicional obj){
        obj.setLocacao(this);        
        this.adicionais.add(obj);
    }
    
    public void removerAdicional(int index){
        this.adicionais.remove(index);
    }    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Calendar getDataLocacao() {
        return dataLocacao;
    }

    public void setDataLocacao(Calendar dataLocacao) {
        this.dataLocacao = dataLocacao;
    }

    public Calendar getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(Calendar dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public Double getValorDiaria() {
        return valorDiaria;
    }

    public void setValorDiaria(Double valorDiaria) {
        this.valorDiaria = valorDiaria;
    }

    public Integer getKmInicial() {
        return kmInicial;
    }

    public void setKmInicial(Integer kmInicial) {
        this.kmInicial = kmInicial;
    }

    public Integer getKmFinal() {
        return kmFinal;
    }

    public void setKmFinal(Integer kmFinal) {
        this.kmFinal = kmFinal;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    public Carro getCarro() {
        return carro;
    }

    public void setCarro(Carro carro) {
        this.carro = carro;
    }

    public List<Adicional> getAdicionais() {
        return adicionais;
    }

    public void setAdicionais(List<Adicional> adicionais) {
        this.adicionais = adicionais;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + Objects.hashCode(this.id);
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
        final Locacao other = (Locacao) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
}
