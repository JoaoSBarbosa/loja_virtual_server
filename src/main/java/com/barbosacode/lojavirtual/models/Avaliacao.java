package com.barbosacode.lojavirtual.models;
import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "avaliacao")
@SequenceGenerator(name = "seq_avaliacao", sequenceName = "seq_avaliacao", allocationSize = 1, initialValue = 1)
public class Avaliacao implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_avaliacao")
    private Long id;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private Integer nota;

    @Temporal(TemporalType.DATE)
    @Column(name = "data_avaliacao")
    private Date dataAvaliacao;

    @ManyToOne
    @JoinColumn(name = "id_produto", nullable = false,
    foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "fk_avaliacao_produto"))
    private Produto produto;

    @ManyToOne(targetEntity = Pessoa.class)
    @JoinColumn(name = "id_pessoa", nullable = false,
    foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "fk_avaliacao_pessoa"))
    private Pessoa pessoa;

    public Avaliacao() {}

    public Avaliacao(Long id, String descricao, Integer nota, Date dataAvaliacao, Produto produto, Pessoa pessoa) {
        this.id = id;
        this.descricao = descricao;
        this.nota = nota;
        this.dataAvaliacao = dataAvaliacao;
        this.produto = produto;
        this.pessoa = pessoa;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getNota() {
        return nota;
    }

    public void setNota(Integer nota) {
        this.nota = nota;
    }

    public Date getDataAvaliacao() {
        return dataAvaliacao;
    }

    public void setDataAvaliacao(Date dataAvaliacao) {
        this.dataAvaliacao = dataAvaliacao;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Avaliacao avaliacao)) return false;
        return Objects.equals(id, avaliacao.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Avaliacao{" +
                "id=" + id +
                ", descricao='" + descricao + '\'' +
                ", nota=" + nota +
                ", dataAvaliacao=" + dataAvaliacao +
                ", produto=" + produto +
                ", pessoa=" + pessoa +
                '}';
    }
}
