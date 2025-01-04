package com.barbosacode.lojavirtual.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "marca_produto")
@SequenceGenerator(name = "seq_marca_produto", sequenceName = "seq_marca_produto", allocationSize = 1, initialValue = 1)
public class MarcaProduto {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_marca_produto")
	private Long id;
	@Column(nullable = false)
	private String descricao;

	public MarcaProduto() {}

	public MarcaProduto(Long id, String descricao) {
		this.id = id;
		this.descricao = descricao;
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

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof MarcaProduto that)) return false;
        return Objects.equals(id, that.id);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(id);
	}

	@Override
	public String toString() {
		return "MarcaProduto{" +
				"id=" + id +
				", descricao='" + descricao + '\'' +
				'}';
	}
}
