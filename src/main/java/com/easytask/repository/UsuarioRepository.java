package com.easytask.repository;

import com.easytask.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String>{
    Usuario findUsuarioByEmail(String email);
    List<Usuario> findAllByOrderByNomeAsc();
}
