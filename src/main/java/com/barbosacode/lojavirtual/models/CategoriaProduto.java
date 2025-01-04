package com.barbosacode.lojavirtual.models;
import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "categoria_produto")
@SequenceGenerator(name = "seq_categoria_produto", sequenceName = "seq_categoria_produto", allocationSize = 1,initialValue = 1)
public class CategoriaProduto {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_categoria_produto")
	private Long id;

	@Column(nullable = false)
	private String descricao;

	public CategoriaProduto() {}

	public CategoriaProduto(Long id, String descricao) {
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
		if (!(o instanceof CategoriaProduto that)) return false;
        return Objects.equals(id, that.id);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(id);
	}

	@Override
	public String toString() {
		return "CategoriaProduto{" +
				"id=" + id +
				", descricao='" + descricao + '\'' +
				'}';
	}
}
