package com.barbosacode.lojavirtual.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "nota_fiscal_compra")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@SequenceGenerator(name = "seq_nota_fiscal_compra", sequenceName = "seq_nota_fiscal_compra", allocationSize = 1, initialValue = 1)
public class NotaFiscalCompra implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "seq_nota_fiscal_compra")
    private Long id;
    @Column(name = "numero_nota")
    private String numeroNota;

    private String serie;
    private String descricao;
    @Column(name = "valor_total")
    private BigDecimal valorTotal;
    @Column(name = "valor_desconto")
    private BigDecimal valorDesconto;

    @Column(name = "valor_icms")
    private BigDecimal valorIcms;

    @Temporal(TemporalType.DATE)
    @Column(name = "data_compra")
    private Date dataCompra;


    @ManyToOne(targetEntity = Pessoa.class)
    @JoinColumn(
            name = "id_pessoa",
            nullable = false,
            foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "fk_pessoa_nfe_compra"))
    private Pessoa pessoa;

    @OneToOne
    @JoinColumn(name = "id_conta_pagar", foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "fk_conta_pagar_nfe_compra"))
    private ContaPagar contaPagar;
}
