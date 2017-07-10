package com.easytask.repository;

import com.easytask.model.Pedido;
import com.easytask.model.Usuario;
import com.easytask.model.enumeracoes.Status;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    List<Pedido> findAllByStatusNotAndUsuario_Username (Status status, String username);
    List<Pedido> findAllByStatusAndUsuario_UsernameNot(Status status, String username);
    List<Pedido> findAllByStatusAndUsuario_UsernameNot(Status status, String username, Sort sort);
}
