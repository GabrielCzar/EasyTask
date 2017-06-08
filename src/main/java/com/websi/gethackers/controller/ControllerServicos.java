package com.websi.gethackers.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by gabriel on 23/05/17.
 */

@Controller
@RequestMapping("/servicos")
public class ControllerServicos {

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
