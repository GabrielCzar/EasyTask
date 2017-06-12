package com.easytask.repository;

import com.easytask.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Usuarios extends JpaRepository<Usuario, String>{
    // public List<Usuario> findByEmail(String email);

    // public List<Usuario> findByCpf(String cpf);
}
