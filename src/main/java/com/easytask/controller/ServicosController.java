package com.easytask.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/servicos")
public class ServicosController {

    @GetMapping("/user")
    public String home () {
        return "/servicos";
    }

    @GetMapping("/user/{id}")
    public String getServicosUser (){
        return "/servicos";
    }

    @PostMapping("/user/{id}")
    public String saveServicoUser (){
        return "/servicos";
    }

    @DeleteMapping("/user/{id}")
    public String remServicosUser (){
        return "/servicos";
    }
}
