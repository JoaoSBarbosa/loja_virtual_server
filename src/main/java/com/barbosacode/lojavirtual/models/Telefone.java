package com.barbosacode.lojavirtual.models;
import com.barbosacode.lojavirtual.enums.TipoTelefone;
import javax.persistence.*;
import java.util.Objects;


@Entity
@Table(name = "telefone")
@SequenceGenerator(name = "seq_telefone", sequenceName = "seq_telefone", allocationSize = 1, initialValue = 1)
public class Telefone {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_telefone")
	private Long id;
	@Column(nullable = false)
	private String ddd;
	@Column(nullable = false)
	private String numero;
	@Enumerated(EnumType.STRING)
	@Column(name = "tipo_telefone")
	private TipoTelefone tipoTelefone;
	@ManyToOne(targetEntity = Pessoa.class)
	@JoinColumn(name = "id_pessoa", nullable = false,
	foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "fk_telefone_pessoa"))
	private Pessoa pessoaTelefone;

	public Telefone() {}

	public Telefone(
			Long id,
			String ddd,
			String numero,
			TipoTelefone tipoTelefone,
			Pessoa pessoaTelefone) {
		this.id = id;
		this.ddd = ddd;
		this.numero = numero;
		this.tipoTelefone = tipoTelefone;
		this.pessoaTelefone = pessoaTelefone;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDdd() {
		return ddd;
	}

	public void setDdd(String ddd) {
		this.ddd = ddd;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public TipoTelefone getTipoTelefone() {
		return tipoTelefone;
	}

	public void setTipoTelefone(TipoTelefone tipoTelefone) {
		this.tipoTelefone = tipoTelefone;
	}

	public Pessoa getPessoaTelefone() {
		return pessoaTelefone;
	}

	public void setPessoaTelefone(Pessoa pessoaTelefone) {
		this.pessoaTelefone = pessoaTelefone;
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Telefone telefone)) return false;
        return Objects.equals(id, telefone.id);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(id);
	}

	@Override
	public String toString() {
		return "Telefone{" +
				"id=" + id +
				", ddd='" + ddd + '\'' +
				", numero='" + numero + '\'' +
				", tipoTelefone=" + tipoTelefone +
				", pessoaTelefone=" + pessoaTelefone +
				'}';
	}
}
