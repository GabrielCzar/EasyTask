package com.easytask.controller;

import com.easytask.model.Usuario;
import com.easytask.service.implementacao.SecurityService;
import com.easytask.service.implementacao.UsuarioService;
import com.easytask.util.IPhotoStorage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;

@Controller
@RequestMapping("/user")
public class UsuarioController {
    private static final String MENSAGEM = "message", COR = "cor";

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private IPhotoStorage photoStorage;

    @GetMapping
    public ModelAndView user (Authentication auth, HashMap<String, Object> map) {
        Usuario user =  usuarioService.findUserByUsername(auth.getName());
        if (user.hasFoto()) user.setUrl(photoStorage.getUrl(user.getFoto()));
        map.put("usuario", user);
        return new ModelAndView("user/user", map);
    }

    @PostMapping("/edit/basic")
    public ModelAndView editUser (@ModelAttribute("usuario") Usuario usuario,
                                  RedirectAttributes attributes,
                                  @RequestParam(value = "imagem", required = false) MultipartFile imagem) {
        if (!imagem.isEmpty()) {
            usuario.setFoto(photoStorage.save(usuario.getUsername(), imagem));
            usuario.setUrl(photoStorage.getUrl(usuario.getFoto()));
        }

        Usuario aux = usuarioService.findUserByUsername(usuario.getUsername());
        aux.merge(usuario.getNome(), usuario.getEmail(), usuario.getTelefone(), usuario.getFoto());
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

    @GetMapping("/fotos/{foto:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String foto) {
        Resource file = photoStorage.loadAsResource(foto);
        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,  "attachment; filename=\"" + file.getFilename() + "\"")
                .body(file);
    }
}
