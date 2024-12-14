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
@Table(name = "status_rastreio")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@SequenceGenerator(name = "seq_status_rastreio", sequenceName = "seq_status_rastreio", allocationSize = 1, initialValue = 1)
public class StatusRastreio implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "seq_status_rastreio")
    private Long id;
    @Column(name = "centro_distribuicao")
    private String centroDistribuicao;
    private String cidade;
    private String estado;
    private String status;

//    @ManyToOne(targetEntity = VendaCompraLoja.class)
//    @JoinColumn(name = "id_venda_compra_loja", nullable = false,
//    foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "fk_venda_compra_loja"))
//    private VendaCompraLoja vendaCompraLoja;
}
