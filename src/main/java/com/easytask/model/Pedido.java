package com.easytask.model;

import com.easytask.model.enumeracoes.Status;
import org.hibernate.validator.constraints.NotBlank;
import org.joda.time.DateTime;
import org.joda.time.Duration;
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
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @ManyToOne @JoinColumn(name = "username", referencedColumnName = "username")
    private Usuario usuario;

    @OneToMany(cascade = {CascadeType.DETACH}, targetEntity = Oferta.class)
    private List<Oferta> ofertas;

    @OneToOne
    private Servico servico;

    @NumberFormat(pattern = "#,##")
    private BigDecimal valor;

    @NumberFormat(pattern = "#,##")
    private BigDecimal valorEstimado;

    @DateTimeFormat(pattern = "dd/MM/yyyy hh:mm:ss")
    private Date dataInicio;

    @DateTimeFormat(pattern = "dd/MM/yyyy hh:mm:ss")
    private Date previsaoFim;

    @DateTimeFormat(pattern = "dd/MM/yyyy hh:mm:ss")
    private Date dataFim;

    @Enumerated(EnumType.STRING)
    private Status status;

    @NotBlank @NotNull(message = "Nome é obrigatório")
    private String nome;

    private Integer prazo;

    @OneToOne
    private Usuario profissional;

    // ___________ GETTERS AND SETTERS _______________


    public Usuario getProfissional() {
        return profissional;
    }

    public void setProfissional(Usuario profissional) {
        this.profissional = profissional;
    }

    public String getDataInicio() {
        SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return fmt.format(dataInicio);
    }

    public String getDataInicioOnly() {
        SimpleDateFormat fmt = new SimpleDateFormat("dd/MM");
        return fmt.format(dataInicio);
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public String getPrevisaoFim() {
        SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return fmt.format(previsaoFim);
    }

    public void setPrevisaoFim() {
        this.previsaoFim = new DateTime(dataInicio).plusDays(prazo).toDate();
    }

    public String getDataFim() {
        SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return fmt.format(dataFim);
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public BigDecimal getValorEstimado() {
        return valorEstimado;
    }

    public void setValorEstimado(BigDecimal valorEstimado) {
        this.valorEstimado = valorEstimado;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getPrazo() {
        return prazo;
    }

    public String getPrazoDecorrido() {
        return String.valueOf(new Duration(new DateTime(dataInicio), new DateTime()).getStandardDays()) + " Dias";
    }

    public String getPrazoRestante() {
        return String.valueOf(prazo - new Duration(new DateTime(dataInicio), new DateTime()).getStandardDays()) + " Dias";
    }

    public void setPrazo(Integer prazo) {
        this.prazo = prazo;
    }

    public List<Oferta> getOfertas() {
        return ofertas;
    }

    public void setOfertas(List<Oferta> ofertas) {
        this.ofertas = ofertas;
    }

    public void addOferta(Oferta oferta) {
        ofertas.add(oferta);
    }

    public void remOferta(Oferta oferta) {
        this.ofertas.remove(oferta);
    }

    @Override
    public String toString() {
        return String.format("Pedido: \nPrazo %d \nValor Estimado %f",
                    prazo, valorEstimado);

    }
}
