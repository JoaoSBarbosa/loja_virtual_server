package com.barbosacode.lojavirtual.models;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@PrimaryKeyJoinColumn(name = "id")
@Table(name = "pessoa_fisica")
public class PessoaFisica extends Pessoa{

	@Column(nullable = false)
	private String cpf;
	
	@Column(name = "data_nascimento")
	private LocalDate dataNascimento;
	
}
