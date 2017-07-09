package com.easytask.model.enumeracoes;

public enum Status {
    ATIVO("Ativo"), PENDENTE("Aprovação Pendente"), ATRASADO("Atrasado"), INATIVO("Desativado");

    private String descricao;

    Status (String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
