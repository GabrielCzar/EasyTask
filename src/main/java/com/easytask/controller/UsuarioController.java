package com.easytask.controller;

import com.easytask.model.Usuario;
import com.easytask.service.implementacao.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import javax.swing.plaf.PanelUI;
import javax.validation.Valid;

@Controller
@RequestMapping("user")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @GetMapping
    public ModelAndView user () {
        return new ModelAndView("user/user");
    }

    @PostMapping("/edit")
    public ModelAndView editUser (@ModelAttribute("usuario") Usuario usuario, ModelAndView mv) {
        System.out.println("TENTANDO ALTERAR SO TESTE");
        return mv;
    }
}
