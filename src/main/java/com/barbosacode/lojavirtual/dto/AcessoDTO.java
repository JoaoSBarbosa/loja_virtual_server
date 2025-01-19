package com.barbosacode.lojavirtual.dto;
import com.barbosacode.lojavirtual.models.Acesso;
import java.io.Serializable;


public class AcessoDTO implements Serializable {

    private Long id;
    private String descricao;

    public AcessoDTO() {}

    public AcessoDTO(Long id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public AcessoDTO(Acesso acesso) {
        id = acesso.getId();
        descricao = acesso.getDescricao();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
