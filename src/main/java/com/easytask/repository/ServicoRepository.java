package com.easytask.repository;

import com.easytask.model.Servico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicoRepository extends JpaRepository<Servico, Long> {
    //@Query(value = "SELECT s.* FROM servicos s WHERE nome = ?1", nativeQuery = true)
    //List<Servico> findServiceByNameContainingIgnoreCase(String nome);
}
