package com.barbosacode.lojavirtual.models;
import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "imagem_produto")
@SequenceGenerator(name = "seq_imagem_produto",sequenceName = "seq_imagem_produto", allocationSize = 1,initialValue = 1)
public class ImagemProduto implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_imagem_produto")
    private Long id;

    @Column(name = "imagem_original", columnDefinition = "TEXT", nullable = false)
    private String imagemOriginal;
    @Column(name = "imagem_miniatura", columnDefinition = "TEXT", nullable = false)
    private String imagemMiniatura;

    @ManyToOne( targetEntity = Produto.class, fetch = FetchType.EAGER )
    @JoinColumn(name = "id_produto", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "fk_produto"))
    private Produto produto;

    public ImagemProduto() {}

    public ImagemProduto(Long id, String imagemOriginal, String imagemMiniatura, Produto produto) {
        this.id = id;
        this.imagemOriginal = imagemOriginal;
        this.imagemMiniatura = imagemMiniatura;
        this.produto = produto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImagemOriginal() {
        return imagemOriginal;
    }

    public void setImagemOriginal(String imagemOriginal) {
        this.imagemOriginal = imagemOriginal;
    }

    public String getImagemMiniatura() {
        return imagemMiniatura;
    }

    public void setImagemMiniatura(String imagemMiniatura) {
        this.imagemMiniatura = imagemMiniatura;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ImagemProduto that)) return false;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "ImagemProduto{" +
                "id=" + id +
                ", imagemOriginal='" + imagemOriginal + '\'' +
                ", imagemMiniatura='" + imagemMiniatura + '\'' +
                ", produto=" + produto +
                '}';
    }
}
