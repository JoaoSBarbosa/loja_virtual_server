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
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "nota_fiscal_venda")
@SequenceGenerator(name = "seq_nota_fiscal_venda", sequenceName = "seq_nota_fiscal_venda", allocationSize = 1, initialValue = 1)
public class NotaFiscalVenda implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_nota_fiscal_venda")
    private Long id;
    @Column(name = "numero_nota_fiscal", nullable = false)
    private String numeroNotaFiscal;
    @Column(nullable = false)
    private String serie;
    @Column(nullable = false)
    private String tipo;
    @Column(columnDefinition = "TEXT", nullable = false)
    private String XML;
    @Column(columnDefinition = "TEXT", nullable = false)
    private String PDF;

    @OneToOne
    @JoinColumn(name = "id_venda_compra",
            nullable = false,
            foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "fk_venda_compra_nfe"))
    private VendaCompra vendaCompra;
}
