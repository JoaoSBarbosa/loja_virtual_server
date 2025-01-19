
package com.barbosacode.lojavirtual.models;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.security.core.GrantedAuthority;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "acesso")
@SequenceGenerator(name = "seq_acesso", sequenceName = "seq_acesso", allocationSize = 1, initialValue = 1)
public class Acesso implements GrantedAuthority{

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_acesso")
	private Long id;
	
	@Column(nullable = false)
	private String descricao;


	@JsonIgnore
	@Override
	public String getAuthority() {
		return this.descricao;
	}

	public Acesso(){}

	public Acesso(String descricao) {
		this.descricao = descricao;
	}

	public Acesso(Long id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Acesso [id=" + id + ", Descrição=" + descricao + "]";
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Acesso acesso = (Acesso) o;
		return Objects.equals(id, acesso.id);
	}


	@Override
	public int hashCode() {
		return Objects.hashCode(id);
	}
}
