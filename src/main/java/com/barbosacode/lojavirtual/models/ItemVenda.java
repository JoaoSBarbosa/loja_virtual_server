package com.barbosacode.lojavirtual.models;
import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "item_venda")
@SequenceGenerator(name = "seq_item_venda", sequenceName = "seq_item_venda", allocationSize = 1, initialValue = 1)
public class ItemVenda implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_item_venda")
    private Long id;

    @Column(nullable = false)
    private Double quantidade;

    @ManyToOne
    @JoinColumn(name = "id_venda_compra", nullable = false,
    foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "fk_item_venda_compra"))
    private VendaCompra vendaCompra;

    @ManyToOne
    @JoinColumn(name = "id_produto", nullable = false,
    foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "fk_item_venda_produto"))
    private Produto produto;

    public ItemVenda() {}

    public ItemVenda(Long id, Double quantidade, VendaCompra vendaCompra, Produto produto) {
        this.id = id;
        this.quantidade = quantidade;
        this.vendaCompra = vendaCompra;
        this.produto = produto;
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

    public VendaCompra getVendaCompra() {
        return vendaCompra;
    }

    public void setVendaCompra(VendaCompra vendaCompra) {
        this.vendaCompra = vendaCompra;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ItemVenda itemVenda)) return false;
        return Objects.equals(id, itemVenda.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "ItemVenda{" +
                "id=" + id +
                ", quantidade=" + quantidade +
                ", vendaCompra=" + vendaCompra +
                ", produto=" + produto +
                '}';
    }
}
