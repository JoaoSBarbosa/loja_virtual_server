package com.barbosacode.lojavirtual.enums;

public enum TipoPessoa {
    FISICA("Fisica"),
    JURIDICA("Juridica");

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
