package com.websi.easytask.repository;

import com.websi.easytask.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

/**
 * Created by gabriel on 29/05/17.
 */
public interface Pedidos extends JpaRepository<Pedido, Long> {
    // Buscar onde a data está dentro de um período.
    // List<Pedido> findByPedidoBetween(Date inicio, Date fim);
}
