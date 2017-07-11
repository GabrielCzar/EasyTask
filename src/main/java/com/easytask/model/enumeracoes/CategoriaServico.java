package com.easytask.model.enumeracoes;

public enum CategoriaServico {
    DESENVOLVEDOR_WEB_FRONT_END("Desenvolvimento de Aplicações Web (Front-End)"),
    DESENVOLVEDOR_WEB_BACK_END("Desenvolvimento de Aplicações Web (Back-End)"),
    DESENVOLVEDOR_DEV_OPS("Desenvolvimento de Aplicações Web (Dev-Ops)"),
    DESENVOLVEDOR_MOBILE("Desenvolvimento de Aplicações Móveis"),
    DESENVOLVEDOR_SOFTWARE("Desenvolvimento de Software"),
    DESENVOLVEDOR_DE_JOGOS("Desenvolvimento de Jogos"),
    DATABASES_RELACIONAIS("Banco de Dados Relacionais"),
    DATABASE_NAO_RELACIONAIS("Banco de Dados não Relacionais"),
    DESIGN("Design"),
    MULTIMIDIA("Multimídia"),
    ESCRITA("Escrita"),
    TRADUCAO("Tradução"),
    MARKETING_E_COMERCIO("Marketing e Comércio"),
    SERVICOS_ADMINISTRATIVOS("Serviços Administrativos"),
    SERVICOS_CORPORATIVOS("Serviços Corporativos"),
    SEO("SEO"),
    OUTRO("Outro");

    private String nome;

    private CategoriaServico (String nome) {
        this.nome = nome;
   }

    public String getNome () {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
