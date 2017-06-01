package com.websi.gethackers.model;

import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

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

    private Servico servico;

}
