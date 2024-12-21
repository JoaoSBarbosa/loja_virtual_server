package com.barbosacode.lojavirtual.models;

import javax.persistence.*;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@PrimaryKeyJoinColumn(name = "id")
@Table(name = "pessoa_juridica")
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
