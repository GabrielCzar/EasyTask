package com.easytask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.easytask.model.Usuario;
import com.easytask.service.implementacao.UsuarioService;

@Controller
public class LoginController {

    @Autowired
    UsuarioService usuarioService;

    @RequestMapping("/login")
    public String login (@AuthenticationPrincipal User user) {
        if (user != null)
            return "redirect:/";
        return "login";
    }

    @PostMapping("/cadastro")
    public ModelAndView add(@ModelAttribute("usuario") Usuario usuario, BindingResult result, ModelAndView mv) {
        mv.setViewName("/user/home");
        usuarioService.add(usuario);
        return mv;
    }

}
