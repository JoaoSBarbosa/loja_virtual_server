package com.barbosacode.lojavirtual.models;

import javax.persistence.*;

import com.barbosacode.lojavirtual.enums.TipoEndereco;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "endereco")
@SequenceGenerator(name = "seq_endereco",sequenceName = "seq_endereco",allocationSize = 1,initialValue = 1)
public class Endereco {

	@Id
	@EqualsAndHashCode.Include
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
	@JoinColumn(
	    name = "id_pessoa", // Nome da coluna que será criada na tabela Endereco para armazenar a chave estrangeira (ID de Pessoa).
	    nullable = false, // Indica que essa coluna é obrigatória e não pode conter valores nulos.
	    foreignKey = @ForeignKey( // Configura a chave estrangeira e sua restrição no banco de dados.
	        value = ConstraintMode.CONSTRAINT, // Habilita a restrição de chave estrangeira no banco.
	        name = "fk_endereco_pessoa" // Nome da restrição de chave estrangeira no banco de dados. 
	    )
	)
	private Pessoa pessoaEndereco; // Representa a associação entre Endereco e Pessoa.

}
