package com.easytask.model;

import com.easytask.model.enumeracoes.Status;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Entity(name = "pedidos")
public class Pedido {
    private static final long serialVersionUID = 330777716768268993L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @ManyToOne
    @JoinColumn(name = "id_usuario", referencedColumnName = "username")
    private Usuario usuario;

    @OneToOne
    private Servico servico;

    @NumberFormat(pattern = "#,##0.00")
    private BigDecimal valor;

    @NumberFormat(pattern = "#,##0.00")
    private BigDecimal valor_estimado;

    @Nullable
    private Integer prazo;

    @DateTimeFormat(pattern = "dd/MM/yyyy hh:mm:ss")
    private Date dataInicio;

    @DateTimeFormat(pattern = "dd/MM/yyyy hh:mm:ss")
    private Date previsaoFim;

    @DateTimeFormat(pattern = "dd/MM/yyyy hh:mm:ss")
    private Date dataFim;

    @Enumerated(EnumType.STRING)
    private Status status;

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

    public Servico getServicos() {
        return servico;
    }

    public void setServicos(Servico servico) { this.servico = servico; }

    public String getDataInicio() {
        SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return fmt.format(dataInicio);
    }

    public void setDataInicio(Date data_inicio) {
        this.dataInicio = data_inicio;
    }

    public String getPrevisaoFim() {
        SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return fmt.format(previsaoFim);
    }

    public void setPrevisao_fim(Date previsao_fim) {
        this.previsaoFim = previsaoFim;
    }

    public String getDataFim() {
        SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return fmt.format(dataFim);
    }

    public void setData_fim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public BigDecimal getValor_estimado() {
        return valor_estimado;
    }

    public void setValor_estimado(BigDecimal valor_estimado) {
        this.valor_estimado = valor_estimado;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Integer getPrazo() {
        return prazo;
    }

    public void setPrazo(Integer prazo) {
        this.prazo = prazo;
    }
}
