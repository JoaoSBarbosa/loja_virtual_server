package com.barbosacode.lojavirtual.models;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;


@Entity
@PrimaryKeyJoinColumn(name = "id")
@Table(name = "pessoa_fisica")
public class PessoaFisica extends Pessoa{

	@Column(nullable = false)
	private String cpf;
	
	@Column(name = "data_nascimento")
	private LocalDate dataNascimento;

	public PessoaFisica() {}

	public PessoaFisica(String cpf, LocalDate dataNascimento) {
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
	}

	public PessoaFisica(Long id, String nome, String sobrenome, String email, String cpf, LocalDate dataNascimento) {
		super(id, nome, sobrenome, email);
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	@Override
	public String toString() {
		return "PessoaFisica{" +
				"cpf='" + cpf + '\'' +
				", dataNascimento=" + dataNascimento +
				'}';
	}
}
