package com.barbosacode.lojavirtual.models;
import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "venda_compra")
@SequenceGenerator(name = "seq_venda_compra", sequenceName = "seq_venda_compra", allocationSize = 1, initialValue = 1)
public class VendaCompra implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_venda_compra")
    private Long id;

    @Column(name = "valor_total", nullable = false)
    private BigDecimal valorTotal;
    @Column(name = "valor_desconto")
    private BigDecimal valorDesconto;
    @Column(name = "valor_frete",nullable = false)
    private BigDecimal valorFrete = BigDecimal.ZERO;
    @Column(name = "quantidade_dias_entrega",nullable = false)
    private Integer quantidadeDiasEntrega = 0;

    @Temporal(TemporalType.DATE)
    @Column(name = "data_venda",nullable = false)
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
    @JoinColumn(name = "id_cumpom_desconto",
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


    public VendaCompra() {}

    public VendaCompra(Long id, NotaFiscalVenda notaFiscalVenda, Endereco enderecoEntrega, Endereco enderecoCobranca, CupomDesconto cupomDesconto, FormaPagamento formaPagamento, Pessoa pessoa, Date dataEntrega, Date dataVenda, Integer quantidadeDiasEntrega, BigDecimal valorFrete, BigDecimal valorDesconto, BigDecimal valorTotal) {
        this.id = id;
        this.notaFiscalVenda = notaFiscalVenda;
        this.enderecoEntrega = enderecoEntrega;
        this.enderecoCobranca = enderecoCobranca;
        this.cupomDesconto = cupomDesconto;
        this.formaPagamento = formaPagamento;
        this.pessoa = pessoa;
        this.dataEntrega = dataEntrega;
        this.dataVenda = dataVenda;
        this.quantidadeDiasEntrega = quantidadeDiasEntrega;
        this.valorFrete = valorFrete;
        this.valorDesconto = valorDesconto;
        this.valorTotal = valorTotal;
    }

    public Endereco getEnderecoCobranca() {
        return enderecoCobranca;
    }

    public void setEnderecoCobranca(Endereco enderecoCobranca) {
        this.enderecoCobranca = enderecoCobranca;
    }

    public Endereco getEnderecoEntrega() {
        return enderecoEntrega;
    }

    public void setEnderecoEntrega(Endereco enderecoEntrega) {
        this.enderecoEntrega = enderecoEntrega;
    }

    public NotaFiscalVenda getNotaFiscalVenda() {
        return notaFiscalVenda;
    }

    public void setNotaFiscalVenda(NotaFiscalVenda notaFiscalVenda) {
        this.notaFiscalVenda = notaFiscalVenda;
    }

    public CupomDesconto getCupomDesconto() {
        return cupomDesconto;
    }

    public void setCupomDesconto(CupomDesconto cupomDesconto) {
        this.cupomDesconto = cupomDesconto;
    }

    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(FormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Date getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(Date dataVenda) {
        this.dataVenda = dataVenda;
    }

    public Date getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(Date dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public Integer getQuantidadeDiasEntrega() {
        return quantidadeDiasEntrega;
    }

    public void setQuantidadeDiasEntrega(Integer quantidadeDiasEntrega) {
        this.quantidadeDiasEntrega = quantidadeDiasEntrega;
    }

    public BigDecimal getValorFrete() {
        return valorFrete;
    }

    public void setValorFrete(BigDecimal valorFrete) {
        this.valorFrete = valorFrete;
    }

    public BigDecimal getValorDesconto() {
        return valorDesconto;
    }

    public void setValorDesconto(BigDecimal valorDesconto) {
        this.valorDesconto = valorDesconto;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof VendaCompra)) return false;
        VendaCompra that = (VendaCompra) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "VendaCompra{" +
                "id=" + id +
                ", valorTotal=" + valorTotal +
                ", valorDesconto=" + valorDesconto +
                ", valorFrete=" + valorFrete +
                ", quantidadeDiasEntrega=" + quantidadeDiasEntrega +
                ", dataVenda=" + dataVenda +
                ", dataEntrega=" + dataEntrega +
                ", pessoa=" + pessoa +
                ", formaPagamento=" + formaPagamento +
                ", cupomDesconto=" + cupomDesconto +
                ", enderecoEntrega=" + enderecoEntrega +
                ", enderecoCobranca=" + enderecoCobranca +
                ", notaFiscalVenda=" + notaFiscalVenda +
                '}';
    }
}
