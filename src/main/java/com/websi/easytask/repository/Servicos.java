package com.websi.easytask.repository;

import com.websi.easytask.model.Servico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by gabriel on 29/05/17.
 */
public interface Servicos extends JpaRepository<Servico, Long> {
    // public List<Servico> findServiceByNameContainingIgnoreCase(String nome);
}
