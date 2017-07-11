package com.easytask.controller;

import java.util.ArrayList;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.easytask.model.Usuario;
import com.easytask.service.implementacao.UsuarioService;

@Controller
public class LoginAndHomeController {

    @Autowired
    UsuarioService usuarioService;

    @RequestMapping("/login")
    public ModelAndView login (Authentication authentication) {
        if (authentication != null)
            return new ModelAndView("redirect:/servicos/user");
        return new ModelAndView("login");
    }

    @PostMapping("/cadastro")
    public ModelAndView add(@Valid @ModelAttribute("usuario") Usuario usuario,
                            BindingResult result,
                            RedirectAttributes redirectAttributes) {
        try {
            if (result.hasErrors()) {
                ArrayList<String> erros = new ArrayList<>();
                for (FieldError err : result.getFieldErrors()) erros.add(err.getDefaultMessage());
                redirectAttributes.addFlashAttribute("erros", erros);
                return new ModelAndView("redirect:/login#form_cadastro");
            }

            usuarioService.add(usuario);

            return login(null);
        } catch (Exception e) {
            ArrayList<String> erros = new ArrayList<>();
            erros.add("CPF já existente!");
            redirectAttributes.addFlashAttribute("erros", erros);
            return new ModelAndView("redirect:/login#form_cadastro");
        }
    }

    @RequestMapping("/")
    public ModelAndView index(Authentication auth) {
        if (auth != null)
            return new ModelAndView("redirect:/servicos/user");
        return new ModelAndView("index");
    }

}
