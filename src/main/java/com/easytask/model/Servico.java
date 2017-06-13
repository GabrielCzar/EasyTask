package com.easytask.model;

import com.easytask.model.enumeracoes.CategoriaServico;
import com.easytask.model.enumeracoes.TipoServico;
import org.hibernate.validator.constraints.NotBlank;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "servicos")
public class Servico {
    private static final long serialVersionUID = -3366643816768268993L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @NotNull(message = "Nome é obrigatório")
    private String nome;

    @NotNull
    @Enumerated(EnumType.STRING)
    private TipoServico tipo;

    @ElementCollection(targetClass = CategoriaServico.class)
    @Enumerated(EnumType.STRING)
    @Column(name = "categorias")
    private Set<CategoriaServico> categorias;

    @NotBlank
    private String atividade;

    @NotBlank
    private String descricao;

    @OneToOne
    @JoinColumn(name = "profissional_do_servico")
    private Usuario profissional;

    public Servico () {}

    public Servico (String nome, TipoServico tipo, Set<CategoriaServico> categorias, String atividade, String descricao) {
        this.nome = nome;
        this.tipo = tipo;
        this.categorias = categorias;
        this.atividade = atividade;
        this.descricao = descricao;
    }

    public Servico (Long id, String nome, TipoServico tipo, Set<CategoriaServico> categorias, String atividade, String descricao) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        this.categorias = categorias;
        this.atividade = atividade;
        this.descricao = descricao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public TipoServico getTipo() {
        return tipo;
    }

    public void setTipo(TipoServico tipo) {
        this.tipo = tipo;
    }

    public Set<CategoriaServico> getCategorias() { return categorias; }

    public void addCategoria(CategoriaServico categoria) { categorias.add(categoria); }

    public void remCategoria(CategoriaServico categoria) { categorias.remove(categoria); }

    public String getAtividade() {
        return atividade;
    }

    public void setAtividade(String atividade) {
        this.atividade = atividade;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
