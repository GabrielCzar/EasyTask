package com.easytask.service;

import com.easytask.model.Token;
import com.easytask.model.Usuario;

import java.util.List;

public interface IUsuarioService {

    boolean add (Usuario usuario);

    boolean update (Usuario usuario);

    Usuario findUserByUsername (String username);

    List<Usuario> findUser();

    boolean delete (Usuario usuario);

    Usuario findByEmail (String email);

    void recoverPassword (String email);

    void newPassword (Token token, String password);

    boolean enableUser (String username, boolean enable);

}
