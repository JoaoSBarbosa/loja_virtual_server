package com.barbosacode.lojavirtual.models;
import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "cupom_desconto")
@SequenceGenerator(name = "seq_cupom_desconto", sequenceName = "seq_cupom_desconto", allocationSize = 1, initialValue = 1)
public class CupomDesconto implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_cupom_desconto")
    private Long id;
    @Column(name = "codigo_desconto", nullable = false)
    private String codigoDesconto;
    @Column(name = "valor_real_desconto")
    private BigDecimal valorRealDesconto;
    @Column(name = "valor_percentual_desconto")
    private BigDecimal valorPorcentualDesconto;
    @Temporal(TemporalType.DATE)
    @Column(name = "data_limite", nullable = false)
    private Date dataLimite;

    public CupomDesconto() {}

    public CupomDesconto(
            Long id,
            Date dataLimite,
            BigDecimal valorRealDesconto,
            String codigoDesconto,
            BigDecimal valorPorcentualDesconto
    ) {
        this.id = id;
        this.dataLimite = dataLimite;
        this.valorRealDesconto = valorRealDesconto;
        this.codigoDesconto = codigoDesconto;
        this.valorPorcentualDesconto = valorPorcentualDesconto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigoDesconto() {
        return codigoDesconto;
    }

    public void setCodigoDesconto(String codigoDesconto) {
        this.codigoDesconto = codigoDesconto;
    }

    public BigDecimal getValorRealDesconto() {
        return valorRealDesconto;
    }

    public void setValorRealDesconto(BigDecimal valorRealDesconto) {
        this.valorRealDesconto = valorRealDesconto;
    }

    public BigDecimal getValorPorcentualDesconto() {
        return valorPorcentualDesconto;
    }

    public void setValorPorcentualDesconto(BigDecimal valorPorcentualDesconto) {
        this.valorPorcentualDesconto = valorPorcentualDesconto;
    }

    public Date getDataLimite() {
        return dataLimite;
    }

    public void setDataLimite(Date dataLimite) {
        this.dataLimite = dataLimite;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof CupomDesconto that)) return false;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }


    @Override
    public String toString() {
        return "CupomDesconto{" +
                "id=" + id +
                ", codigoDesconto='" + codigoDesconto + '\'' +
                ", valorRealDesconto=" + valorRealDesconto +
                ", valorPorcentualDesconto=" + valorPorcentualDesconto +
                ", dataLimite=" + dataLimite +
                '}';
    }
}
