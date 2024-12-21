package com.barbosacode.lojavirtual.enums;

public enum TipoTelefone {

    RESIDENCIAL("Residencial"),
    COMERCIAL("Comercial"),
    CELULAR("Celular"),
    FAX("Fax"),
    RECADO("Recado"),
    EMERGENCIA("Emergencia"),
    WHATSAPP("WhatsApp"),
    CONTATO_ALTERNATIVO("Contato Alternativo");

    private String descricao;

    private TipoTelefone(String descricao){
        this.descricao = descricao;
    }
    @Override
    public String toString() {
        return this.descricao;
    }
}
