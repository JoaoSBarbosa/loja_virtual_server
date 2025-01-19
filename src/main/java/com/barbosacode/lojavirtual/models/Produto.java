package com.barbosacode.lojavirtual.models;
import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "produto")
@SequenceGenerator(name = "seq_produto", sequenceName = "seq_produto", allocationSize = 1, initialValue = 1)
public class Produto implements Serializable {

    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "seq_produto")
    private Long id;
    @Column(nullable = false)
    private String nome;
    @Column(name = "descricao_curta", nullable = false)
    private String descricaoCurta;
    @Column(name = "descricao_longa", columnDefinition = "TEXT",length = 10000, nullable = false)
    private String descricaoLonga;
    @Column(name = "valor_compra", nullable = false)
    private BigDecimal valorCompra = BigDecimal.ZERO;
    @Column(name = "valor_venda", nullable = false)
    private BigDecimal valorVenda = BigDecimal.ZERO;
    @Column(name = "tipo_unidade", nullable = false)
    private String tipoUnidade;
    @Column(nullable = false)
    private Boolean ativo = Boolean.TRUE;
    @Column(nullable = false)
    private Double peso;
    @Column(nullable = false)
    private Double largura;
    @Column(nullable = false)
    private Double altura;
    @Column(nullable = false)
    private Double profundidade;
    @Column(name = "quantidade_estoque_atual", nullable = false)
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
            nullable = false,
            foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT,
            name = "fk_categoria_produto")
    )
    private CategoriaProduto categoriaProduto;

    public Produto() {}

    public Produto(Long id, String nome, String descricaoCurta, String descricaoLonga, BigDecimal valorCompra, BigDecimal valorVenda, String tipoUnidade, Boolean ativo, Double peso, Double largura, Double altura, Double profundidade, Integer quantidadeEstoqueAtual, Integer quantidadeAlertaEstoque, Integer quantidadeEstoqueAtualAnterior, Boolean alertaEstqueBaixoAtivo, String urlVideo, Integer quantidadeClique, MarcaProduto marcaProduto, CategoriaProduto categoriaProduto) {
        this.id = id;
        this.nome = nome;
        this.descricaoCurta = descricaoCurta;
        this.descricaoLonga = descricaoLonga;
        this.valorCompra = valorCompra;
        this.valorVenda = valorVenda;
        this.tipoUnidade = tipoUnidade;
        this.ativo = ativo;
        this.peso = peso;
        this.largura = largura;
        this.altura = altura;
        this.profundidade = profundidade;
        this.quantidadeEstoqueAtual = quantidadeEstoqueAtual;
        this.quantidadeAlertaEstoque = quantidadeAlertaEstoque;
        this.quantidadeEstoqueAtualAnterior = quantidadeEstoqueAtualAnterior;
        this.alertaEstqueBaixoAtivo = alertaEstqueBaixoAtivo;
        this.urlVideo = urlVideo;
        this.quantidadeClique = quantidadeClique;
        this.marcaProduto = marcaProduto;
        this.categoriaProduto = categoriaProduto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricaoCurta() {
        return descricaoCurta;
    }

    public void setDescricaoCurta(String descricaoCurta) {
        this.descricaoCurta = descricaoCurta;
    }

    public String getDescricaoLonga() {
        return descricaoLonga;
    }

    public void setDescricaoLonga(String descricaoLonga) {
        this.descricaoLonga = descricaoLonga;
    }

    public BigDecimal getValorCompra() {
        return valorCompra;
    }

    public void setValorCompra(BigDecimal valorCompra) {
        this.valorCompra = valorCompra;
    }

    public BigDecimal getValorVenda() {
        return valorVenda;
    }

    public void setValorVenda(BigDecimal valorVenda) {
        this.valorVenda = valorVenda;
    }

    public String getTipoUnidade() {
        return tipoUnidade;
    }

    public void setTipoUnidade(String tipoUnidade) {
        this.tipoUnidade = tipoUnidade;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public Double getLargura() {
        return largura;
    }

    public void setLargura(Double largura) {
        this.largura = largura;
    }

    public Double getAltura() {
        return altura;
    }

    public void setAltura(Double altura) {
        this.altura = altura;
    }

    public Double getProfundidade() {
        return profundidade;
    }

    public void setProfundidade(Double profundidade) {
        this.profundidade = profundidade;
    }

    public Integer getQuantidadeEstoqueAtual() {
        return quantidadeEstoqueAtual;
    }

    public void setQuantidadeEstoqueAtual(Integer quantidadeEstoqueAtual) {
        this.quantidadeEstoqueAtual = quantidadeEstoqueAtual;
    }

    public Integer getQuantidadeAlertaEstoque() {
        return quantidadeAlertaEstoque;
    }

    public void setQuantidadeAlertaEstoque(Integer quantidadeAlertaEstoque) {
        this.quantidadeAlertaEstoque = quantidadeAlertaEstoque;
    }

    public Integer getQuantidadeEstoqueAtualAnterior() {
        return quantidadeEstoqueAtualAnterior;
    }

    public void setQuantidadeEstoqueAtualAnterior(Integer quantidadeEstoqueAtualAnterior) {
        this.quantidadeEstoqueAtualAnterior = quantidadeEstoqueAtualAnterior;
    }

    public Boolean getAlertaEstqueBaixoAtivo() {
        return alertaEstqueBaixoAtivo;
    }

    public void setAlertaEstqueBaixoAtivo(Boolean alertaEstqueBaixoAtivo) {
        this.alertaEstqueBaixoAtivo = alertaEstqueBaixoAtivo;
    }

    public String getUrlVideo() {
        return urlVideo;
    }

    public void setUrlVideo(String urlVideo) {
        this.urlVideo = urlVideo;
    }

    public Integer getQuantidadeClique() {
        return quantidadeClique;
    }

    public void setQuantidadeClique(Integer quantidadeClique) {
        this.quantidadeClique = quantidadeClique;
    }

    public MarcaProduto getMarcaProduto() {
        return marcaProduto;
    }

    public void setMarcaProduto(MarcaProduto marcaProduto) {
        this.marcaProduto = marcaProduto;
    }

    public CategoriaProduto getCategoriaProduto() {
        return categoriaProduto;
    }

    public void setCategoriaProduto(CategoriaProduto categoriaProduto) {
        this.categoriaProduto = categoriaProduto;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Produto)) return false;
        Produto produto = (Produto) o;
        return Objects.equals(id, produto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Produto{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", descricaoCurta='" + descricaoCurta + '\'' +
                ", descricaoLonga='" + descricaoLonga + '\'' +
                ", valorCompra=" + valorCompra +
                ", valorVenda=" + valorVenda +
                ", tipoUnidade='" + tipoUnidade + '\'' +
                ", ativo=" + ativo +
                ", peso=" + peso +
                ", largura=" + largura +
                ", altura=" + altura +
                ", profundidade=" + profundidade +
                ", quantidadeEstoqueAtual=" + quantidadeEstoqueAtual +
                ", quantidadeAlertaEstoque=" + quantidadeAlertaEstoque +
                ", quantidadeEstoqueAtualAnterior=" + quantidadeEstoqueAtualAnterior +
                ", alertaEstqueBaixoAtivo=" + alertaEstqueBaixoAtivo +
                ", urlVideo='" + urlVideo + '\'' +
                ", quantidadeClique=" + quantidadeClique +
                ", marcaProduto=" + marcaProduto +
                ", categoriaProduto=" + categoriaProduto +
                '}';
    }
}
