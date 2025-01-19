package com.barbosacode.lojavirtual.models;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "nota_item_produto")
@SequenceGenerator(name = "seq_nota_item_produto", sequenceName = "seq_nota_item_produto", allocationSize = 1, initialValue = 1)
public class NotaItemProduto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_nota_item_produto")
    private Long id;

    @Column(nullable = false)
    private Double quantidade;

    @ManyToOne(targetEntity = NotaItemProduto.class)
    @JoinColumn(name = "id_nota_fiscal_compra",
            nullable = false,
            foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT,name = "fk_nfe_compra_item"))
    private NotaFiscalCompra notaFiscalCompra;

    @ManyToOne(targetEntity = NotaItemProduto.class)
    @JoinColumn(name = "id_produto", nullable = false,
    foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "fk_produto_nota_item"))
    private Produto produto;

    public NotaItemProduto() {}

    public NotaItemProduto(Long id, Produto produto, NotaFiscalCompra notaFiscalCompra, Double quantidade) {
        this.id = id;
        this.produto = produto;
        this.notaFiscalCompra = notaFiscalCompra;
        this.quantidade = quantidade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }

    public NotaFiscalCompra getNotaFiscalCompra() {
        return notaFiscalCompra;
    }

    public void setNotaFiscalCompra(NotaFiscalCompra notaFiscalCompra) {
        this.notaFiscalCompra = notaFiscalCompra;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof NotaItemProduto)) return false;
        NotaItemProduto that = (NotaItemProduto) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "NotaItemProduto{" +
                "id=" + id +
                ", quantidade=" + quantidade +
                ", notaFiscalCompra=" + notaFiscalCompra +
                ", produto=" + produto +
                '}';
    }
}

