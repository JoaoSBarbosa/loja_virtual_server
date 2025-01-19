package com.barbosacode.lojavirtual.models;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "nota_fiscal_venda")
@SequenceGenerator(name = "seq_nota_fiscal_venda", sequenceName = "seq_nota_fiscal_venda", allocationSize = 1, initialValue = 1)
public class NotaFiscalVenda implements Serializable {

    @Id
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

    public NotaFiscalVenda() {}

    public NotaFiscalVenda(Long id, String numeroNotaFiscal, String serie, String tipo, String XML, String PDF, VendaCompra vendaCompra) {
        this.id = id;
        this.numeroNotaFiscal = numeroNotaFiscal;
        this.serie = serie;
        this.tipo = tipo;
        this.XML = XML;
        this.PDF = PDF;
        this.vendaCompra = vendaCompra;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroNotaFiscal() {
        return numeroNotaFiscal;
    }

    public void setNumeroNotaFiscal(String numeroNotaFiscal) {
        this.numeroNotaFiscal = numeroNotaFiscal;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getXML() {
        return XML;
    }

    public void setXML(String XML) {
        this.XML = XML;
    }

    public String getPDF() {
        return PDF;
    }

    public void setPDF(String PDF) {
        this.PDF = PDF;
    }

    public VendaCompra getVendaCompra() {
        return vendaCompra;
    }

    public void setVendaCompra(VendaCompra vendaCompra) {
        this.vendaCompra = vendaCompra;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof NotaFiscalVenda)) return false;
        NotaFiscalVenda that = (NotaFiscalVenda) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "NotaFiscalVenda{" +
                "id=" + id +
                ", numeroNotaFiscal='" + numeroNotaFiscal + '\'' +
                ", serie='" + serie + '\'' +
                ", tipo='" + tipo + '\'' +
                ", XML='" + XML + '\'' +
                ", PDF='" + PDF + '\'' +
                ", vendaCompra=" + vendaCompra +
                '}';
    }
}
