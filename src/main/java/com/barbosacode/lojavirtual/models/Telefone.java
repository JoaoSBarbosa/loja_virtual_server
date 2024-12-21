package com.barbosacode.lojavirtual.models;

import com.barbosacode.lojavirtual.enums.TipoTelefone;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@Entity
@Table(name = "telefone")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@SequenceGenerator(name = "seq_telefone", sequenceName = "seq_telefone", allocationSize = 1, initialValue = 1)
public class Telefone {
	
	@Id
	@EqualsAndHashCode.Include
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

	

}
