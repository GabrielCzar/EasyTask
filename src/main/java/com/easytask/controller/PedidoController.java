package com.easytask.controller;

import com.easytask.model.Pedido;
import com.easytask.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.awt.event.MouseEvent;

/**
 * Created by gabriel on 23/06/17.
 */
@Controller
@RequestMapping("/pedidos/user")
public class PedidoController {

    @Autowired
    PedidoRepository pedidoRepository;

    @GetMapping
    public ModelAndView pedidosShow (ModelAndView mv) {
        Long id = Long.parseLong("1");
        Pedido pedido = pedidoRepository.findOne(id);
        System.out.println("PEDIDO DATA INICIO" + pedido.getDataFim());
        System.out.println("PEDIDO DATA 2" + pedido.getPrevisaoFim());
        System.out.println("PEDIDO DATA 3" + pedido.getDataInicio());
        mv.addObject("pedido", pedido);
        mv.setViewName("pedido/pedidos");
        return mv;
    }
}
