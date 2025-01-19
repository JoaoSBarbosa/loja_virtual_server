package com.barbosacode.lojavirtual.models;
import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "nota_fiscal_compra")
@SequenceGenerator(name = "seq_nota_fiscal_compra", sequenceName = "seq_nota_fiscal_compra", allocationSize = 1, initialValue = 1)
public class NotaFiscalCompra implements Serializable {


    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "seq_nota_fiscal_compra")
    private Long id;
    @Column(name = "numero_nota")
    private String numeroNota;

    @Column(nullable = false)
    private String serie;

    private String descricao;
    @Column(name = "valor_total", nullable = false)
    private BigDecimal valorTotal;
    @Column(name = "valor_desconto")
    private BigDecimal valorDesconto;

    @Column(name = "valor_icms", nullable = false)
    private BigDecimal valorIcms;

    @Temporal(TemporalType.DATE)
    @Column(name = "data_compra", nullable = false)
    private Date dataCompra;


    @ManyToOne(targetEntity = Pessoa.class)
    @JoinColumn(
            name = "id_pessoa",
            nullable = false,
            foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "fk_pessoa_nfe_compra"))
    private Pessoa pessoa;

    @OneToOne
    @JoinColumn(
            name = "id_conta_pagar",
            nullable = false,
            foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "fk_conta_pagar_nfe_compra"))
    private ContaPagar contaPagar;


    public NotaFiscalCompra() {}

    public NotaFiscalCompra(
            ContaPagar contaPagar,
            Long id,
            String numeroNota,
            String descricao,
            String serie,
            Pessoa pessoa,
            Date dataCompra,
            BigDecimal valorIcms,
            BigDecimal valorDesconto,
            BigDecimal valorTotal) {
        this.contaPagar = contaPagar;
        this.id = id;
        this.numeroNota = numeroNota;
        this.descricao = descricao;
        this.serie = serie;
        this.pessoa = pessoa;
        this.dataCompra = dataCompra;
        this.valorIcms = valorIcms;
        this.valorDesconto = valorDesconto;
        this.valorTotal = valorTotal;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroNota() {
        return numeroNota;
    }

    public void setNumeroNota(String numeroNota) {
        this.numeroNota = numeroNota;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public BigDecimal getValorDesconto() {
        return valorDesconto;
    }

    public void setValorDesconto(BigDecimal valorDesconto) {
        this.valorDesconto = valorDesconto;
    }

    public BigDecimal getValorIcms() {
        return valorIcms;
    }

    public void setValorIcms(BigDecimal valorIcms) {
        this.valorIcms = valorIcms;
    }

    public Date getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(Date dataCompra) {
        this.dataCompra = dataCompra;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public ContaPagar getContaPagar() {
        return contaPagar;
    }

    public void setContaPagar(ContaPagar contaPagar) {
        this.contaPagar = contaPagar;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof NotaFiscalCompra)) return false;
        NotaFiscalCompra that = (NotaFiscalCompra) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "NotaFiscalCompra{" +
                "id=" + id +
                ", numeroNota='" + numeroNota + '\'' +
                ", serie='" + serie + '\'' +
                ", descricao='" + descricao + '\'' +
                ", valorTotal=" + valorTotal +
                ", valorDesconto=" + valorDesconto +
                ", valorIcms=" + valorIcms +
                ", dataCompra=" + dataCompra +
                ", pessoa=" + pessoa +
                ", contaPagar=" + contaPagar +
                '}';
    }
}
