package com.websi.gethackers.repository;

import com.websi.gethackers.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by gabriel on 01/06/17.
 */
public interface Usuarios extends JpaRepository<Usuario, String>{
    List<Usuario> findByEmail(String email);

    List<Usuario> findByCpf(String cpf);
}
