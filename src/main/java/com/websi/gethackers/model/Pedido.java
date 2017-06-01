package com.websi.gethackers.model;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by gabriel on 29/05/17.
 */
@Entity(name = "pedidos")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotNull
    @NumberFormat(pattern = "#,##0.00")
    private BigDecimal valor;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Servico> servicos;

    @DateTimeFormat(pattern = "dd/MM/yyyy hh:mm:ss")
    private Date data_inicio;
    @DateTimeFormat(pattern = "dd/MM/yyyy hh:mm:ss")
    private Date previsao_fim;
    @DateTimeFormat(pattern = "dd/MM/yyyy hh:mm:ss")
    private Date data_fim;
}
