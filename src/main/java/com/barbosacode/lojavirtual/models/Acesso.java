
package com.barbosacode.lojavirtual.models;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.springframework.security.core.GrantedAuthority;

import java.util.Objects;

@Entity
@Table(name = "acesso")
@SequenceGenerator(name = "seq_acesso", sequenceName = "seq_acesso", allocationSize = 1, initialValue = 1)
public class gitAcesso implements GrantedAuthority{

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_acesso")
	private Long id;
	
	@Column(nullable = false)
	private String descricao;
	
	
	@Override
	public String getAuthority() {
		return this.descricao;
	}

	public Acesso(){}


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
		if (!(o instanceof Acesso acesso)) return false;
        return Objects.equals(id, acesso.id);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(id);
	}
}
