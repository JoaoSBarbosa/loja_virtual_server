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
@Table(name = "venda_compra")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@SequenceGenerator(name = "seq_venda_compra", sequenceName = "seq_venda_compra", allocationSize = 1, initialValue = 1)
public class VendaCompra implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_venda_compra")
    private Long id;

    @Column(name = "valor_total")
    private BigDecimal valorTotal;
    @Column(name = "valor_desconto")
    private BigDecimal valorDesconto;
    @Column(name = "valor_frete")
    private BigDecimal valorFrete;
    @Column(name = "quantidade_dias_entrega")
    private Integer quantidadeDiasEntrega;

    @Temporal(TemporalType.DATE)
    @Column(name = "data_venda")
    private Date dataVenda;
    @Temporal(TemporalType.DATE)
    @Column(name = "data_entrega")
    private Date dataEntrega;

    @ManyToOne(targetEntity = Pessoa.class)
    @JoinColumn(name = "id_pessoa", nullable = false,
    foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "fk_pessoa_compra_venda"))
    private Pessoa pessoa;

    @ManyToOne(targetEntity = FormaPagamento.class)
    @JoinColumn(name = "id_forma_pagamento", nullable = false,
    foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "fk_forma_pagamento_venda_compra"))
    private FormaPagamento formaPagamento;

    @ManyToOne(targetEntity = CupomDesconto.class)
    @JoinColumn(name = "id_cumpom_desconto", nullable = false,
    foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "fk_cupom_desconto_compra_venda"))
    private CupomDesconto cupomDesconto;

    @ManyToOne(targetEntity = Endereco.class)
    @JoinColumn(name = "id_endereco_entrega", nullable = false,
    foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "fk_endereco_entrega_compra_venda"))
    private Endereco enderecoEntrega;

    @ManyToOne(targetEntity = Endereco.class)
    @JoinColumn(name = "id_endereco_cobranca",
    foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "fk_endereco_cobraca_compra_venda"))
    private Endereco enderecoCobranca;

//    @OneToOne
//    @JoinColumn(name = "id_nota_fiscal_venda", nullable = false,
//    foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "fk_nota_fiscal_venda"))
//    private NotaFiscalVenda notaFiscalVenda;

    @OneToOne(mappedBy = "vendaCompra", cascade = CascadeType.ALL, orphanRemoval = true)
    private NotaFiscalVenda notaFiscalVenda;
}
