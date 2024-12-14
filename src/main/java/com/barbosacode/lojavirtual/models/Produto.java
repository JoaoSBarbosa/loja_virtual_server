package com.barbosacode.lojavirtual.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "produto")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@SequenceGenerator(name = "seq_produto", sequenceName = "seq_produto", allocationSize = 1, initialValue = 1)
public class Produto implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "seq_produto")
    private Long id;
    private String nome;
    @Column(name = "descricao_curta")
    private String descricaoCurta;
    @Column(name = "descricao_longa", columnDefinition = "TEXT",length = 10000)
    private String descricaoLonga;
    @Column(name = "valor_compra")
    private BigDecimal valorCompra = BigDecimal.ZERO;
    @Column(name = "valor_venda")
    private BigDecimal valorVenda = BigDecimal.ZERO;
    @Column(name = "tipo_unidade")
    private String tipoUnidade;
    private Boolean ativo = Boolean.TRUE;
    private Double peso;
    private Double largura;
    private Double altura;
    private Double profundidade;
    @Column(name = "quantidade_estoque_atual")
    private Integer quantidadeEstoqueAtual = 0;
    @Column(name = "quantidade_alerta_estoque")
    private Integer quantidadeAlertaEstoque = 0;
    @Column(name = "quantidade_estoque_atual_anterior")
    private Integer quantidadeEstoqueAtualAnterior = 0;
    @Column(name = "alerta_estoque_baixo_ativo")
    private Boolean alertaEstqueBaixoAtivo = Boolean.FALSE;
    @Column(name = "url_video_youtube")
    private String urlVideo;
    @Column(name = "quantidade_clique")
    private Integer quantidadeClique = 0;

    @ManyToOne(targetEntity = MarcaProduto.class)
    @JoinColumn(
            name = "id_marca_produto",
            nullable = false,
            foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "fk_marca_produto"))
    private MarcaProduto marcaProduto;

    @ManyToOne(targetEntity = CategoriaProduto.class)
    @JoinColumn(
            name = "id_categoria_produto",
            foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT,
            name = "fk_categoria_produto")
    )
    private CategoriaProduto categoriaProduto;
}
