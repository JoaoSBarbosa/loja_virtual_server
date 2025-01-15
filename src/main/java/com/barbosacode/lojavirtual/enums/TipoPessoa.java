package com.barbosacode.lojavirtual.enums;

public enum TipoPessoa {
    USUARIO("Usuário"),
    FUNCIONARIO("Funcionário"),
    ADMINISTRADOR("Administrador"),
    CLIENTE("Cliente"),
    FORNECEDOR("Fornecedor"),
    PARCEIRO("Parceiro"),
    ESTAGIARIO("Estagiário"),
    FREELANCER("Freelancer"),
    GERENTE("Gerente");

    private String descricao;

    public String getDescricao() {
        return descricao;
    }

    private TipoPessoa(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String  toString() {
        return "TipoPessoa{" +
                "descricao='" + descricao + '\'' +
                '}';
    }
}
