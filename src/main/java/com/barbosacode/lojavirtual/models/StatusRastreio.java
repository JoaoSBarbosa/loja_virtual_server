package com.barbosacode.lojavirtual.models;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "status_rastreio")
@SequenceGenerator(name = "seq_status_rastreio", sequenceName = "seq_status_rastreio", allocationSize = 1, initialValue = 1)
public class StatusRastreio implements Serializable {


    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "seq_status_rastreio")
    private Long id;
    @Column(name = "centro_distribuicao")
    private String centroDistribuicao;
    private String cidade;
    private String estado;
    private String status;

    @ManyToOne(targetEntity = VendaCompra.class)
    @JoinColumn(name = "id_venda_compra_loja", nullable = false,
    foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "fk_venda_compra_loja"))
    private VendaCompra vendaCompra;


    public StatusRastreio() {}

    public StatusRastreio(Long id, String centroDistribuicao, String cidade, String estado, String status, VendaCompra vendaCompra) {
        this.id = id;
        this.centroDistribuicao = centroDistribuicao;
        this.cidade = cidade;
        this.estado = estado;
        this.status = status;
        this.vendaCompra = vendaCompra;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCentroDistribuicao() {
        return centroDistribuicao;
    }

    public void setCentroDistribuicao(String centroDistribuicao) {
        this.centroDistribuicao = centroDistribuicao;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public VendaCompra getVendaCompra() {
        return vendaCompra;
    }

    public void setVendaCompra(VendaCompra vendaCompra) {
        this.vendaCompra = vendaCompra;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof StatusRastreio)) return false;
        StatusRastreio that = (StatusRastreio) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "StatusRastreio{" +
                "id=" + id +
                ", centroDistribuicao='" + centroDistribuicao + '\'' +
                ", cidade='" + cidade + '\'' +
                ", estado='" + estado + '\'' +
                ", status='" + status + '\'' +
                ", vendaCompra=" + vendaCompra +
                '}';
    }
}
