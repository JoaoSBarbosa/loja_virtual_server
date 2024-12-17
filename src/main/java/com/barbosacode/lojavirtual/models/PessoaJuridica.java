package com.barbosacode.lojavirtual.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "pessoa_juridica")
@SequenceGenerator(name="seq_pessoa_juridica", allocationSize = 1,initialValue = 1)
public class PessoaJuridica extends Pessoa{

	@Column(nullable = false)
	private String cnpj;
	@Column(name = "inscricao_estadual", nullable = false)
	private String inscricaoEstadual;
	@Column(name = "inscricao_municiapal")
	private String inscricaoMunicipal;
	@Column(name = "nome_fantasia", nullable = false)
	private String nomeFantasia;
	@Column(name = "razao_social", nullable = false)
	private String razaoSocial;
	private String categoria;
	

}
