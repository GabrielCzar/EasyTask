package com.easytask.repository;

import com.easytask.model.Servico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Servicos extends JpaRepository<Servico, Long> {
    // public List<Servico> findServiceByNameContainingIgnoreCase(String nome);
}
