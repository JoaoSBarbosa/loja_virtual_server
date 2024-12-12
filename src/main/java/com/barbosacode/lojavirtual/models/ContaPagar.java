package com.barbosacode.lojavirtual.models;

import com.barbosacode.lojavirtual.enums.StatusContaPagar;
import lombok.*;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@ToString
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "conta_pagar")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@SequenceGenerator(name = "seq_conta_pagar", sequenceName = "seq_conta_pagar", allocationSize = 1, initialValue = 1)
public class ContaPagar implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "seq_conta_pagar")
    private Long id;
    private BigDecimal valorTotal;
    private BigDecimal valorDesconto;
    private BigDecimal valorPago;
    private String descricao;
    @Temporal(TemporalType.DATE)
    @Column(name = "data_vencimento")
    private Date dataVencimento;
    @Column(name = "data_pagamento")
    @Temporal(TemporalType.DATE)
    private Date dataPagamento;

    @Enumerated(EnumType.STRING)
    private StatusContaPagar status;

    @ManyToOne(targetEntity = Pessoa.class)
    @JoinColumn(
            name = "id_pessoa",
            nullable = false,
            foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "conta_pagar_pessoa_fk"))
    private Pessoa pessoa;

    @ManyToOne(targetEntity = Pessoa.class)
    @JoinColumn(
            name = "id_fornecedor",
            nullable = false,
            foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "conta_pagar_fornecedor_fk"))
    private Pessoa fornecedor;

    @ManyToOne(targetEntity = FormaPagamento.class)
    @JoinColumn(
            name = "id_forma_pagamento",
            nullable = false,
            foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "conta_pagar_forma_pagamento_fk"))
    private FormaPagamento formaPagamento;



}
