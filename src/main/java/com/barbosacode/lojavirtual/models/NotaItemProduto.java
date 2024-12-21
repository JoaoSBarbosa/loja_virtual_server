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
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "nota_item_produto")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@SequenceGenerator(name = "seq_nota_item_produto", sequenceName = "seq_nota_item_produto", allocationSize = 1, initialValue = 1)
public class NotaItemProduto implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @EqualsAndHashCode.Include
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


}

