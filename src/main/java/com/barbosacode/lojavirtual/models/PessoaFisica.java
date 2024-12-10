package com.barbosacode.lojavirtual.models;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;

import lombok.Data;

@Data
@Entity
public class PessoaFisica extends Pessoa{

	private String cpf;
	
	@Column(name = "data_nascimento")
	private LocalDate dataNascimento;
	
}
