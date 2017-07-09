package com.easytask.controller;

import com.easytask.model.Pedido;
import com.easytask.repository.PedidoRepository;
import com.easytask.repository.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.awt.event.MouseEvent;
import java.util.HashMap;

/**
 * Created by gabriel on 23/06/17.
 */
@Controller
@RequestMapping("/pedidos/user")
public class PedidoController {

    @Autowired
    PedidoRepository pedidoRepository;

    @GetMapping("/{id}")
    public ModelAndView pedidosShow (@PathVariable Long id, HashMap<String, Object> map) {
        map.put("pedido", pedidoRepository.findOne(id));
        return new ModelAndView("pedidos/pedido", map);
    }

    @GetMapping("/{id}/delete")
    public ModelAndView deletePedido (@PathVariable Long id) {
        pedidoRepository.delete(id);
        return new ModelAndView("redirect:/servicos/user");
    }

}
