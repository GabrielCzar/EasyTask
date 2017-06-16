package com.easytask.model;

import com.sun.istack.internal.Nullable;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "usuarios",
        uniqueConstraints =
        @UniqueConstraint(columnNames = {"email", "cpf"}, name = "usuario_uk"))
public class Usuario implements UserDetails {
    private static final long serialVersionUID = 991043816768268993L;
    @Id
    @NotBlank(message = "Nome de usuário é obrigatório")
    @Size(message = "O username deve ter no minimo 4 caracteres", min = 4)
    private String username;

    @Order
    @NotBlank(message = "O Nome é obrigatório")
    private String nome;

    @Size(message = "O CPF deve ter 11 caracteres", min = 11)
    @CPF(message = "O CPF inválido")
    @Column(unique = true)
    private String cpf;

    @NotNull(message = "O email é obrigatório")
    @Column(unique = true)
    @Email(message = "O email é inválido")
    private String email;

    @NotNull(message = "A senha é obrigatoria")
    @Size(min = 4)
    @Pattern(regexp = ".*[A-Za-z0-9]+.*", message = "A senha deve conter letras e números")
    private String password;

    private String telefone;

    private String foto;

    private Boolean habilitado;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="papel_usuario", joinColumns=@JoinColumn(name="usuario_id"), inverseJoinColumns=@JoinColumn(name="papel_id"))
    private List<Papel> papeis;

    @Nullable
    private String profissao;

    @OneToMany
    private List<Avaliacao> avaliacoes;

    public boolean hasProfissao () {
        return profissao != null;
    }

    public String getProfissao() {
        return profissao;
    }

    public void setProfissao(String profissao) {
        this.profissao = profissao;
    }

    public List<Avaliacao> getAvaliacoes() {
        return avaliacoes;
    }

    public void setAvaliacoes(List<Avaliacao> avaliacoes) {
        this.avaliacoes = avaliacoes;
    }

    public boolean isHabilitado() {
        return habilitado;
    }

    public void setHabilitado(boolean habilitado) {
        this.habilitado = habilitado;
    }

    public List<Papel> getPapeis() {
        return papeis;
    }

    public void setPapeis(List<Papel> papeis) {
        this.papeis = papeis;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Transient
    private String url;


    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public void setHashSenha(String senha) {
        this.setPassword(new BCryptPasswordEncoder().encode(senha));
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Usuario other = (Usuario) obj;
        if (cpf == null) {
            if (other.cpf != null)
                return false;
        } else if (!cpf.equals(other.cpf))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
        return result;
    }

    @Override
    public String getPassword() { return this.password; }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getUsername() { return this.username; }
    
	public void setUsername(String username) { this.username = username; }
    
    public Boolean getHabilitado() {
		return habilitado;
	}

	public void setHabilitado(Boolean habilitado) {
		this.habilitado = habilitado;
	}

	@Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.habilitado;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.papeis;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
