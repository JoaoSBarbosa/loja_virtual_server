package com.barbosacode.lojavirtual.enums;

public enum StatusContaPagar {

    COBRANCA("Pagar"),
    VENCIDA("Vencida"),
    ABERTA("Aberta"),
    QUITADA("Quitada"),
    NEGOCIADA("Renegociada"),
    CANCELADA_PARCIALMENTE("Cancelada Parcialmente"),
    CANCELADA_TOTALMENTE("Cancelada Totalmente"),
    EM_ANALISE("Em Análise"),
    AGUARDANDO_APROVACAO("Aguardando Aprovação"),
    EM_NEGOCIACAO("Em Negociação"),
    PENDENTE("Pendente"),
    PARCIALMENTE_PAGA("Parcialmente Paga"),
    BLOQUEADA("Bloqueada"),
    EM_DISPUTA("Em Disputa"),
    PAGAMENTO_FUNCIONARIO("Pagamento Funcionario"),
    DEVOLVIDA("Devolvida");


    private String descricao;

    private StatusContaPagar(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    @Override
    public String toString(){
        return this.descricao;
    }
}
