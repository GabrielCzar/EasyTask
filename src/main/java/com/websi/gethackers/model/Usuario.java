package com.websi.gethackers.model;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Created by gabriel on 01/06/17.
 */
@Entity
@Table(name = "usuarios",
        uniqueConstraints =
            @UniqueConstraint(columnNames = {"email", "cpf"}, name = "usuario_uk"))
public class Usuario {
    @Id
    @NotNull(message = "Nome de usuário é obrigatório")
    @Size(message = "O tamanho minimo é 4 caracteres", min = 4)
    private String username;

    @Size(message = "Tamanho inválido", min = 11)
    @CPF(message = "CPF inválido")
    @Column(unique = true)
    private String cpf;

    @NotNull(message = "Email é obrigatório")
    @Column(unique = true)
    @Email(message = "Email inválido")
    private String email;

    @NotNull(message = "Senha é obrigatoria")
    @Size(min = 4)
    @Pattern(regexp = ".*[A-Za-z0-9]+.*", message = "A senha deve conter letras e números")
    private String password;

    private String telefone;

    private String foto;

    @Transient
    private String url;

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
