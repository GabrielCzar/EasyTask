package com.easytask.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.easytask.model.Pedido;
import com.easytask.repository.PedidoRepository;

@Controller
@RequestMapping("/pedidos/user")
public class PedidoController {

    @Autowired
    private PedidoRepository pedidoRepository;

    @GetMapping("/{id}")
    public ModelAndView pedidosShow (Authentication auth, @PathVariable Long id, HashMap<String, Object> map) {
        Pedido pedido = pedidoRepository.findOne(id);
        map.put("pedido", pedido);
        return new ModelAndView("pedidos/pedido", map);
    }

    @GetMapping("/{id}/delete")
    public ModelAndView deletePedido (@PathVariable Long id) {
        pedidoRepository.delete(id);
        return new ModelAndView("redirect:/servicos/user");
    }
}
