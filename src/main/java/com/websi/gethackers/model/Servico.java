package com.websi.gethackers.model;

import com.websi.gethackers.model.enumeracoes.CategoriaServico;
import com.websi.gethackers.model.enumeracoes.TipoServico;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by gabriel on 29/05/17.
 */
@Entity(name = "servicos")
public class Servico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    @NotNull
    @Enumerated(EnumType.STRING)
    private TipoServico tipo;

    @NotNull
    @Enumerated(EnumType.STRING)
    private CategoriaServico categoria;

    private String Atividade;

    private String descricao;
}
