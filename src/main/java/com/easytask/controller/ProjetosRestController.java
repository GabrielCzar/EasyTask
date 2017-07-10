package com.easytask.controller;

import com.easytask.model.Pedido;
import com.easytask.model.enumeracoes.Status;
import com.easytask.repository.PedidoRepository;
import com.easytask.service.implementacao.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/projetos")
public class ProjetosRestController {

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    PedidoRepository pedidoRepository;

    @GetMapping
    public List<Pedido> allPedidos(String username) {
        return pedidoRepository.findAllByStatusAndUsuario_UsernameNot(Status.PENDENTE, username);
    }

    @GetMapping("/filter")
    public List<Pedido> allPedidosCategoria (String filter, String username) {
        return pedidoRepository.findAllByStatusAndUsuario_UsernameNot(Status.PENDENTE, username, new Sort(Sort.Direction.ASC, filter));
    }
}
