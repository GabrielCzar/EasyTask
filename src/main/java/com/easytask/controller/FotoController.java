package com.easytask.controller;

import com.easytask.dto.Foto;
import com.easytask.service.implementacao.UsuarioService;
import com.easytask.storage.FotoReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/fotos")
public class FotoController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired(required = false)
    private FotoReader fotoReader;

    @RequestMapping(value = "/{username}", method = RequestMethod.POST)
    public Foto upload(@PathVariable String username
            , @RequestParam("files[]") MultipartFile[] files) {

        String url = usuarioService.salvarFoto(username, files[0]);
        return new Foto(url);
    }

    @RequestMapping("/{nome:.*}")
    public byte[] recuperar(@PathVariable String nome) {
        return fotoReader.recuperar(nome);
    }

}