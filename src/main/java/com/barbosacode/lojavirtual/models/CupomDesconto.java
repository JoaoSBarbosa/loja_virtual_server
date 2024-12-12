package com.barbosacode.lojavirtual.models;

import lombok.*;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


@Data
@Entity
@Table(name = "cupom_desconto")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@SequenceGenerator(name = "seq_cupom_desconto", sequenceName = "seq_cupom_desconto", allocationSize = 1, initialValue = 1)
public class CupomDesconto implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_cupom_desconto")
    private Long id;
    @Column(name = "codigo_desconto")
    private String codigoDesconto;
    @Column(name = "valor_real_desconto")
    private BigDecimal valorRealDesconto;
    @Column(name = "valor_percentual_desconto")
    private BigDecimal valorPorcentualDesconto;
    @Temporal(TemporalType.DATE)
    @Column(name = "data_limite")
    private Date dataLimite;
}
