package com.easytask.repository;

import com.easytask.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    // Buscar onde a data está dentro de um período.
    //List<Pedido> findByPedidoBetween(Date inicio, Date fim);
}
