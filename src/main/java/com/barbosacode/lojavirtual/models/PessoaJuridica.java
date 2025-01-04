package com.barbosacode.lojavirtual.models;
import javax.persistence.*;

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
	

	public PessoaJuridica() {}

	public PessoaJuridica(String cnpj, String inscricaoEstadual, String inscricaoMunicipal, String nomeFantasia, String razaoSocial, String categoria) {
		this.cnpj = cnpj;
		this.inscricaoEstadual = inscricaoEstadual;
		this.inscricaoMunicipal = inscricaoMunicipal;
		this.nomeFantasia = nomeFantasia;
		this.razaoSocial = razaoSocial;
		this.categoria = categoria;
	}

	public PessoaJuridica(Long id, String nome, String sobrenome, String email, String cnpj, String inscricaoEstadual, String inscricaoMunicipal, String nomeFantasia, String razaoSocial, String categoria) {
		super(id, nome, sobrenome, email);
		this.cnpj = cnpj;
		this.inscricaoEstadual = inscricaoEstadual;
		this.inscricaoMunicipal = inscricaoMunicipal;
		this.nomeFantasia = nomeFantasia;
		this.razaoSocial = razaoSocial;
		this.categoria = categoria;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getInscricaoEstadual() {
		return inscricaoEstadual;
	}

	public void setInscricaoEstadual(String inscricaoEstadual) {
		this.inscricaoEstadual = inscricaoEstadual;
	}

	public String getInscricaoMunicipal() {
		return inscricaoMunicipal;
	}

	public void setInscricaoMunicipal(String inscricaoMunicipal) {
		this.inscricaoMunicipal = inscricaoMunicipal;
	}

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}


}
