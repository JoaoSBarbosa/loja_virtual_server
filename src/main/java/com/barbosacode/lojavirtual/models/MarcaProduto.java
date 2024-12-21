package com.barbosacode.lojavirtual.models;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "marca_produto")
@SequenceGenerator(name = "seq_marca_produto", sequenceName = "seq_marca_produto", allocationSize = 1, initialValue = 1)
public class MarcaProduto {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_marca_produto")
	private Long id;
	@Column(nullable = false)
	private String descricao;
}
