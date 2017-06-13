package com.easytask.model.enumeracoes;

/**
 * Created by gabriel on 29/05/17.
 */
public enum CategoriaServico {
    ASSITENCIA_TECNICA("Assistência Técnica"),
    AULAS("Aulas"),
    AUTOS("Autos"),
    CONSULTORIA("Consultoria"),
    DESIGN_E_TECNOLOGIA("Design e Tecnologia"),
    EVENTOS("Eventos"),
    MODA_E_BELEZA("Moda e Beleza"),
    REFORMAS("Reformas"),
    SAUDE("Saúde"),
    SERVICOS_DOMESTICOS("Serviços Domésticos");

    private String nome;

    private CategoriaServico(String nome) { this.nome = nome; }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
