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

    @NumberFormat(pattern = "#,##0.00")
    private BigDecimal valor_estimado;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Servico> servicos;

    @DateTimeFormat(pattern = "dd/MM/yyyy hh:mm:ss")
    private Date data_inicio;

    @DateTimeFormat(pattern = "dd/MM/yyyy hh:mm:ss")
    private Date previsao_fim;

    @DateTimeFormat(pattern = "dd/MM/yyyy hh:mm:ss")
    private Date data_fim;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public List<Servico> getServicos() {
        return servicos;
    }

    public void setServicos(List<Servico> servicos) {
        this.servicos = servicos;
    }

    public Date getData_inicio() {
        return data_inicio;
    }

    public void setData_inicio(Date data_inicio) {
        this.data_inicio = data_inicio;
    }

    public Date getPrevisao_fim() {
        return previsao_fim;
    }

    public void setPrevisao_fim(Date previsao_fim) {
        this.previsao_fim = previsao_fim;
    }

    public Date getData_fim() {
        return data_fim;
    }

    public void setData_fim(Date data_fim) {
        this.data_fim = data_fim;
    }
}
