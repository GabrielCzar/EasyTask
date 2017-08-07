package com.easytask.controller;

import com.easytask.model.Usuario;
import com.easytask.service.implementacao.EmailService;
import com.easytask.service.implementacao.SecurityService;
import com.easytask.service.implementacao.UsuarioService;
import com.easytask.util.files.IPhotoStorage;

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

import javax.jws.WebParam;
import java.util.HashMap;
import java.util.UUID;

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

    @Autowired
    private EmailService emailService;

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

    @GetMapping("/recovery-password")
    public ModelAndView recoveryPassword (@RequestParam String email, RedirectAttributes rd) {
        Usuario usuario = usuarioService.findByEmail(email);
        if (usuario == null) {
            rd.addFlashAttribute("recovery-fail", "Email não cadastrado!");
            return new ModelAndView("redirect:/login");
        }
        usuario.setRecoveryToken(UUID.randomUUID().toString());
        usuarioService.update(usuario);
        emailService.sendRecoveryEmail(email, usuario.getRecoveryToken());
        rd.addFlashAttribute("recovery-success", "Email para recuperação de senha enviado com sucesso!");
        return new ModelAndView("redirect:/login");
    }

    @GetMapping("/reset-password")
    public ModelAndView resetPassword (@RequestParam("token") String token, @RequestParam("email") String email, ModelAndView mv) {
        if (token == null)
            return new ModelAndView("redirect:/404");
        mv.addObject("token", token);
        mv.addObject("email", email);
        mv.setViewName("user/reset-password");
        return mv;
    }

    @PostMapping("/reset-password")
    public ModelAndView newPassword ( @RequestParam("email") String email,
                                      @RequestParam("token") String token,
                                      @RequestParam("password") String password,
                                      @RequestParam("confirm-password") String cpassword,
                                      RedirectAttributes rd) {
        Usuario usuario = usuarioService.findByEmail(email);
        if (!usuario.getRecoveryToken().equals(token))
            return new ModelAndView("redirect:/404");
        if (!password.equals(cpassword)) {
            rd.addFlashAttribute("Ocorreu um erro ao alterar a senha, acesse o link no seu email e tente novamente!");
            return new ModelAndView("redirect:/login");
        }
        usuario.setPassword(password);
        usuario.setRecoveryToken(UUID.randomUUID().toString());
        usuarioService.update(usuario);
        rd.addFlashAttribute("Senha alterada com sucesso!");
        return new ModelAndView("redirect:/login");
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
