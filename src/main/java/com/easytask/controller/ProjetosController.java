package com.easytask.controller;

import com.easytask.model.Pedido;
import com.easytask.model.Usuario;
import com.easytask.model.enumeracoes.Status;
import com.easytask.repository.PedidoRepository;
import com.easytask.service.implementacao.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;

@Controller
@RequestMapping("/projetos")
public class ProjetosController {

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    PedidoRepository pedidoRepository;

    @GetMapping("/show")
    public ModelAndView showProjetos(Authentication auth, HashMap<String, Object> map) {
        map.put("pedidos", pedidoRepository.findAllByStatusAndUsuario_UsernameNot(Status.PENDENTE, auth.getName()));
        return new ModelAndView("pedidos/show-projetos");
    }

}
