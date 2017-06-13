package com.easytask.model.enumeracoes;

/**
 * Created by gabriel on 29/05/17.
 */
public enum TipoServico {
    HOMEOFFICE("Homeoffice"),
    PRESENCIAL("Presencial");

    private String nome;

    private TipoServico (String nome) { this.nome = nome; }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
