package com.barbosacode.lojavirtual.models;

import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "pessoa")
@SequenceGenerator(name = "seq_pessoa", sequenceName = "seq_pessoa", allocationSize = 1, initialValue = 1)
public abstract class Pessoa implements Serializable {

    @Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_pessoa")
	private Long id;

	@Column(nullable = false)
	private String nome;
	@Column(name = "sobrenome")
	private String sobrenome;
	@Column(nullable = false)
	private String email;
	
	@OneToMany(mappedBy = "pessoaTelefone")
	private List<Telefone> telefones = new ArrayList<>();
	
	@OneToMany(mappedBy = "pessoaEndereco")
	private List<Endereco> enderecos = new ArrayList<>();


	@Override
	public String toString() {
		return "Pessoa [id=" + id + ", nome=" + nome + ", sobreNome=" + sobrenome + ", email=" + email + ", telefones="
				+ telefones + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pessoa other = (Pessoa) obj;
		return Objects.equals(id, other.id);
	}

	
	
	
}
