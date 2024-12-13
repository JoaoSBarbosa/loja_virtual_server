package com.barbosacode.lojavirtual.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;

@Data
@Entity
@Table(name = "imagem_produto")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@SequenceGenerator(name = "seq_imagem_produto",sequenceName = "seq_imagem_produto", allocationSize = 1,initialValue = 1)
public class ImagemProduto implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_imagem_produto")
    private Long id;

    @Column(name = "imagem_original", columnDefinition = "TEXT")
    private String imagemOriginal;
    @Column(name = "imagem_miniatura", columnDefinition = "TEXT")
    private String imagemMiniatura;

    @ManyToOne( targetEntity = Produto.class, fetch = FetchType.EAGER )
    @JoinColumn(
            name = "id_produto",
            nullable = false,
            foreignKey = @ForeignKey(
                    value = ConstraintMode.CONSTRAINT,
                    name = "fk_produto"))
    private Produto produto;
}
