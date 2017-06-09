package com.websi.easytask.repository;

import com.websi.easytask.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by gabriel on 01/06/17.
 */
public interface Usuarios extends JpaRepository<Usuario, String>{
    // public List<Usuario> findByEmail(String email);

    // public List<Usuario> findByCpf(String cpf);
}
