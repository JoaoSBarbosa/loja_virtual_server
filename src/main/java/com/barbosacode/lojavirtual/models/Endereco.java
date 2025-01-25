package com.barbosacode.lojavirtual.models;
import javax.persistence.*;
import com.barbosacode.lojavirtual.enums.TipoEndereco;
import com.fasterxml.jackson.annotation.JsonBackReference;

import java.util.Objects;

@Entity
@Table(name = "endereco")
@SequenceGenerator(name = "seq_endereco",sequenceName = "seq_endereco",allocationSize = 1,initialValue = 1)
public class Endereco {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_endereco")
	private Long id;

	@Column(nullable = false)
	private String cep;

	@Column(nullable = false)
	private String longradouro;

	@Column(nullable = false)
	private String numero;

	private String complemento;

	@Column(nullable = false)
	private String bairro;

	@Column(nullable = false)
	private String uf;

	@Column(nullable = false)
	private String cidade;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private TipoEndereco tipoEndereco;
	
	@ManyToOne(targetEntity = Pessoa.class) // Define o relacionamento "muitos-para-um" com a entidade Pessoa.
	@JsonBackReference
	@JoinColumn(
	    name = "id_pessoa", // Nome da coluna que será criada na tabela Endereco para armazenar a chave estrangeira (ID de Pessoa).
	    nullable = false, // Indica que essa coluna é obrigatória e não pode conter valores nulos.
	    foreignKey = @ForeignKey( // Configura a chave estrangeira e sua restrição no banco de dados.
	        value = ConstraintMode.CONSTRAINT, // Habilita a restrição de chave estrangeira no banco.
	        name = "fk_endereco_pessoa" // Nome da restrição de chave estrangeira no banco de dados. 
	    )
	)
	private Pessoa pessoaEndereco; // Representa a associação entre Endereco e Pessoa.

	public Endereco(){}

	public Endereco(Long id, String cep, String longradouro, String numero, String complemento, String bairro, String uf, String cidade, TipoEndereco tipoEndereco, Pessoa pessoaEndereco) {
		this.id = id;
		this.cep = cep;
		this.longradouro = longradouro;
		this.numero = numero;
		this.complemento = complemento;
		this.bairro = bairro;
		this.uf = uf;
		this.cidade = cidade;
		this.tipoEndereco = tipoEndereco;
		this.pessoaEndereco = pessoaEndereco;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getLongradouro() {
		return longradouro;
	}

	public void setLongradouro(String longradouro) {
		this.longradouro = longradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public TipoEndereco getTipoEndereco() {
		return tipoEndereco;
	}

	public void setTipoEndereco(TipoEndereco tipoEndereco) {
		this.tipoEndereco = tipoEndereco;
	}

	public Pessoa getPessoaEndereco() {
		return pessoaEndereco;
	}

	public void setPessoaEndereco(Pessoa pessoaEndereco) {
		this.pessoaEndereco = pessoaEndereco;
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Endereco)) return false;
		Endereco endereco = (Endereco) o;
		return Objects.equals(id, endereco.id);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(id);
	}

	@Override
	public String toString() {
		return "Endereco{" +
				"id=" + id +
				", cep='" + cep + '\'' +
				", longradouro='" + longradouro + '\'' +
				", numero='" + numero + '\'' +
				", complemento='" + complemento + '\'' +
				", bairro='" + bairro + '\'' +
				", uf='" + uf + '\'' +
				", cidade='" + cidade + '\'' +
				", tipoEndereco=" + tipoEndereco +
				", pessoaEndereco=" + pessoaEndereco +
				'}';
	}
}
