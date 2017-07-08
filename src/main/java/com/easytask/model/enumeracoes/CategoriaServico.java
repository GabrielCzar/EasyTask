package com.easytask.model.enumeracoes;

import java.util.ArrayList;
import java.util.List;

public enum CategoriaServico {
    SOFTWARE_E_DESENVOLVIMENTO_DE_APLICACOES("Software e Desenvolvimento de Aplicações")
 //           "Desenvolvimento de Aplicações Web", "Desenvolvimento de Aplicações Móveis", "Desenvolvimento de Software",
 ///           "Desenvolvimento de Jogos", "Implementação de Ferramentas e Aplicações", "Scripts e Utilidades", "Consulta Sobre Desenvolvimento de uma Aplicação",
  ///          "Hospedagem e Administração de Servidores", "Web UX / UI", "Analise de Dados", "Outro"
    ,
    DESIGN_E_MULTIMIDIA("Design e Multimidia"),
//        "Design Gráfico", "Webdesign", "Materiais Impressos", "Produção de Video",
  //          "Fotografia", "Ilustração", "Animação", "Graficos 3D", "Produção de Som", "Dublagem", "Consultoria de Design",
    ///        "Outro"}),
    ESCRITA_E_TRADUCAO("Escrita e Tradução"),
    //"Copyright", "Escrever Blogs e Artigos", "Tradução", "Revisão de Textos", "Redação Técnica", "Escrita Criativa", "Outro"}),
    MARKETING_E_COMERCIO("Marketing e Comércio"),
   // "Redes Sociais", "Campanhas", "SEO", "Telemarketing", "Email Marketing",
     //       "Estratégias de Marketing", "Publicidade Offline", "Consultria de Marketing", "Outro"}),
    SERVICOS_ADMINISTRATIVOS("Serviços Administrativos"),
//    "Assitente Virtual", "Administração", "Suporte ao Cliente", "Ferramentas Administrativas","Outro"
    SERVICOS_CORPORATIVOS("Serviços Corporativos"),
  //  "Escrituras", "Serviços Finançeiros e Planejamento", "Serviços Juridicos",
    //        "Serviços de RH", "Estatistica e Analise", "Outro"}),
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
