package com.easytask.service;

import com.easytask.model.Usuario;

import java.util.List;

public interface IUsuarioService {

    boolean add (Usuario usuario);

    boolean update (Usuario usuario);

    Usuario findUserByUsername (String username);

    Usuario findUsuarioByUsernameOrEmail(String username_or_email);

    List<Usuario> findUser();

    boolean delete (Usuario usuario);

    Usuario findByEmail (String email);

    boolean enableUser (String username, boolean enable);

}
