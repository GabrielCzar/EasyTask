package com.easytask.model.enumeracoes;

public enum TipoServico {
    HOMEOFFICE("A Distância"),
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
