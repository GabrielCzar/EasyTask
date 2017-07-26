package com.easytask.repository;

import com.easytask.model.Pedido;
import com.easytask.model.enumeracoes.CategoriaServico;
import com.easytask.model.enumeracoes.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    List<Pedido> findAllByStatusNotAndUsuario_Username (Status status, String username);
    List<Pedido> findAllByStatusAndUsuario_UsernameNotOrderByDataInicio(Status status, String username);
    Page<Pedido> findAllByStatusAndUsuario_UsernameNotOrderByDataInicio(Status status, String username, Pageable pageable);
    List<Pedido> findAllByStatusAndUsuario_UsernameNotAndServico_CategoriaOrderByDataInicio(Status status, String username, CategoriaServico categoria);
    Page<Pedido> findAllByStatusAndUsuario_UsernameNotAndServico_CategoriaOrderByDataInicio(Status status, String username, CategoriaServico categoria, Pageable pageable);
    List<Pedido> findAllByProfissionalUsername(String username);
}
