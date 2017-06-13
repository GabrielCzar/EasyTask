package com.easytask.repository;

import com.easytask.model.Token;
import com.easytask.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends JpaRepository<Token, String> {
    Token findByUsuario(Usuario usuario);
}
