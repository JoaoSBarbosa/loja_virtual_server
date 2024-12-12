package com.barbosacode.lojavirtual.models;

import com.barbosacode.lojavirtual.enums.StatusContaReceber;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "conta_receber")
@SequenceGenerator(name = "seq_conta_receber", sequenceName = "seq_conta_receber", allocationSize = 1, initialValue = 1)
public class ContaReceber implements Serializable {
    @Serial
    private static final long serialVersionUID =1L;
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_conta_receber")
    private Long id;

    private String descricao;
    @Enumerated(EnumType.STRING)
    private StatusContaReceber status;
    @Temporal(TemporalType.DATE)
    @Column(name = "data_vencimento")
    private Date dataVencimento;
    @Temporal(TemporalType.DATE)
    @Column(name = "data_pagamento")
    private Date dataPagamento;
    @Column(name = "valor_total")
    private BigDecimal valorTotal;
    @Column(name = "valor_desconto")
    private BigDecimal valorDesconto;

    @ManyToOne(targetEntity = Pessoa.class) // Muitas conta a receber para uma pessoa
    @JoinColumn(name = "id_pessoa", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "conta_receber_pessoa_fk"))
    private Pessoa pessoa;
}
