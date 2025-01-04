package com.barbosacode.lojavirtual.models;

import com.barbosacode.lojavirtual.enums.StatusContaReceber;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;


@Entity
@Table(name = "conta_receber")
@SequenceGenerator(name = "seq_conta_receber", sequenceName = "seq_conta_receber", allocationSize = 1, initialValue = 1)
public class ContaReceber implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_conta_receber")
    private Long id;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusContaReceber status;

    @Temporal(TemporalType.DATE)
    @Column(name = "data_vencimento", nullable = false)
    private Date dataVencimento;

    @Temporal(TemporalType.DATE)
    @Column(name = "data_pagamento")
    private Date dataPagamento;

    @Column(name = "valor_total", nullable = false)
    private BigDecimal valorTotal;

    @Column(name = "valor_desconto")
    private BigDecimal valorDesconto;

    @ManyToOne(targetEntity = Pessoa.class) // Muitas conta a receber para uma pessoa
    @JoinColumn(name = "id_pessoa", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "conta_receber_pessoa_fk"))
    private Pessoa pessoa;

    public ContaReceber() {
    }

    public ContaReceber(
            Long id,
            String descricao,
            StatusContaReceber status,
            Date dataVencimento,
            Date dataPagamento,
            BigDecimal valorTotal,
            BigDecimal valorDesconto,
            Pessoa pessoa) {
        this.id = id;
        this.descricao = descricao;
        this.status = status;
        this.dataVencimento = dataVencimento;
        this.dataPagamento = dataPagamento;
        this.valorTotal = valorTotal;
        this.valorDesconto = valorDesconto;
        this.pessoa = pessoa;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public StatusContaReceber getStatus() {
        return status;
    }

    public void setStatus(StatusContaReceber status) {
        this.status = status;
    }

    public Date getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(Date dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public Date getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
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

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ContaReceber that)) return false;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "ContaReceber{" +
                "id=" + id +
                ", descricao='" + descricao + '\'' +
                ", status=" + status +
                ", dataVencimento=" + dataVencimento +
                ", dataPagamento=" + dataPagamento +
                ", valorTotal=" + valorTotal +
                ", valorDesconto=" + valorDesconto +
                ", pessoa=" + pessoa +
                '}';
    }
}
