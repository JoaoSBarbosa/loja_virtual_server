package com.barbosacode.lojavirtual.models;

import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

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
	
	private String cep;
	private String longradouro;
	private String numero;
	private String complemento;
	private String bairro;
	private String uf;
	private String cidade;
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
