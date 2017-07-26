package com.easytask.controller;

import com.easytask.model.Pedido;
import com.easytask.model.enumeracoes.CategoriaServico;
import com.easytask.model.enumeracoes.Status;
import com.easytask.repository.PedidoRepository;
import com.easytask.service.implementacao.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projetos")
public class ProjetosRestController {

    @Autowired
    private PedidoRepository pedidoRepository;

    @GetMapping
    public List<Pedido> allPedidos(@ModelAttribute("username") String username) {
        return pedidoRepository.findAllByStatusAndUsuario_UsernameNotOrderByDataInicio(Status.PENDENTE, username);
    }

    @GetMapping("/page")
    public Page<Pedido> allPedidos(@ModelAttribute("username") String username, Pageable pageable) {
        return pedidoRepository.findAllByStatusAndUsuario_UsernameNotOrderByDataInicio(Status.PENDENTE, username, pageable);
    }

    @GetMapping("/{categoria}")
    public List<Pedido> allPedidosCategoria (@PathVariable("categoria") CategoriaServico categoria, @ModelAttribute("username") String username) {
        return pedidoRepository.findAllByStatusAndUsuario_UsernameNotAndServico_CategoriaOrderByDataInicio(Status.PENDENTE, username, categoria);
    }

    @GetMapping("/{categoria}/page")
    public Page<Pedido> allPedidosCategoria (@PathVariable("categoria") CategoriaServico categoria, @ModelAttribute String username, Pageable pageable) {
        return pedidoRepository.findAllByStatusAndUsuario_UsernameNotAndServico_CategoriaOrderByDataInicio(Status.PENDENTE, username, categoria, pageable);
    }
}
