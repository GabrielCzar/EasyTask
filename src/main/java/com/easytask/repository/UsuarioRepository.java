package com.easytask.repository;

import com.easytask.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String>{
    Usuario findByEmail(String email);

    Usuario findByCpf(String cpf);

    List<Usuario> findAllByOrderByNomeAsc();
}
