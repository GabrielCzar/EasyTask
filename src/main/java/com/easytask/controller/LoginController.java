package com.easytask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.easytask.model.Usuario;
import com.easytask.service.implementacao.UsuarioService;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.ArrayList;

@Controller
public class LoginController {

    @Autowired
    UsuarioService usuarioService;

    @RequestMapping("/login")
    public ModelAndView login (@AuthenticationPrincipal User user) {
        if (user != null)
            return new ModelAndView("redirect:/");
        return new ModelAndView("login");
    }

    @PostMapping("/cadastro")
    public ModelAndView add(@Valid @ModelAttribute("usuario") Usuario usuario,
                            BindingResult result,
                            RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            ArrayList<String> erros = new ArrayList<>();
            for (FieldError err : result.getFieldErrors()) erros.add(err.getDefaultMessage());
            redirectAttributes.addFlashAttribute("erros", erros);
            return new ModelAndView("redirect:/login#form_cadastro");
        }
        usuarioService.add(usuario);
        return new ModelAndView("/user/home");
    }

}
