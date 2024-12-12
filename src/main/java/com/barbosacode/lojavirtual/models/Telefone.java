package com.barbosacode.lojavirtual.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

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
	private String ddd;
	private String numero;
	@Column(name = "tipo_telefone")
	private String tipoTelefone;
	@ManyToOne
	@JoinColumn(name = "id_pessoa")
	private Pessoa pessoaTelefone;

	

}
