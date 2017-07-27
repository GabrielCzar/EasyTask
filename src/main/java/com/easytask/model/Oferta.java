package com.easytask.model;

import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Oferta {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NumberFormat(pattern = "#,##")
    private BigDecimal valor;

    @OneToOne
    private Usuario usuario;

    @ManyToOne
    private Pedido pedido;

    public Oferta () {}

    public Oferta (BigDecimal valor, Usuario usuario, Pedido pedido) {
        this.valor = valor;
        this.usuario = usuario;
        this.pedido = pedido;
    }

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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
}
