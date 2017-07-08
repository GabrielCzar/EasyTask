package com.easytask.controller;

import com.easytask.model.Servico;
import com.easytask.model.enumeracoes.CategoriaServico;
import com.easytask.model.enumeracoes.TipoServico;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.WebParam;
import java.util.HashMap;

@Controller
@RequestMapping("servicos/user")
public class ServicosController {

    @GetMapping
    public ModelAndView servicosUser (HashMap<String, Object> map){
        map.put("tipos", TipoServico.values());
        map.put("categorias", CategoriaServico.values());
        return new ModelAndView("servico/servico", map);
    }

    @PostMapping("/new-order")
    public ModelAndView newOrder (Servico servico, HashMap<String, Object> map){
        map.put("tipos", TipoServico.values());
        map.put("categorias", CategoriaServico.values());
        return new ModelAndView("servico/order-finish", map);
    }


    @PostMapping("/finish-order")
    public ModelAndView newOrderFinish (Servico servico, ModelAndView mv) {
        System.out.println("ENTROU FINISH ----------- FINISH");
        return servicosUser(new HashMap<>());
    }

    @PostMapping("/{id}")
    public ModelAndView saveServicoUser (){
        return new ModelAndView("servico/servico");
    }

}
