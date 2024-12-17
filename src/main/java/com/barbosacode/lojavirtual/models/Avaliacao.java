package com.barbosacode.lojavirtual.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "avaliacao")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@SequenceGenerator(name = "seq_avaliacao", sequenceName = "seq_avaliacao", allocationSize = 1, initialValue = 1)
public class Avaliacao implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_avaliacao")
    @EqualsAndHashCode.Include
    private Long id;

    private String descricao;
    private Integer nota;
    @Temporal(TemporalType.DATE)
    @Column(name = "data_avaliacao")
    private Date dataAvaliacao;

    @ManyToOne
    @JoinColumn(name = "id_produto", nullable = false,
    foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "fk_avaliacao_produto"))
    private Produto produto;

    @ManyToOne
    @JoinColumn(name = "id_pessoa", nullable = false,
    foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "fk_avaliacao_pessoa"))
    private Pessoa pessoa;


}
