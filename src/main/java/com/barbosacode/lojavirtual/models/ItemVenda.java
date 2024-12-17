package com.barbosacode.lojavirtual.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "item_venda")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@SequenceGenerator(name = "seq_item_venda", sequenceName = "seq_item_venda", allocationSize = 1, initialValue = 1)
public class ItemVenda implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @EqualsAndHashCode.Include
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
}
