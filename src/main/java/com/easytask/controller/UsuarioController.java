package com.easytask.controller;

import com.easytask.service.implementacao.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.easytask.model.Usuario;
import com.easytask.service.implementacao.UsuarioService;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.jws.WebParam;
import java.util.HashMap;

@Controller
@RequestMapping("/user")
public class UsuarioController {
    private static final String MENSAGEM = "message", COR = "cor";

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    SecurityService securityService;

    @GetMapping
    public ModelAndView user (Authentication auth, HashMap<String, Object> map) {
        map.put("usuario", usuarioService.findUserByUsername(auth.getName()));
        return new ModelAndView("user/user", map);
    }

    @PostMapping("/edit/basic")
    public ModelAndView editUser (Usuario usuario, RedirectAttributes attributes) {
        Usuario aux = usuarioService.findUserByUsername(usuario.getUsername());
        aux.merge(usuario.getNome(), usuario.getEmail(), usuario.getTelefone());
        usuarioService.update(aux);
        attributes.addFlashAttribute(MENSAGEM, "Seus dados foram atualizados com sucesso!");
        attributes.addFlashAttribute(COR, "green");
        return new ModelAndView("redirect:/user");
    }

    @PostMapping("/edit/hard")
    public ModelAndView editUserPass(String username, String password, String cpassword, String oldpassword, RedirectAttributes redirect) {
        if (password.equals(cpassword) &&
                securityService.reAutenticar(username, oldpassword)) {
            Usuario usuario = usuarioService.findUserByUsername(username);
            usuario.setHashSenha(password);
            usuarioService.update(usuario);
            securityService.reAutenticar(username, password);
            redirect.addFlashAttribute(MENSAGEM, "Senha alterada!");
            redirect.addFlashAttribute(COR, "green");
        }
        redirect.addFlashAttribute(MENSAGEM, "Não foi possivel alterar a senha!");
        redirect.addFlashAttribute(COR, "red");

        return new ModelAndView("redirect:/user");
    }
}
